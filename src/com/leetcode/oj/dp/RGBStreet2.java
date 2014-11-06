package com.leetcode.oj.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;

public class RGBStreet2 {

    enum Color {
        R, G, B, Y;
    }

    public static double estimateCost(Map<Color, List<Double>> cost) {
        // validate each value has the same size of list, size must > 0.
        // validate each number in each list is valid (not null, >0).

        Set<Color> colors = cost.keySet();

        Map<Color, List<Double>> totalCost = new HashMap<>();

        // init
        int n = cost.values().iterator().next().size();
        for (Color c : colors) {
            List<Double> list = new ArrayList<>(n);
            list.add(cost.get(c).get(0));
            totalCost.put(c, list);
        }

        for (int i = 1; i < n; i++) {
            for (Color c : colors) {
                totalCost.get(c).add(cost.get(c).get(i) + getMinCost(totalCost, c, colors, i - 1));
            }
        }

        double minCost = Double.MAX_VALUE;
        for (Color c : colors) {
            minCost = Math.min(minCost, totalCost.get(c).get(n - 1));
        }
        return minCost;
    }

    // find min cost with color diff from designated color
    private static Double getMinCost(Map<Color, List<Double>> cost, Color color, Set<Color> colors, int i) {
        double minCost = Double.MAX_VALUE;
        for (Color c : colors) {
            // skip the same color
            if (c == color)
                continue;

            minCost = Math.min(minCost, cost.get(c).get(i));
        }
        return minCost;
    }

    public static void main(String[] args) {
        Map<Color, List<Double>> c = new HashMap<>();
        c.put(Color.R, Lists.newArrayList(10d, 15d, 20d));
        c.put(Color.G, Lists.newArrayList(20d, 15d, 20d));
        c.put(Color.B, Lists.newArrayList(50d, 1d, 60d));
        c.put(Color.B, Lists.newArrayList(9d, 23d, 7.5d));

        System.out.println(estimateCost(c));
    }
}
