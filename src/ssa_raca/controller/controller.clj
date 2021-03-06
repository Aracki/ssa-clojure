(ns ssa-raca.controller.controller
  (:require
    [clostache.parser :as clostache]
    [ssa-raca.model.customers :as customers-model]
    [ssa-raca.model.employees :as employees-model]
    ))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
           (str "views/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))


(defn index []
  (render-template "index" {}))

(defn customers[]
  (render-template "customers" {:customers (customers-model/allCustomers)}))

(defn updatingEmployees [id]
  (render-template "updateEmployee" {:employees (employees-model/get id)
                                     :offices (employees-model/allOffices)
                                     }))

(defn updatingCustomers [id]
  (render-template "updateCustomer" {:customers (customers-model/get id)
                                     :employees (employees-model/allEmployees)
                                     }))


(defn employees []
  (render-template "employees" {:employees (employees-model/allEmployees)
                                :offices (employees-model/allOffices)
                                }))