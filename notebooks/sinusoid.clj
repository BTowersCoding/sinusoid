^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns notebooks.sinusoid
  (:require [nextjournal.clerk :as clerk]
            [sinusoid.math :as math :refer [pi deg round]]))

;; ## Solve sinusoidal equations

;; Adapted from: https://www.khanacademy.org/math/trigonometry/trig-equations-and-identities/xfefa5515:sinusoidal-equations/e/solve-advanced-sinusoidal-equations

;; Derive one or more expressions that together represent all solutions to the equation. 
;; Assume $n$ is any integer.

(def mode :deg)

^{:nextjournal.clerk/visibility #{:hide}}
(case mode
  :deg (clerk/html "Your answer should be in degrees rounded to the nearest hundredth.")
  :rad (clerk/html "Your answer should be in radians."))

(def sinusoid
  {:amplitude 8, :trig-fn "\\cos", :period 12, :x-shift 4, :val -4})

^{:nextjournal.clerk/visibility #{:hide}}
(let [{:keys [amplitude trig-fn period x-shift val]} sinusoid]
  (clerk/tex (str amplitude trig-fn "(" period "x)" 
                 (when (pos? x-shift) "+")
                  x-shift "=" val)))

;; Trig identities

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex 
 (case mode
   :deg (case (:trig-fn sinusoid)
          "\\cos" "\\cos(\\theta)=\\cos(-\\theta)"
          "\\sin" "\\sin(\\theta)=\\sin(180\\degree-\\theta)")
   :rad (case (:trig-fn sinusoid)
          "\\cos" "\\cos(\\theta)=\\cos(-\\theta)"
          "\\sin" "\\sin(\\theta)=\\sin(\\pi-\\theta)")))

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (case mode
   :deg (case (:trig-fn sinusoid)
          "\\cos" "\\cos(\\theta)=\\cos(\\theta+360\\degree)"
          "\\sin" "\\sin(\\theta)=\\sin(\\theta+360\\degree)")
   :rad (case (:trig-fn sinusoid)
          "\\cos" "\\cos(\\theta)=\\cos(\\theta+2\\pi)"
          "\\sin" "\\sin(\\theta)=\\sin(\\theta+2\\pi)")))

