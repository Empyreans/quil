(ns quil.snippets.environment
  (:require #?(:clj [quil.snippets.macro :refer [defsnippet]])
            [quil.core :as q :include-macros true]
            quil.snippets.all-snippets-internal)
  #?(:cljs
     (:use-macros [quil.snippets.macro :only [defsnippet]])))

(defsnippet current-frame-rate-target-frame-rate
  ["current-frame-rate" "target-frame-rate"]
  {}

  (q/background 255)
  (q/fill 0)
  (q/text (str "(q/current-frame-rate) = " (q/current-frame-rate)) 10 20)
  (q/text (str "(q/target-frame-rate) = " (q/target-frame-rate)) 10 40))

(defsnippet current-graphics
  "current-graphics"
  {}

  (q/background 255)
  (q/fill 0 0 255)
  (.rect (q/current-graphics) 0 0 100 100)

  (let [gr (q/create-graphics 100 100)]
    (q/with-graphics gr
      (.fill (q/current-graphics) 255 255 0)
      (.ellipse (q/current-graphics) 50 50 100 100))
    (q/image gr 70 70)))

(defsnippet cursor
  "cursor"
  {}

  (q/no-cursor)
  (q/cursor)

  (doseq [type [:arrow :cross :hand :move :text :wait]]
    (q/cursor type)))

#?(:clj
   (defsnippet cursor-image
     "cursor-image"
     {:setup (q/set-state! :image (q/request-image "test/html/cursor.jpg"))}

     (if (zero? (.-width (q/state :image)))
       (q/text "Loading" 10 10)
       (do
         (q/cursor-image (q/state :image))
         (q/cursor-image (q/state :image) 16 16)
         (q/image (q/state :image) 0 0)))))

(defsnippet focused
  "focused"
  {}

  (q/background 255)
  (q/fill 0)
  (q/text (str (q/focused)) 10 20))

(defsnippet frame-count
  "frame-count"
  {}

  (q/background 255)
  (q/fill 0)
  (q/text (str (q/frame-count)) 10 20))

(defsnippet frame-rate
  "frame-rate"
  {}

  (q/background 255)
  (q/fill 0)
  (q/text (str (q/target-frame-rate)) 10 20)

  (q/frame-rate (inc (rand-int 5))))

(defsnippet height-width
  ["height" "width"]
  {}

  (q/background 255)
  (q/fill 0)
  (q/text (str (q/width) "x" (q/height)) 10 20))

(defsnippet no-cursor
  "no-cursor"
  {}

  (q/no-cursor))

#?(:clj
   (defsnippet screen-height-screen-width
     ["screen-width" "screen-height"]
     {}

     (q/background 255)
     (q/fill 0)
     (let [w (q/screen-width)
           h (q/screen-height)]
       (q/text (str w "x" h) 10 20))))

(defsnippet display-density
  "display-density"
  {}

  (q/background 255)
  (q/fill 0)
  (q/text-num (q/display-density) 10 20))

(defsnippet pixel-density
  "pixel-density"
  {:settings #(q/pixel-density 1)}

  (q/background 255)
  (q/fill 0)
  (q/ellipse 102 102 200 200)
  (q/triangle 200 200 400 300 300 400))
