(ns ssa_raca.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.basic-authentication :refer :all]
            [ring.util.response :as resp]
            [ssa-raca.controller.controller :as controller]
            [ssa-raca.model.employees :as employee-model]
            [ssa-raca.model.customers :as customers-model]
            )
  )

(defroutes public-routes
           (GET "/" [] (controller/index))
           (route/resources "/")
           (GET "/index" [] (controller/index))
           (route/resources "/")
           (GET "/customers" [] (controller/customers))
           (route/resources "/")
           (GET "/employees" [] (controller/employees))
           (route/resources "/")

           (GET "/model/employees/:id/remove" [id]
             (do (employee-model/removeE id)
                 (resp/redirect "/employees")))

           (GET "/model/customers/:id/remove" [id]
               (do (customers-model/removeC id)
                 (resp/redirect "/customers")))

                 (POST "/model/employees/insert" [& params]
                   (do (employee-model/insertE params)
                     (resp/redirect "/employees")))

           (GET "/model/employees/:id/update" [id] (controller/updatingEmployees id))

           (POST "/model/employees/:employeeNumber/update" [& params]
             (do (employee-model/update (:employeeNumber params) params)
                 (resp/redirect "/employees")))


           (POST "/model/customers/insert" [& params]
             (do (customers-model/insertC params)
                 (resp/redirect "/customers")))

           (GET "/model/customers/:id/update" [id] (controller/updatingCustomers id))

           (POST "/model/customers/:customerNumber/update" [& params]
             (do (customers-model/update (:customerNumber params) params)
                 (resp/redirect "/customers")))

           )

(defroutes app-routes
  public-routes
           (route/not-found "404 Not Found")
           )
(def app
  (handler/site app-routes)
  )