^{:nextjournal.clerk/visibility #{:hide}}
(defn inverse-trig-fn [f n]
  (case f
    "\\cos" (Math/acos n)
    "\\sin" (Math/asin n)))

;; ### Isolate the sinusoidal function

^{:nextjournal.clerk/visibility #{:hide}}
(let [{:keys [amplitude trig-fn period x-shift val]} sinusoid]
  (clerk/tex
   (str "\\begin{aligned}"
        amplitude trig-fn "(" period "x)"
       (when (pos? x-shift) "+")
        x-shift "&=" val "\\\\\\\\ "
        amplitude trig-fn "(" period "x)&=" (- val x-shift) "\\\\\\\\ "
        trig-fn "(" period "x)&=" (math/ratio (/ (- val x-shift) amplitude))
        "\\end{aligned}")))

;; Use the inverse trig function to find one value of `(* period x)`

^{:nextjournal.clerk/visibility #{:hide}}
(let [{:keys [amplitude trig-fn x-shift val]} sinusoid
      v (inverse-trig-fn trig-fn (/ (- val x-shift) amplitude))]
  (clerk/tex
   (str trig-fn "^{-1}\\left(" 
        (math/ratio (/ (- val x-shift) amplitude)) "\\right)="
        (case mode
          :deg (str (deg v) "^\\circ")
          :rad (or (last (first (filter (fn [[d _]]
                                          (> 0.000001 (Math/abs (- d v))))
                                        math/fractions-of-pi)))
                   (round v 1000))))))

^{:nextjournal.clerk/visibility #{:hide}}
(let [{:keys [amplitude x-shift val]} sinusoid]
  (when (= -1  (/ (- val x-shift) amplitude))
   (clerk/html "Since -1 is a trough, it is the only solution in this interval.")))

^{:nextjournal.clerk/visibility #{:hide}}
(let [{:keys [amplitude x-shift val]} sinusoid]
  (when (= 1  (/ (- val x-shift) amplitude))
    (clerk/html "Since 1 is a peak, it is the only solution in this interval.")))

^{:nextjournal.clerk/visibility #{:hide}}
(let [{:keys [amplitude x-shift val]} sinusoid]
  (when-not (or (= -1  (/ (- val x-shift) amplitude))
                (= 1  (/ (- val x-shift) amplitude)))
    (clerk/html "Now we can use the first identity to find the second solution within the interval:")))

^{:nextjournal.clerk/visibility #{:hide}}
(let [{:keys [amplitude trig-fn x-shift val]} sinusoid
      v (inverse-trig-fn trig-fn (/ (- val x-shift) amplitude))]
  (when-not (or (= -1  (/ (- val x-shift) amplitude))
                (= 1  (/ (- val x-shift) amplitude)))
    (clerk/tex
     (case mode 
       :deg (case trig-fn
              "\\cos" (str "-" (deg v) " ^\\circ")
              "\\sin" (str "180^\\circ" 
                           (if (neg? (deg v)) "+" "-") 
                           (Math/abs (deg v)) 
                           " ^\\circ=" (- 180 (deg v)) "^\\circ"))
       :rad (case trig-fn
              "\\cos" (str "-" (or (last (first (filter (fn [[d _]]
                                                          (> 0.000001 (Math/abs (- d v))))
                                                        math/fractions-of-pi)))
                                   (round v 1000)))
              "\\sin" (str "\\pi-" (round v 1000) "=" 
                           (round (- pi v) 1000)))))))

;; ### Finding all solutions

^{:nextjournal.clerk/visibility #{:hide}}
(let [{:keys [amplitude x-shift val]} sinusoid]
  (when-not (or (= -1  (/ (- val x-shift) amplitude))
                (= 1  (/ (- val x-shift) amplitude)))
    (clerk/html "Using the second identity, we can find all the solutions 
                 to our equation from the two angles we found above. 
                 The first solution gives us the following:")))

^{:nextjournal.clerk/visibility #{:hide}}
(defn solution1-deg [m]
  (let [{:keys [amplitude trig-fn period x-shift val]} m
        v (inverse-trig-fn trig-fn (/ (- val x-shift) amplitude))]
    (str "\\begin{aligned}" period "x&="
         (deg v) "^\\circ+n\\cdot360^\\circ \\\\\\\\x&=\\dfrac{"
         (deg v) "^\\circ+n\\cdot360^\\circ}{" period "}="
         (round (/ (deg v) period) 100)
         "^\\circ+n\\cdot" (/ 360 period) "^\\circ"
         "\\end{aligned}")))

(comment 
  (solution1-deg {:amplitude 8, :trig-fn "\\cos", :period 12, :x-shift 4, :val -4})
  )


^{:nextjournal.clerk/visibility #{:hide}}
(defn solution1-rad [m]
  (let
   [{:keys [amplitude trig-fn period x-shift val]} m
    v (inverse-trig-fn trig-fn (/ (- val x-shift) amplitude))]
    (str "\\begin{aligned}" period "x&="
         (or (last (first (filter (fn [[d _]]
                                    (> 0.000001 (Math/abs (- d v))))
                                  math/fractions-of-pi)))
             (round v 1000))
         "+n\\cdot2\\pi \\\\\\\\x&=\\dfrac{"
         (or (last (first (filter (fn [[d _]]
                                    (> 0.000001 (Math/abs (- d v))))
                                  math/fractions-of-pi)))
             (round v 1000))
         "+n\\cdot2\\pi}{" period "}="
         (when (neg? (/ v period)) "-")
         (or (last (first (filter (fn [[d _]]
                                    (> 0.000001 (Math/abs (- d (/ v period)))))
                                  math/fractions-of-pi)))
             (round (/ v period) 1000))
         "+n\\cdot\\dfrac{2\\pi}{" period "}"
         "\\end{aligned}")))

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (case mode
   :deg (solution1-deg sinusoid)
   :rad (solution1-rad sinusoid)))

^{:nextjournal.clerk/visibility #{:hide}}
(let [{:keys [amplitude x-shift val]} sinusoid]
  (when-not (or (= -1  (/ (- val x-shift) amplitude))
                (= 1  (/ (- val x-shift) amplitude)))
    (clerk/html "Similarly, the second solution gives us the following:")))

^{:nextjournal.clerk/visibility #{:hide}}
(defn solution2-sin-deg [m]
  (let [{:keys [amplitude trig-fn period x-shift val]} m
        v (inverse-trig-fn trig-fn (/ (- val x-shift) amplitude))]
    (when-not (or (= -1  (/ (- val x-shift) amplitude))
                  (= 1  (/ (- val x-shift) amplitude)))
       (str "\\begin{aligned}" period "x&="
            (- 180 (deg v))
            "^\\circ+n\\cdot360^\\circ \\\\\\\\x&=\\dfrac{"
            (- 180 (deg v)) "^\\circ+n\\cdot360^\\circ}{" period "}="
            (round (/ (- 180 (deg v)) period) 100)
            "^\\circ+n\\cdot" (/ 360 period) "^\\circ"
            "\\end{aligned}"))))

  ^{:nextjournal.clerk/visibility #{:hide}}
(defn solution2-sin-rad [m]
  (let [{:keys [amplitude trig-fn period x-shift val]} m
        v (inverse-trig-fn trig-fn (/ (- val x-shift) amplitude))]
    (when-not (or (= -1  (/ (- val x-shift) amplitude))
                  (= 1  (/ (- val x-shift) amplitude)))
       (str "\\begin{aligned}" period "x&="
            (round (- pi v) 1000) "+n\\cdot2\\pi \\\\\\\\x&=\\dfrac{"
            (round (- pi v) 1000) "+n\\cdot2\\pi}{" period "}="
            (or (last (first (filter (fn [[d _]]
                                       (> 0.000001 (Math/abs (- d (/ (- pi v) period)))))
                                     math/fractions-of-pi)))
                (round (/ (- pi v) period) 1000))
            "+n\\cdot\\dfrac{2\\pi}{" period "}"
            "\\end{aligned}"))))

^{:nextjournal.clerk/visibility #{:hide}}
(defn solution2-cos-deg [m]
  (let [{:keys [amplitude trig-fn period x-shift val]} m
        v (inverse-trig-fn trig-fn (/ (- val x-shift) amplitude))]
    (when-not (or (= -1  (/ (- val x-shift) amplitude))
                  (= 1  (/ (- val x-shift) amplitude)))
       (str "\\begin{aligned}" period "x&="
            "-" (deg v) "^\\circ+n\\cdot360^\\circ \\\\\\\\x&=\\dfrac{"
            "-" (deg v) "^\\circ+n\\cdot360^\\circ}{" period "}="
            "-" (round (/ (deg v) period) 100)
            "^\\circ+n\\cdot" (/ 360 period) "^\\circ"
            "\\end{aligned}"))))

^{:nextjournal.clerk/visibility #{:hide}}
(defn solution2-cos-rad [m]
  (let [{:keys [amplitude trig-fn period x-shift val]} m
        v (inverse-trig-fn trig-fn (/ (- val x-shift) amplitude))]
    (when-not (or (= -1  (/ (- val x-shift) amplitude))
                  (= 1  (/ (- val x-shift) amplitude)))
       (str "\\begin{aligned}" period "x&="
            "-" (or (last (first (filter (fn [[d _]]
                                           (> 0.000001 (Math/abs (- d v))))
                                         math/fractions-of-pi)))
                    (round v 1000))
            "+n\\cdot2\\pi \\\\\\\\x&=\\dfrac{"
            "-" (or (last (first (filter (fn [[d _]]
                                           (> 0.000001 (Math/abs (- d v))))
                                         math/fractions-of-pi)))
                    (round v 1000))
            "+n\\cdot2\\pi}{" period "}="
            "-"
            (or (last (first (filter (fn [[d _]]
                                       (> 0.000001 (Math/abs (- d (/ v period)))))
                                     math/fractions-of-pi)))
                (round (/ v period) 1000))
            "+n\\cdot\\dfrac{2\\pi}{" period "}"
            "\\end{aligned}"))))

^{:nextjournal.clerk/visibility #{:hide}}
(clerk/tex
 (case (:trig-fn sinusoid)
   "\\cos"
   (case mode
     :deg (solution2-cos-deg sinusoid)
     :rad (solution2-cos-rad sinusoid))
   "\\sin"
   (case mode
     :deg (solution2-sin-deg sinusoid)
     :rad (solution2-sin-rad sinusoid))))
