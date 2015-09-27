# Projekat za savremene softverske arhitekture

Simple clojure web application for handling data from database. This web app is using basic CRUD operations (Create, Read, Update, Delete). It uses standard mysql jdbc connector for communicating with database which is hosted on localhost.

On the front-end, web app uses Bootstrap 3.3.5. and JQuery 3 libraries.
All public html files are .mustache extension, because the easy way of binding data between controller.clj and html pages providing simple Logic-less template.

Sending data from server for mustache template with Clostache library:


```html
(defn employees []
  (render-template "employees" {:employees (employees-model/allEmployees)
                                :offices (employees-model/allOffices)
                                }))
```

In this way you can use this variables in html pages with scriplets code :
{{employees}} and {{offices}} ...

The app uses Ring and Compojure library for abstracting HTTP requests and response into a simple API.

![Alt text](/img/customers.png?raw=true "Customers table")

![Alt text](/img/employees.png?raw=true "Employees table")

![Alt text](/img/edit.png?raw=true "Edit employee")

![Alt text](/img/insert.png?raw=true "Insert employee")

A Clojure project @FON 2015

## Usage

Use file classicmodels.sql in root folder and import it in mysql database.

Start web application using 'lein ring server' command.

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
