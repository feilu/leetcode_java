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
 * ���룺��һ����һ��0-999999֮��������������������ģ�data center�������������ŵ�ÿһ�зֱ���һ���������ģ�ÿһ��������һ��0-299������������������ĵ������飨data sets��.
 * Ȼ������Կո�ֿ������ݡ����ݶ���1-999999֮���������ÿһ�����999���ַ�����
 * 
 * �����
 * 
 * Ҫ������ֲ�ʽ���ݵĲ��裬���������������ļ以�࿽�������ʹ�������������Ķ������е����ݡ� ��ӡ���Ĳ���ĸ�ʽ�ǣ� <data-set-id> <from> <to>
 * 
 * <data-set-id> ������Ҫ���������ݣ� <from> �Ǹ����������������ĵ������� <to>�Ǽ���������ȥ���������ĵ�����
 * 
 * ����������󣬴�ӡ��done����
 * 
 * ��ʵ�ֲַ�ʽ���ݵĲ�����������Ψһ�ģ���ӡ���κ���ȷ�ز�����ɡ�
 * 
 * Լ��:
 * 
 * �����������stdin��������䵽stdout�����Լ�����������Ч�ġ�
 * 
 * ���磺
 * 
 * ����: 3
 * 
 * 5 1 3 4 5 7
 * 
 * 2 1 3
 * 
 * 1 2
 * 
 * 
 * һ����ȷ�����: 2 3 2
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
