package com.leetcode.stack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

public class FlattenMap {
    public static FlattenMap flat;

    public String flattenMapIterative(String dict) {
        // skip the step to validate input, empty/null or invalid string.
        // also does not consider {}
        Deque<String> stack = new ArrayDeque<>();
        Deque<Pair<String, Integer>> result = new ArrayDeque<>();
        int depth = 0;
        for (int i = 0; i < dict.length(); i++) {
            switch (dict.charAt(i)) {
                case '{':
                    depth++;
                case ',':
                    int index = dict.indexOf(':', i);
                    stack.push(dict.substring(i + 1, index));
                    i = index;
                    break;                              
                case '}':
                    if (stack.size() == 0)
                        break;
                    String prefix = stack.pop();
                    Deque<Pair<String, Integer>> tmp = new ArrayDeque<>();
                    while (result.size() > 0) {
                        Pair<String, Integer> p = result.poll();
                        if (p.getRight() < depth)
                            tmp.add(p);
                        else
                            tmp.add(Pair.of(prefix + "." + p.getLeft(), depth - 1));
                    }
                    result = tmp;
                    depth--;
                    break;
                default:
                    int commaIdx = dict.indexOf(',', i) < 0 ? Integer.MAX_VALUE : dict.indexOf(',', i);
                    int closeIdx = dict.indexOf('}', i) < 0 ? Integer.MAX_VALUE : dict.indexOf('}', i);
                    index = Math.min(commaIdx, closeIdx);
                    String str = stack.pop() + ":" + dict.substring(i, index);
                    result.add(Pair.of(str, depth));
                    i = index - 1;
                    break;                    
            }                
        }
        
        StringBuilder sb = new StringBuilder("{");
        for (Pair<String, Integer> s : result)
            sb.append(s.getLeft() + ",");
        sb.replace(sb.length() - 1, sb.length(), "}");
        return sb.toString();
    }
    
    public String flattenMapRecursive(String dict) {
        Map<Integer, Integer> parenIdx = new HashMap<>();
        Deque<Integer> depth = new ArrayDeque<>();
        for (int i = 0; i < dict.length(); i++) {
            if (dict.charAt(i) == '{')
                depth.push(i);
            else if (dict.charAt(i) == '}') {
                int start = depth.pop();
                parenIdx.put(start, i);
            }                
        }
        
        List<String> res = flatRecursive(dict, 1, parenIdx.get(0) - 1, parenIdx);
        StringBuilder sb = new StringBuilder("{");
        for (String s : res)
            sb.append(s + ",");
        sb.replace(sb.length() - 1, sb.length(), "}");
        return sb.toString();
    }

    private List<String> flatRecursive(String dict, int start, int end, Map<Integer, Integer> parenIdx) {
        List<String> res = new ArrayList<>();
        if (start >= end) {
            return res;
        }
        
        int index = dict.indexOf(':', start);
        String prefix = dict.substring(start, index);

        if (dict.charAt(index + 1) == '{') {
            int closeIdx = parenIdx.get(index + 1);
            List<String> tmp = flatRecursive(dict, index + 2, closeIdx - 1, parenIdx);
            for (int i = 0; i < tmp.size(); i++)
                res.add(prefix + "." + tmp.get(i));
            res.addAll(flatRecursive(dict, closeIdx + 2, end, parenIdx));
        } else {
            int idx = dict.indexOf(',', start) < 0 ? end + 1 : Math.min(dict.indexOf(',', start), end + 1);
            res.add(prefix + ":" + dict.substring(index + 1, idx));
            res.addAll(flatRecursive(dict, idx == dict.length() ? idx : idx + 1, end, parenIdx));
        }
        return res;
    }

    @Before
    public void setUp() {
        flat = new FlattenMap();
    }

    @Test
    public void simpleTest() {
        String s = "{a:bb,cc:dddd}";
        String res = flat.flattenMapIterative(s);
        assertEquals(s, res);
        assertEquals(s, flat.flattenMapRecursive(s));

        s = "{a:b}";
        assertEquals(s, flat.flattenMapIterative(s));
        assertEquals(s, flat.flattenMapRecursive(s));

        s = "{a:{b:c,d:e}}";
        assertEquals("{a.b:c,a.d:e}", flat.flattenMapRecursive(s));
    }

    @Test
    public void testNestedMutipleItem() {
        String s = "{a:b,c:{d:e,f:g},h:i,j:{k:l}}";
        String res = flat.flattenMapIterative(s);
        assertEquals("{a:b,c.d:e,c.f:g,h:i,j.k:l}", res);

        res = flat.flattenMapRecursive(s);
        assertEquals("{a:b,c.d:e,c.f:g,h:i,j.k:l}", res);
    }

    @Test
    public void testDeepNest() {
        String s = "{aa:{bb:{c1:d1,c2:d2},f:g},w:z,p:{q:r,x:y}}";
        String res = flat.flattenMapIterative(s);
        assertEquals("{aa.bb.c1:d1,aa.bb.c2:d2,aa.f:g,w:z,p.q:r,p.x:y}", res);

        res = flat.flattenMapRecursive(s);
        assertEquals("{aa.bb.c1:d1,aa.bb.c2:d2,aa.f:g,w:z,p.q:r,p.x:y}", res);
    }

}
