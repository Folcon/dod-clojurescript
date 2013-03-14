(ns dod.core
	(:require-macros [hiccups.core :as hiccups])
	(:require [hiccups.runtime :as hiccupsrt]))

(.write js/document "Hello, ClojureScript! ")

(def num-players 2)
(def max-dice 3)
(def board-size 2)
(def board-hexnum (* board-size board-size))


(defn gen-board [] 
	(vec (for [n (range board-hexnum)]
	 	[(rand-int num-players) (inc (rand-int max-dice))])))

(.write js/document (gen-board) " ")


(defn player-letter [n]
	(get {0 "a" 1 "b"} n))

(.write js/document (player-letter 0) "<br />")

;; (defn draw-board [board]
;;	(clojure.string/join "<br/>"
;;		(for [y (range board-size)]
;;			(repeat (- board-size y) "&nbsp;")
;;			(for [x (range board-size)]
;;				(let [hex (nth board (+ x (* board-size y)))]
;;					(format "%s-%s" (player-letter (first hex))
;;					(second hex)))))))

(defn pretty-hex [hex]
	(format "%s-%s" (player-letter (first hex)) (second hex)))

(defn draw-board [pretty]
	(clojure.string/join "<br />" (map #(map pretty-hex %) (partition 2 (gen-board)))))

(.write js/document (draw-board (gen-board)))
