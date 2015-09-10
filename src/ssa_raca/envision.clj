(ns ssa-raca.envision
  (:require [clojurewerkz.envision.core         :as envision]
            [clojurewerkz.envision.chart-config :as cfg]

            (envision/render
              [(envision/histogram 10 (take 100 (distribution/normal-distribution 5 10))
                                   {:tick-format "s"})

               (envision/linear-regression
                 (flatten (for [i (range 0 20)]
                            [{:year (+ 2000 i)
                              :income (+ 10 i (rand-int 10))
                              :series "series-1"}
                             {:year (+ 2000 i)
                              :income (+ 10 i (rand-int 20))
                              :series "series-2"}]
                            ))
                 :year
                 :income
                 [:year :income :series])
               (cfg/make-chart-config
                 {:id            "line"
                  :headline      "Line Chart"
                  :x             "year"
                  :y             "income"
                  :x-config      {:order-rule "year"}
                  :series-type   "line"
                  :data          (flatten (for [i (range 0 20)]
                                            [{:year (+ 2000 i)
                                              :income (+ 10 i (rand-int 10))
                                              :series "series-1"}
                                             {:year (+ 2000 i)
                                              :income (+ 10 i (rand-int 20))
                                              :series "series-2"}]
                                            ))
                  :series        "series"
                  :interpolation :cardinal
                  })
               (cfg/make-chart-config
                 {:id            "area"
                  :headline      "Area Chart"
                  :x             "year"
                  :y             "income"
                  :x-config      {:order-rule "year"}
                  :series-type   "area"
                  :data          (into [] (for [i (range 0 20)] {:year (+ 2000 i) :income (+ 10 i (rand-int 10))}))
                  :interpolation :cardinal
                  })
               ])))