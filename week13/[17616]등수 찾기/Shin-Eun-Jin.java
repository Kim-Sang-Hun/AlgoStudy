import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17616_등수찾기 {
    static int N, M, X;
    static ArrayList<ArrayList<Integer>> AToB;
    static ArrayList<ArrayList<Integer>> BToA;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        AToB = new ArrayList<>();
        BToA = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            AToB.add(new ArrayList<>());
            BToA.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            AToB.get(A).add(B);
            BToA.get(B).add(A);
        }

        System.out.println((bfs(X, BToA) + 1) + " " + (N - bfs(X, AToB)));

    }

    public static int bfs(int num, ArrayList<ArrayList<Integer>> list) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] isVisit = new boolean[N + 1];
        queue.offer(num);
        isVisit[num] = true;

        int cnt = 0;
        while (!queue.isEmpty()) {
            int curNum = queue.poll();

            for (int nextNum : list.get(curNum)) {
                if (isVisit[nextNum]) {
                    continue;
                }

                queue.offer(nextNum);
                isVisit[nextNum] = true;
                cnt++;
            }
        }

        return cnt;
    }
}
