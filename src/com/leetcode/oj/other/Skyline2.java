package com.leetcode.oj.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Skyline2 {

    public static void printSkyLine(Rect[] input) {
        Map<Point, Point> map = new HashMap<>();
        List<Point> list = new ArrayList<>();

        for (Rect rect : input) {
            Point start = new Point(rect.left, rect.height, true);
            Point end = new Point(rect.right, rect.height, false);
            list.add(start);
            list.add(end);
            map.put(end, start);
        }

        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.pos - o2.pos;
            }
        });

        TreeSet<Point> treeSet = new TreeSet<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.height - o2.height;
            }
        });
        
        for (Point p : list) {
            int currentHeight = treeSet.isEmpty() ? 0 : treeSet.last().height;

            if (p.isStart)
                treeSet.add(p);
            else
                treeSet.remove(map.get(p));

            if (treeSet.isEmpty())
                System.out.println(p.pos + " ====> " + currentHeight);
            else if (currentHeight != treeSet.last().height)
                System.out.println(p.pos + " ====> " + treeSet.last().height);
        }
        
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*Rect[] rects = new Rect[8];
        rects[0] = new Rect(0, 2, 1);
        rects[1] = new Rect(1, 5, 3);
        rects[2] = new Rect(3, 7, 4);
        rects[3] = new Rect(4, 8, 2);
        rects[4] = new Rect(6, 13, 3);
        rects[5] = new Rect(9, 11, 5);
        rects[6] = new Rect(10, 15, 1);
        rects[7] = new Rect(12, 14, 2);*/
        
        Rect[] rects = new Rect[3];
        rects[0] = new Rect(0, 3, 8);
        rects[1] = new Rect(1, 5, 3);
        rects[2] = new Rect(6, 7, 3);
        printSkyLine(rects);

    }
}

class Rect {
    int left, right, height;

    public Rect(int l, int r, int h) {
        left = l;
        right = r;
        height = h;
    }
}

class Point {
    int pos, height;
    boolean isStart = false;

    public Point(int p, int h, boolean s) {
        pos = p;
        height = h;
        isStart = s;
    }
}