(ns ssa-raca.music.music
  (:use [overtone.core])
  )


(defn proba []
  (demo(sin-osc))
  )


(connect-external-server)
(proba)