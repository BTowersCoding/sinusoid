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

;; $5\sin(3x)-1=3$

(def pi Math/PI)

(defn deg [rad]
  (/ (Math/round (* 100 (/ rad (/ pi 180)))) 100.0))

(def amplitude 5)
(def trig-fn "\\sin")

(def period 3)
(def x-shift -1)
(def equals 3)

;; Trig identities
^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex 
 (case trig-fn
   "\\cos" "\\cos(\\theta)=\\cos(-\\theta)"
   "\\sin" "\\sin(\\theta)=\\sin(180\\degree-\\theta)"))

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (case trig-fn
   "\\cos" "\\cos(\\theta)=\\cos(\\theta+360\\degree)"
   "\\sin" "\\sin(\\theta)=\\sin(\\theta+360\\degree)"))

(defn inverse-trig-fn [n]
  (case trig-fn
    "\\cos" (Math/acos n)
    "\\sin" (Math/asin n)))

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
(clerk/tex 
 (case trig-fn
   "\\cos" (str "-" (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) " ^\\circ")
   "\\sin" (str "180^\\circ-" (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) " ^\\circ="
                (- 180 (deg (inverse-trig-fn (/ (- equals x-shift) amplitude)))))))

;; Using the second identity, we can find all the solutions to our equation from the two angles we found above. The first solution gives us the following:

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (str "\\begin{aligned}" period "x&="
  (case mode
    :deg (str (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) "^\\circ"))
  "+n\\cdot360^\\circ \\\\\\\\"
  "x&=\\dfrac{" 
  (str (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) "^\\circ")
  "+n\\cdot360^\\circ}{" period "}=" 
  (/ (Math/round (* 100 (/ (deg (inverse-trig-fn (/ (- equals x-shift) amplitude)))
                           period))) 100.0)
  "^\\circ+n\\cdot"
      (/ 360 period)
      "^\\circ\\end{aligned}"))

;; Similarly, the second solution gives us the following:

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (str "\\begin{aligned}" period "x&="
      (case trig-fn 
        "\\cos"
        (case mode
          :deg (str (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) "^\\circ"))
        "\\sin"
        (case mode
          :deg (str (- 180 (deg (inverse-trig-fn (/ (- equals x-shift) amplitude)))) "^\\circ")))
  "+n\\cdot360^\\circ \\\\\\\\"
  "x&=\\dfrac{"
       (case trig-fn 
        "\\cos"
  (str (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))) "^\\circ")
         "\\sin"
          (str (- 180 (deg (inverse-trig-fn (/ (- equals x-shift) amplitude)))) "^\\circ"))
  "+n\\cdot360^\\circ}{" period "}="
      (case mode
        :deg
        (/ (- 180 (deg (inverse-trig-fn (/ (- equals x-shift) amplitude))))
                          period))
  "^\\circ+n\\cdot"
      (/ 360 period)
      "^\\circ\\end{aligned}"))
