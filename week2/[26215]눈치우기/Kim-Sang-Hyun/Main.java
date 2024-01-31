import java.io.*;
import java.util.*;

/*
 * [26215] 눈 치우기
 * https://www.acmicpc.net/problem/26215
*/
public class Main {
    
    public static PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        for (int i = 0; i < N; i++) {
            priorityQueue.add(Integer.parseInt(st.nextToken()));
        }
        
        System.out.println(solution(N, priorityQueue));
        
    }
    
    public static int solution(int N, PriorityQueue<Integer> priorityQueue) {
        int time = 0;
        int n1, n2;
        
        while(priorityQueue.size() > 1) {
            n1 = priorityQueue.poll();
            n2 = priorityQueue.poll();
            if(n1 - 1 > 0) {
                priorityQueue.add(n1 - 1);
            }
            if(n2 - 1 > 0) {
                priorityQueue.add(n2 - 1);
            }
            time++;
        }
        
        if(priorityQueue.size() == 1) {
            time += priorityQueue.poll();
        }
        
        return time > 1440 ? -1 : time;
        
    }
    
}