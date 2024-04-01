import java.util.*;

public class Main {
    static int L, C;
    static List<String> list;
    static PriorityQueue<String> queue = new PriorityQueue<>();

    public static void bfs(int idx, String str) {
        if (idx >= L) {
            return;
        }
        if (idx == L - 1) {
            int count = 0;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'u' || c == 'o') {
                    count++;
                }
            }
            if (count == 0) {
                return;
            } else if (L - count < 2) {
                return;
            } else {
                queue.add(str);
            }
        }
        /* 
        string을 charAt으로 하나하나 꺼내보며 마지막으로 들어간 글자보다 큰 것만 들어가게 합니다.
        index를 늘려가며 문자열의 길이를 셉니다.
        */
        for (int i = idx; i < C; i++) {
            String s = list.get(i);
            if (str.charAt(idx) < s.charAt(0)) {
                bfs(idx + 1, str + s);
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        L = s.nextInt();
        C = s.nextInt();
        list = new ArrayList<>();
        for (int i = 0; i < C; i++) {
            String a = s.next();
            list.add(a);
        }
        Collections.sort(list);
        for (int i = 0; i < C - L + 1; i++) {
            bfs(0, list.get(i));
        }
        for (String str: queue) {
            System.out.println(str);
        }
    }
}
