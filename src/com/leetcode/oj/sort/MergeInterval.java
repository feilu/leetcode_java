package com.leetcode.oj.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example,
 * 
 * Given [1,3],[2,6],[8,10],[15,18],
 * 
 * return [1,6],[8,10],[15,18].
 * 
 * @author flu
 * 
 */
public class MergeInterval {

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0 || intervals.size() == 1)
            return intervals;
        
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        List<Interval> res = new ArrayList<>();
        Interval prev = intervals.get(0);

        for (Interval curr : intervals) {
            if (curr.start <= prev.end) {
                // start merging.
                prev = new Interval(prev.start, Math.max(prev.end, curr.end));
            } else {
                res.add(prev);
                prev = curr;
            }
        }

        res.add(prev);

        return res;
    }

    public static void main(String[] args) {
        List<Interval> a = new ArrayList<>();
        a.add(new Interval(1, 4));
        a.add(new Interval(4, 5));

        System.out.println(new MergeInterval().merge(a));
    }

}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}