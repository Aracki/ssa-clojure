(ns ssa-raca.lastfm.lastfm-api
  (:refer-clojure :exclude [read])
  (:require [clj-http.client :as http]
            [cheshire.core :as json]
            [clojure.string :refer [join]])
  (:import java.security.MessageDigest))

(def ^:dynamic *api* "http://ws.audioscrobbler.com/2.0/")

(defn ^:private md5 [s]
  (join (map (partial format "%02x")
             (.digest
               (doto (MessageDigest/getInstance "MD5")
                 (.reset)
                 (.update (.getBytes s)))))))

(defn ^:private sign
  [params secret]
  (md5
    (str (->> (for [[k v] (dissoc params :format)
                    :when v]
                [(name k) v])
              (sort-by first)
              (apply concat)
              (join))
         secret)))

(defn ^:private remove-stupid-characters [k]
  (-> (remove #{\@ \#} k)
      (join)
      (keyword)))

(defn ^:private req [method key http-method params]
  (let [params (assoc params
                 :format "json"
                 :method method
                 :api_key key)
        secret (:secret params)
        key-fn (:key-fn params remove-stupid-characters)
        params (dissoc params :secret :key-fn)
        params (assoc params :api_sig (sign params secret))]
    (-> (http/request {:url *api*
                       :method http-method
                       :query-params (when (= :get http-method) params)
                       :form-params (when (= :post http-method) params)})
        (:body)
        (json/decode key-fn))))

(defn read
  "Execute a read-only API request. Takes a method, API token, and some parameters.
   You can also pass in a :key-fn param that is passed to cheshire's decode function."
  [method key & [params]]
  (req method key :get params))

(defn write
  "Execute a writing API request. Takes a method, API token, and some parameters.
   You can also pass in a :key-fn param that is passed to cheshire's decode function."
  [method key & [params]]
  (req method key :post params))



(defn get-token
  "Takes an API key and secret and returns a map with an authentication token and
   a URL to send the user to in order to authenticate. The next step is
   to call get-session with the token returned here after the user has
   authenticated your application."
  [key secret]
  (when-let [token (:token (read "auth.getToken" key {:secret secret}))]
    {:url (format "http://www.last.fm/api/auth/?api_key=%s&token=%s" key token)
     :token token}))

(defn web-get-token
  "Takes an API key and returns a map with a URL to send the user to
   in to authenticate. You can optionally specify a callback URL
   that is different to your API Account callback url. The next step is
   to call get-session with the token supplied to the callback URL as a
   GET parameter after the user has authenticated your application."
  ([key]
   {:url (format "http://www.last.fm/api/auth/?api_key=%s" key)})
  ([key callback]
   {:url (format "http://www.last.fm/api/auth/?api_key=%s&cb=%s"
                 key
                 callback)}))

(defn get-session
  "Takes an API key, secret, and the :token key in the map returned by get-token.
   Returns a map containing :name and :key keys. To authenticate requests, just
   pass an :sk key in params with the :key key in the map returned by this function."
  [key secret token]
  (:session (read "auth.getSession" key {:token token :secret secret})))