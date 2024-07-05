import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
메모리 66000, 시간 612
양방향 매핑하고 루트(1)부터 부모를 처리해줬다.
*/
public class Main {

    static int N;
    static List<List<Integer>> edges;
    static int[] head;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        edges = new ArrayList<>();
        head = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges.get(a).add(b);
            edges.get(b).add(a);
        }
        mapChild(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(head[i]).append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    public static void mapChild(int cur) {
        List<Integer> childs = edges.get(cur);
        for (int child : childs) {
            if (head[child] == 0) {
                head[child] = cur;
                mapChild(child);
            }
        }
    }
}
