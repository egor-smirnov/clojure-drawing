(ns drawing.bridge
   (:require [quil.core :as q]))

(defn setup []

  (q/frame-rate 30)
  (q/color-mode :rgb)
  (q/stroke 127 0 0))


(defn draw []
  (let [mouse-x (q/mouse-x)
        mouse-y (q/mouse-y)]
    (q/stroke (/ mouse-x 2.0) 0 0)
    (q/line 100 100 mouse-x mouse-y)
    (q/stroke 255 0 0)
    (q/line 200 0 mouse-x mouse-y)
    (q/stroke 0 0 0)
    (q/line 0 200 mouse-x mouse-y)
    (q/stroke 0 (/ mouse-y 2.0) 0)
    (q/line 200 200 mouse-x mouse-y)))

(q/defsketch hello-lines
  :title "You can see lines. Hi from Clojure Bridge!"
  :size [500 500]
  :setup setup
  :draw draw )