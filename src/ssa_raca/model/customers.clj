(ns ssa-raca.model.customers
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]
            )
  )

(def mysql-db {
               :subprotocol "mysql"
               :subname "//localhost:3306/classicmodels"
               :user "ivan"
               :password "ivan"
               :zeroDateTimeBehaviour "convertToNull"
               })

(def now (str (java.sql.Timestamp. (System/currentTimeMillis))))

(defn allCustomers []
  (jdbc/query mysql-db
           ["SELECT * FROM customers c"]))

(defn removeC [id]
  (jdbc/delete! mysql-db :customers (sql/where {:customerNumber id})))

(defn get [id]
  (first (jdbc/query mysql-db
                     (sql/select * :customers (sql/where {:customerNumber id})))))

(defn update [id params]
  (jdbc/update! mysql-db :customers params (sql/where {:customerNumber id})))

(defn insertC
  [params]
  (jdbc/insert! mysql-db :customers params))
