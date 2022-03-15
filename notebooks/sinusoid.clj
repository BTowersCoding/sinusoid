^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns notebooks.sinusoid
  (:require [nextjournal.clerk :as clerk]
            [sinusoid.ratio :refer [ratio]]))

;; ## [Solve sinusoidal equations](https://www.khanacademy.org/math/trigonometry/trig-equations-and-identities/xfefa5515:sinusoidal-equations/e/solve-advanced-sinusoidal-equations)

;; Find the solutions to the equation. Assume $n$ is any integer.

(def mode :deg)

^{:nextjournal.clerk/visibility #{:hide}}
(case mode
  :deg (clerk/html "Your answer should be in degrees rounded to the nearest hundredth."))

;; $7\cos(5x)+9=12$

(def pi Math/PI)

(defn deg [rad]
  (/ (Math/round (* 100 (/ rad (/ pi 180)))) 100.0))

(deg (Math/acos (/ 3 7)))

(def amplitude 7)
(def trig-fn "\\cos")

(def period 5)
(def x-shift 9)
(def equals 12)

;; Trig identities
^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex "\\cos(\\theta)=\\cos(-\\theta)")
^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex "\\cos(\\theta)=\\cos(\\theta+360\\degree)")

(defn inverse-trig-fn [n]
  (case trig-fn
   "\\cos" (Math/acos n)))

;; Isolate the sinusoidal function

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (str "\\begin{aligned}"
      amplitude trig-fn "(" period "x)+" x-shift "&=" equals "\\\\\\\\ "
      amplitude trig-fn "(" period "x)&=" (- equals x-shift) "\\\\\\\\ "
      trig-fn "(" period "x)&=" (ratio (/ (- equals x-shift) amplitude))
      "\\end{aligned}"))

;; Use the inverse trig function to find one value of `(* period x)`

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (str trig-fn "^{-1}\\left(" (ratio (/ (- equals x-shift) amplitude)) "\\right)="
                  (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) "^\\circ"))

;; Now we can use the first identity to find the second solution within the interval:

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex (str "-" (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) " ^\\circ")) 

;; Using the second identity, we can find all the solutions to our equation from the two angles we found above. The first solution gives us the following:

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (str
  "\\begin{aligned}"
  period "x&="
  (case mode
    :deg (str (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) "^\\circ"))
  "+n\\cdot360^\\circ \\\\\\\\"
  "x&=\\dfrac{" 
  (str (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) "^\\circ")
  "+n\\cdot360^\\circ}{"
  period
  "}=" 
  (/ (Math/round (* 100 (/ (deg (inverse-trig-fn (/ (- equals x-shift) amplitude)))
                           period))) 
     100.0)
  "^\\circ+n\\cdot72^\\circ\\end{aligned}"))

;; Similarly, the second solution gives us the following:

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (str
  "\\begin{aligned}"
  period "x&=-"
  (case mode
    :deg (str (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) "^\\circ"))
  "+n\\cdot360^\\circ \\\\\\\\"
  "x&=\\dfrac{-"
  (str (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) "^\\circ")
  "+n\\cdot360^\\circ}{"
  period
  "}=-"
  (/ (Math/round (* 100 (/ (deg (inverse-trig-fn (/ (- equals x-shift) amplitude)))
                           period)))
     100.0)
  "^\\circ+n\\cdot72^\\circ\\end{aligned}"))
