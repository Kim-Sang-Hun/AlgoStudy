import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2412_암벽등반 {
    static int n, T;
    static ArrayList<Integer>[] list;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 홈의 개수
        T = Integer.parseInt(st.nextToken()); // 암벽의 정상
        list = new ArrayList[200001];
        for (int i = 0; i <= 200000; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list[y].add(x);
        }

        for (int i = 0; i <= 200000; i++) {
            Collections.sort(list[i]);
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(0, 0));

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Pos cur = queue.poll();

                if (cur.y == T) {
                    return ans;
                }

                for (int y = cur.y - 2; y <= cur.y + 2; y++) {
                    if (y < 0 || y > 200000)
                        continue;

                    for (int x = 0; x < list[y].size(); x++) {
                        if (cur.x + 2 < list[y].get(x)) {
                            break;
                        } else if (cur.x - 2 > list[y].get(x)) {
                            continue;
                        }

                        queue.offer(new Pos(list[y].get(x), y));
                        list[y].remove(x);
                        x--;
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
