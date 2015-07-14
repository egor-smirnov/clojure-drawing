(ns drawing.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :angle 0})

(defn update [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (+ (:angle state) 0.1)})

(defn draw [state]
  ; Clear the sketch by filling it with light-grey color.
  (q/background 240)
  ; Set circle color.
  (q/fill (:color state) 255 255)
  ; Calculate x and y coordinates of the circle.
  (let [angle (:angle state)
        x (* 150 (q/cos angle))
        y (* 150 (q/sin angle))]
    ; Move origin point to the center of the sketch.
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ; Draw the circle.
      (q/ellipse x y 100 100))))

(q/defsketch circles 
  :title "You spin my circle right round"
  :size [500 500]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update is called on each iteration before draw is called.
  ; It updates sketch state.
  :update update
  :draw draw
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])


(defn total-with-tip
  "Given subtotal, return total after tax and tip."
  [subtotal tip-pct]
  (* 1.08 subtotal (+ 1 tip-pct)))

(defn share-per-person
  "Given subtotal of bill, return total after tax."
  [subtotal tip-pct number-of-people]
  (/ (total-with-tip subtotal tip-pct) number-of-people))


(defn average
  "Takes a vector of bill amounts and returns the average of those amounts"
  [bills]
  (* (/ (reduce + bills) (count bills)) 1.0))

(def my-hometown "Nizhniy Novgorod")

(defn format-name
  "Write a function called format-name that takes two arguments, first-name and last-name."
  [first-name last-name]
  (str last-name ", " first-name))

(def me {:first "Olga" :last "Smirnova"})

(def classmates [{:first "Margaret" :last "Atwood" :town "Moscow"}
            {:first "Doris" :last "Lessing" :town "Berlin"}
            {:first "Ursula" :last "Le Guin" :town "New York"}
            {:first "Alice" :last "Munro"  :town "London"}])

(conj classmates me)

(get-names(conj classmates me))

(defn return-name
  [person]
  (str (get person :first) " " (:last person)))

;;(defn get-names
  ;;[persons]
  ;;(map return-name persons))

(defn get-names
  [persons]
  (vec (map (fn [person] (str (get person :first) " " (:last person))) persons)))

(get-names [{:first "Margaret" :last "Atwood"}
            {:first "Doris" :last "Lessing"}
            {:first "Ursula" :last "Le Guin"}
            {:first "Alice" :last "Munro"}])

(defn format-name
  [person]
  (if (get person :middle)
    (str (get person :first) " " (get person :middle) " " (get person :last))
    (str (get person :first) " " (get person :last))
    ))

(format-name {:first "Margaret" :last "Atwood"})
;=> "Margaret Atwood"

(format-name {:first "Ursula" :last "Le Guin" :middle "K."})
;=> "Ursula K. Le Guin"

