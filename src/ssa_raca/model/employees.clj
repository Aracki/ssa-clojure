(ns ssa-raca.model.employees
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]
            )
  )

(def mysql-db {
               :subprotocol "mysql"
               :subname "//localhost:3306/classicmodels"
               :user "root"
               :password ""
               :zeroDateTimeBehaviour "convertToNull"
               })

(def now (str (java.sql.Timestamp. (System/currentTimeMillis))))

(defn allEmployees []
  (jdbc/query mysql-db
              ["SELECT * FROM employees e"]))

(defn allOffices []
  (jdbc/query mysql-db
              ["SELECT * FROM offices o"]))

(defn get [id]
  (first (jdbc/query mysql-db
                  (sql/select * :employees (sql/where {:employeeNumber id})))))

(defn removeE [id]
  (jdbc/delete! mysql-db :employees (sql/where {:employeeNumber id})))


(defn update [id params]
  (jdbc/update! mysql-db :employees params (sql/where {:employeeNumber id})))

(defn insertE
  [params]
  (jdbc/insert! mysql-db :employees params))
