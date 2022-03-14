(ns sinusoid
  (:require [nextjournal.clerk :as clerk]))

(clerk/serve! {:browse? true})

(clerk/show! "notebooks/sinusoid.clj")

(clerk/serve! {:watch-paths ["notebooks" "src"]})