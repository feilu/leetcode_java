package com.leetcode.oj.hash;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * 
 * @author flu
 * 
 */
public class MaxPointsOnLine {

    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0)
            return 0;

        if (points.length == 1)
            return 1;

        int max = 0;
        Map<BigDecimal, Integer> slopeMap;

        for (Point p : points) {
            // initialize
            slopeMap = new HashMap<>();
            int localMax = 0;

            // count number of same points of p, these will be added to localMax in the end.
            int samePoint = 1;

            // compute slop for all other points with p, if any slop is the same, they belong to the same line.
            for (Point q : points) {
                if (p == q)
                    continue;

                if (p.x == q.x && p.y == q.y) {
                    samePoint++;
                    continue;
                }

                BigDecimal slope = getSlope(p, q);
                int c = slopeMap.containsKey(slope) ? slopeMap.get(slope) + 1 : 1;
                slopeMap.put(slope, c);
                localMax = localMax < c ? c : localMax;
            }
            localMax += samePoint;

            max = max < localMax ? localMax : max;
        }
        return max;
    }

    private BigDecimal getSlope(Point p, Point q) {
        if (p.x == q.x)
            return BigDecimal.valueOf(Double.MAX_VALUE); // special value for vertical lines.
        else
            return BigDecimal.valueOf((double) (p.y - q.y) / (p.x - q.x));
    }

    public static void main(String[] args) {
        Point p1 = new Point(84, 250);
        Point p2 = new Point(0, 0);
        Point p22 = new Point(1, 0);
        Point p3 = new Point(0, -70);
        Point p4 = new Point(0, -70);
        Point p5 = new Point(1, -1);
        Point p6 = new Point(21, 10);
        Point p7 = new Point(42, 90);
        Point p8 = new Point(-42, -230);
        
        /*Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Point p3 = new Point(1, 0);*/
        

        Point[] p = new Point[] { p1, p2, p22, p3 };

        System.out.println(new MaxPointsOnLine().maxPoints(p));
    }

}

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}
