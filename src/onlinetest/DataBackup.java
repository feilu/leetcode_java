package onlinetest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 输入：第一行是一个0-999999之间的整数，代表数据中心（data center）的数量。接着的每一行分别是一个数据中心，每一行最先是一个0-299的整数代表该数据中心的数据组（data sets）.
 * 然后就是以空格分开的数据。数据都是1-999999之间的整数。每一行最多999个字符长。
 * 
 * 输出：
 * 
 * 要求输出分布式备份的步骤，将数据在数据中心间互相拷贝，最后使得所有数据中心都有所有的数据。 打印出的步骤的格式是： <data-set-id> <from> <to>
 * 
 * <data-set-id> 就是所要拷贝的数据， <from> 是该数据所在数据中心的索引， <to>是即将拷贝过去的数据中心的索引
 * 
 * 当步骤结束后，打印“done”。
 * 
 * 能实现分布式备份的步骤往往不是唯一的，打印出任何正确地步骤均可。
 * 
 * 约束:
 * 
 * 输入必须来自stdin，输出必输到stdout。可以假设输入是有效的。
 * 
 * 例如：
 * 
 * 输入: 3
 * 
 * 5 1 3 4 5 7
 * 
 * 2 1 3
 * 
 * 1 2
 * 
 * 
 * 一种正确地输出: 2 3 2
 * 
 * 2 3 1
 * 
 * 1 1 3
 * 
 * 4 1 2
 * 
 * 5 1 3
 * 
 * 5 3 2
 * 
 * 4 2 3
 * 
 * 3 1 3
 * 
 * 7 1 2
 * 
 * 7 1 3
 * 
 * done
 * 
 * 
 */
public class DataBackup {

    private static final int MAX_DC = 999999;
    private static final int MAX_LEN = 999;

    private Map<Integer, Integer> fullDataMap = new HashMap<Integer, Integer>();
    private List<Set<Integer>> inputData = new ArrayList<Set<Integer>>();

    public static void main(String[] args) throws Exception {
        DataBackup backup = new DataBackup();

        backup.processInput();
        backup.runReplication();
    }

    private void processInput() throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int dcNum = Integer.parseInt(reader.readLine().trim());

            for (int i = 1; i <= dcNum; i++) {
                try {
                    String line = reader.readLine();
                    if (line.length() > MAX_LEN)
                        throw new IllegalArgumentException("line length exceeds " + MAX_LEN);

                    String[] num = line.split(" ");
                    Set<Integer> set = new HashSet<>();

                    int size = Integer.parseInt(num[0]);
                    for (int t = 1; t <= size; t++) {
                        int tmp = Integer.parseInt(num[t]);
                        set.add(tmp);

                        if (!fullDataMap.containsKey(tmp)) {
                            fullDataMap.put(tmp, i); // keep which dc has tmp.
                        }
                    }
                    inputData.add(set);

                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
    }

    private void runReplication() {
        int dcNum = inputData.size();
        for (int data : fullDataMap.keySet()) {
            int dc = fullDataMap.get(data);

            for (int id = 1; id <= dcNum; id++) {
                if (!inputData.get(id - 1).contains(data)) {
                    System.out.println(data + " " + dc + " " + id);
                }
            }
        }
        System.out.println("Done");
    }
}
