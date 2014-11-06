package onlinetest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
    private static final int MAX_LINE_LEN = 999;
    private static final int MAX_DATA_SET = 299;
    private static final int MAX_DATA_SIZE = 999999;
    
    // data index to data set id mapping.
    private Map<Integer, Integer> fullDataMap = new HashMap<>();
    private List<Set<Integer>> inputData = new ArrayList<Set<Integer>>();
    
    public static void main(String args[] ) throws Exception {
        Solution backup = new Solution();
        backup.processInput();
        backup.runReplication();        
    }
    
    private void processInput() throws Exception {
        // try with resource to auto close readers.
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int dcNum = Integer.parseInt(reader.readLine().trim());
            
            for (int dc = 1; dc <= dcNum; dc++) {
                String line = reader.readLine();
                if (line.length() > MAX_LINE_LEN)
                    throw new IllegalArgumentException("line length exceeds " + MAX_LINE_LEN);
                
                String[] num = line.split(" ");                
                
                int size = Integer.parseInt(num[0]);
                if (size < 0 || size > MAX_DATA_SET)
                    throw new IllegalArgumentException("Invalid data set size, must be positive and less than " + MAX_DATA_SET);
                
                Set<Integer> set = new HashSet<>();
                for (int t = 1; t <= size; t++) {
                    int tmp = validateData(Integer.parseInt(num[t]));                    
                    set.add(tmp);
                    
                    // add the number to full data map;
                    if (!this.fullDataMap.containsKey(tmp))
                        this.fullDataMap.put(tmp, dc);
                }
                this.inputData.add(set);
            }
        }
    }
    
    private void runReplication() {
        int dcNum = this.inputData.size();
        
        // for all data in full data map, start single thread replication.
        for (int data : this.fullDataMap.keySet()) {
            // get data center that has this data
            int dc = this.fullDataMap.get(data); 
            
            for (int id = 1; id <= dcNum; id++) {
                if (!this.inputData.get(id - 1).contains(data)) {
                    System.out.println(data + " " + dc + " " + id);
                }
            }
        }
        System.out.println("done");
    }
    
    private static int validateData(int num) {
        if (num >=1 && num <= MAX_DATA_SIZE) {
            return num;
        } else {
            throw new IllegalArgumentException("Invalid data size, must be positive and less than " + MAX_DATA_SIZE);
        }
    }
}