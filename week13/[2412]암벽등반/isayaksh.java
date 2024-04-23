import java.util.*;
import java.io.*;

public class isayaksh {

    private static int N, T;

    private static boolean[] visited;

    private static List<Hole>[] holes;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        holes = new List[T+1];
        for(int t = 0; t < T+1; t++) {
            holes[t] = new ArrayList<Hole>();
        }

        for(int n = 1; n < N+1; n++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            holes[y].add(new Hole(x, y, n, 0));
        }

        Deque<Hole> deque = new ArrayDeque<Hole>();
        deque.add(new Hole(0, 0, 0, 0));
        visited[0] = true;

        while(!deque.isEmpty()) {

            Hole h = deque.poll();

            if(h.y >= T) {
                System.out.println(h.cnt);
                return;
            }

            for(int y = h.y-2; y < h.y+3; y++) {

                if(y < 0 || T < y) continue;
                
                for(Hole other : holes[y]) {
                    if(other.x < h.x - 2 || h.x + 2 < other.x || visited[other.no]) continue;
                    visited[other.no] = true;
                    deque.add(new Hole(other.x, other.y, other.no, h.cnt+1));
                }

            }

        }

        System.out.println(-1);

    }

    static class Hole {
        int x, y, no, cnt;
        Hole(int x, int y, int no, int cnt) {
            this.x = x;
            this.y = y;
            this.no = no;
            this.cnt = cnt;
        }
    }

}
