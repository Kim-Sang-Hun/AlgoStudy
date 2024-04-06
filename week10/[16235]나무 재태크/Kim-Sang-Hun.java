import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
문제 설명에 충실했다.
봄에 survive, dead, reproduction 리스트를 새로 만들고
생존은 survive, 죽은 나무는 dead, 살고 나이가 5의 배수인 나무는 reproduction에 넣어줬다.
 */
public class JUN16235_나무재테크 {

    static int n, treeCnt, time;
    static int[][] map, add;
    static int[][] dirs = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static PriorityQueue<Tree> pq;
    static List<Tree> survive, dead, reproduction;

    static class Tree implements Comparable<Tree> {
        int y, x, age;

        public Tree(int y, int x, int age) {
            this.y = y;
            this.x = x;
            this.age = age;
        }


        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        treeCnt = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        add = new int[n][n];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = 5;
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pq = new PriorityQueue<>();
        for (int i = 0; i < treeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            pq.add(new Tree(y, x, age));
        }
        for (int i = 0; i < time; i++) {
            spring(i);
            if (pq.size() == 0) break;
            summer();
            autumn();
            winter();
        }
        System.out.println(pq.size());
    }

    private static void spring(int t) {
        survive = new ArrayList<>();
        dead = new ArrayList<>();
        reproduction = new ArrayList<>();
        int size = pq.size();
        for (int i = 0; i < size; i++) {
            Tree cur = pq.poll();
            int y = cur.y;
            int x = cur.x;
            int age = cur.age;
            if (age <= map[y][x]) {
                survive.add(cur);
                cur.age += 1;
                if (cur.age % 5 == 0) {
                    reproduction.add(cur);
                }
                map[y][x] -= age;
            } else {
                dead.add(cur);
            }
        }
        pq.addAll(survive);
    }

    private static void summer() {
        for (Tree d : dead) {
            map[d.y][d.x] += d.age / 2;
        }
    }

    private static void autumn() {
        for (Tree re : reproduction) {
            for (int i = 0; i < 8; i++) {
                int ny = re.y + dirs[i][0];
                int nx = re.x + dirs[i][1];

                if (ny >= n || ny < 0 || nx >= n || nx < 0) continue;
                pq.add(new Tree(ny, nx, 1));
            }
        }
    }

    private static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] += add[i][j];
            }
        }
    }
}
