import java.util.*;
import java.io.*;

public class isayaksh {

    private static int T, N, D, C;
    private static Map<Integer, List<Computer>> dependency;
    private static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {

            int count = 0;
            int time = 0;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            // 의존성 자료구조 초기화
            dependency = new HashMap<Integer, List<Computer>>();

            for(int n = 1; n < N+1; n++) {
                dependency.put(n, new ArrayList<Computer>());
            }

            for(int d = 0; d < D; d++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                dependency.get(b).add(new Computer(a, s));
            }
            
            Queue<Computer> queue = new PriorityQueue<Computer>();
            visited = new boolean[N+1];

            queue.add(new Computer(C, 0));

            while(!queue.isEmpty()) {
                Computer parent = queue.poll();
                
                if(visited[parent.no]) continue;

                visited[parent.no] = true;
                count++;
                time = parent.sec;

                for(Computer child : dependency.get(parent.no)) {
                    if(visited[child.no]) continue;
                    queue.add(new Computer(child.no, parent.sec + child.sec));
                }
            }

            sb.append(count + " " + time + "\n");

        }

        System.out.println(sb);
        
    }

    static class Computer implements Comparable<Computer> {
        int no, sec;
        Computer(int no, int sec) {
            this.no = no;
            this.sec = sec;
        }
        @Override
        public int compareTo(Computer other) {
            return this.sec - other.sec;
        }
    }

}
