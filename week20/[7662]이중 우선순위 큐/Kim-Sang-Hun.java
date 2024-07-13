import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < T; i++) {
            
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();
            
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                String op = st.nextToken();
                int target = Integer.parseInt(st.nextToken());
                if (op.equals("I")) {
                    map.put(target, map.getOrDefault(target, 0) + 1);
                } else {
                    if (map.isEmpty()) continue;
                    int num;
                    if (target == 1) {
                        num = map.lastKey();
                    } else {
                        num = map.firstKey();
                    }
                    if (map.put(num, map.get(num) - 1) == 1) {
                        map.remove(num);
                    }
                }
            }
            if (map.isEmpty()) {
                sb.append("EMPTY").append(System.lineSeparator());
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append(System.lineSeparator());
            }
        }
        System.out.print(sb);
    }
}
