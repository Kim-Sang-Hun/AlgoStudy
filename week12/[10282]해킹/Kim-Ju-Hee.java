package april3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN10282_해킹_김주희 {
    static int n, d, c, time;
    static boolean[] visited;
    
    static class Infection implements Comparable<Infection>{
        int time, com;
        
        public Infection(int time, int com) {
            this.time = time;
            this.com = com;
        }

        @Override
        public String toString() {
            return "Infection [time=" + time + ", com=" + com + "]";
        }

        @Override
        public int compareTo(Infection o) {
            return this.time - o.time;
        }

        
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            
            List<int[]>[] adj = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            
            visited = new boolean[n+1];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                
                adj[b].add(new int[] {a,s});
            }
            
            
            PriorityQueue<Infection> q = new PriorityQueue<>();
            q.add(new Infection(0, c));

            int cnt = 0;
            while(!q.isEmpty()) {
                Infection cur = q.poll();

                if(visited[cur.com]) continue;

                cnt++;
                visited[cur.com] = true;
                time = cur.time;
        
                for(int[] a : adj[cur.com]) {
                    if(visited[a[0]]) continue;

                    q.add(new Infection(time + a[1], a[0]));
                    
                }
                
            }
            
            System.out.printf("%d %d\n", cnt, time);
 
        }

    }

}
