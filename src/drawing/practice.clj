(ns drawing.practice
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def flake (ref nil))        ;; reference to snowflake image
(def background (ref nil))   ;; reference to blue background image

(def snowflakes-x [100 400 700])

(defn update-x
  [x swing]
  (let [start (- x swing)
        end (+ x swing)
        new-x (+ start (rand-int (- end start)))]
    (cond
     (> 0 new-x) (q/width)
     (< (q/width) new-x) 0
     :else new-x )))

(defn update-y
  [y speed]
  (if (>= y (q/height))
    0
    (+ y speed)))

(defn setup []
  ;; loading two images
  (dosync
   (ref-set flake (q/load-image "images/white_flake.png"))
   (ref-set background (q/load-image "images/blue_background.png")))
  (q/smooth)
  (q/frame-rate 30)
  [{:x 100 :y 10 :speed 1 :swing 10}
   {:x 400 :y 300 :speed 5 :swing 5}
   {:x 700 :y 100 :speed 3 :swing 8}
   {:x 550 :y 300 :speed 1 :swing 15}])


(defn draw [state]
  ;; drawing blue background and a snowflake on it
  (q/background-image @background)
  (doseq [p state] 
    (q/image @flake (:x p) (:y p))))

;(defn update [state]
 ; (for [p state]
  ;(if (> (:y p) (q/height))
   ; (assoc p :y 0)
   ; (assoc p :y (+ (:speed p) (:y p))))))

(defn update [state]
  (for [p state]
    (merge p {:x (update-x (:x p) (:swing p)) :y (update-y (:y p) (:speed p))})))

(q/defsketch practice
  :title "Clara's Quil practice"
  :size [1000 1000]
  :setup setup
  :draw draw
  :update update
  :middleware [m/fun-mode])