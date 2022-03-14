^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns notebooks.sinusoid
  (:require [nextjournal.clerk :as clerk]))

(let [amplitude 7
      trig-fn "\\cos"
      period 5
      x-shift 9]
  (clerk/tex (str amplitude trig-fn "(" period "x)+" x-shift "=12")))

;; $\begin{aligned}7\cos(5x)+9&=12\\\\ 7\cos(5x)&=3\\\\ \cos(5x)&=\dfrac{3}{7}\end{aligned}$​​

^{:nextjournal.clerk/visibility #{:hide}}
(let [period 7]
  (clerk/tex (str "\\cos^{-1}\\left(\\dfrac{3}{7}\\right)=64.62^\\circ")))
