import java.util.*;
import java.io.*;

public class isayaksh {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int answer = 0;

        int N = Integer.parseInt(br.readLine());
        
        int[] degree = new int[N+1];
        int[] workingTime = new int[N+1];
        Map<Integer, List<Integer>> edge = new HashMap<Integer, List<Integer>>();
        Queue<Job> priority = new PriorityQueue<Job>();

        for(int n = 1; n < N+1; n++) {
            edge.put(n, new ArrayList<Integer>());
        }

        for(int n = 1; n < N+1; n++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int pre = Integer.parseInt(st.nextToken());

            workingTime[n] = time;
            degree[n] = pre;

            for(int i = 0; i < pre; i++) {
                int target = Integer.parseInt(st.nextToken());
                edge.get(target).add(n);
            }
        }

        for(int n = 1; n < N+1; n++) {
            if(degree[n] == 0) {
                priority.add(new Job(n, workingTime[n]));
                if(answer < workingTime[n]) answer = workingTime[n];
            }
        }

        while(!priority.isEmpty()) {
            Job job = priority.poll();

            for(int next : edge.get(job.no)) {
                if(degree[next] == 0) continue;
                
                if(--degree[next] == 0) {
                    priority.add(new Job(next, job.time + workingTime[next]));
                    if(answer < job.time + workingTime[next]) answer = job.time + workingTime[next];
                }
            }

        }

        System.out.println(answer);

    }

    static class Job implements Comparable<Job> {
        int no, time;
        Job(int no, int time) {
            this.no = no;
            this.time = time;
        }
        @Override
        public int compareTo(Job other) {
            return this.time - other.time;
        }
    }

}
