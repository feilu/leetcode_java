package com.leetcode.oj.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class Skyline {

    static class Rect {
        public int lft;
        public int rgt;
        public int height;
        
        public Rect(int l, int r, int h) {
            lft = l;
            rgt = r;
            height = h;
        }
    }
    
    static class Point {
        public boolean start;
        public int pos;
        public int height;
        
        public Point(boolean s, int p, int h) {
            start = s;
            pos = p;
            height = h;
        }
    }
    
    static class PointComparator implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            return o1.height - o2.height;
        }
    }
    
    static void printSkyLine(Rect[] rects) {
        if (null == rects)
            return;
        
        Map<Point, Point> map = new HashMap<Point, Point>();
        List<Point> points = new ArrayList<Point>();
        for (Rect rect : rects)
        {
            Point s = new Point(true, rect.lft, rect.height);
            Point e = new Point(false, rect.rgt, rect.height);
            points.add(s);
            points.add(e);
            map.put(e, s);
        }
        
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.pos - o2.pos;
            }
        });
        
        SortedSet<Point> tree = new TreeSet<Point>(new PointComparator());
        for (Point pt : points) {
            int curHeight = tree.isEmpty() ? 0 : tree.last().height;
            if (pt.start)
                tree.add(pt);
            else
                tree.remove(map.get(pt));
            
            if (tree.isEmpty())
                System.out.println(pt.pos + " ===> " + curHeight);
            else if (tree.last().height != curHeight)
                System.out.println(pt.pos + " ===> " + tree.last().height);
        }
    }
    
    public static void main(String[] strs) {
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