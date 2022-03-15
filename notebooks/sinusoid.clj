^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns notebooks.sinusoid
  (:require [nextjournal.clerk :as clerk]
            [sinusoid.ratio :refer [ratio]]))

;; ## [Solve sinusoidal equations](https://www.khanacademy.org/math/trigonometry/trig-equations-and-identities/xfefa5515:sinusoidal-equations/e/solve-advanced-sinusoidal-equations)

;; Find the solutions to the equation. Your answer should be in degrees. Assume $n$ is any integer.

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

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (str trig-fn "^{-1}\\left(" (ratio (/ (- equals x-shift) amplitude)) "\\right)="
                  (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) "^\\circ"))

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (str
  "\\begin{aligned}5x&=64.62^\\circ+n\\cdot360^\\circ \\\\\\\\"
  "x&=\\dfrac{64.62^\\circ+n\\cdot360^\\circ}{5}=12.92^\\circ+n\\cdot72^\\circ\\end{aligned}"))

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex 
 (str
  "\\begin{aligned}5x&=-64.62^\\circ+n\\cdot360^\\circ \\\\\\\\"
  "x&=\\dfrac{-64.62^\\circ+n\\cdot360^\\circ}{5}=-12.92^\\circ+n\\cdot72^\\circ\\end{aligned}"))