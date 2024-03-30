import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Shin-Eun-Jin {
    public static long[] distance;
    public static int N,M,W;
    public static ArrayList<node> road;
    public static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<TC; i++) {
            road = new ArrayList<>();
            boolean isPossible = false;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            distance = new long[N+1];
            for(int j=0; j<M; j++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                road.add(new node(S,E,T));
                road.add(new node(E,S,T));
            }
            for(int k=0; k<W; k++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken())*(-1);
                road.add(new node(S,E,T));
            }
            for(int p=1; p<N+1; p++) {
                Arrays.fill(distance, Long.MAX_VALUE);
                distance[p] = 0;
                bellmanFord(p);
                if(distance[p] < 0) {
                    isPossible = true;
                    break;
                }
            }
            if(isPossible)System.out.println("YES");
            else System.out.println("NO");
        }
    }
    public static boolean bellmanFord(int v) {
        boolean isInfinite = false;
        for(int i=1; i<=N; i++) {
            boolean update = false;
            for(int k=0; k<road.size(); k++) {
                int from = road.get(k).start;
                int to = road.get(k).end;
                int time = road.get(k).time;

                if(distance[from] == Long.MAX_VALUE) continue;
                if(distance[to] > distance[from] + time) {
                    distance[to] = distance[from] + time;
                    update = true;
                    if(i > N-1) isInfinite = true;
                }
            }
            if (!update) {
                break;
            }
        }
        return isInfinite;
    }
    static class node {
        int start,end,time;

        public node(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }

}
