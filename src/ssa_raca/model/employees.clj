(ns ssa-raca.model.employees
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

(defn allEmployees []
  (jdbc/query mysql-db
              ["SELECT * FROM employees e"]))

(defn removeE [id]
  (jdbc/delete! mysql-db :employees (sql/where {:employeeNumber id})))