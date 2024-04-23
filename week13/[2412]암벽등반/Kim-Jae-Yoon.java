import java.awt.Point;
import java.util.*;
import java.io.*;
/*
  Title: 암벽 등반
  Tier: Gold 4
  Algorithm: BFS, HashMap
  Constraint: 2 Second, 128MB
  Comment: map의 Key를 Point로 설정했을 때, new Point로 대입할 경우 객체 간 비교가 아니라 값끼리 비교가 된다는 사실을 해당 문제를 통해 학습했습니다.
           아마 이런 분류의 문제가 나오면 Java로 쉽게 풀 수 있을 것 같네요.
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, t;
    static boolean[] vis;
    static List<Integer>[] height;
    static HashMap<Point, Integer> map = new HashMap<>();

    static boolean inDistance(int a, int b, int x, int y) {
        return Math.abs(a - x) <= 2 && Math.abs(b - y) <= 2;
    }

    static void solution() {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));
        map.put(new Point(0, 0), 0);
        while (!q.isEmpty()) {
            Point cur = q.poll();
            if(t <= cur.x){
                System.out.println(map.get(cur));
                return;
            }
            for (int h = 2; h >= -2; --h) {
                int nxtH = cur.x + h;
                if(nxtH < 0 || nxtH > 200000) continue;
                for(int y : height[nxtH]){
                    Point nxt = new Point(nxtH, y);
                    if(!inDistance(nxt.x, nxt.y, cur.x, cur.y)) continue;
                    if(map.containsKey(nxt) && map.containsKey(cur) && map.get(nxt) <= map.get(cur) + 1) continue;
                    map.put(nxt, map.get(cur) + 1);
                    q.add(nxt);
                }
            }

        }
        System.out.println(-1);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        height = new ArrayList[200001];
        for (int i = 0; i <= 200000; ++i) {
            height[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            height[x].add(y);
            map.put(new Point(x, y), (int) 1e9);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
