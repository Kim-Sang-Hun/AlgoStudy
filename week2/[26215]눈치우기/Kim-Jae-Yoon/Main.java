import java.io.*;
import java.util.*;

public class Main {
	
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, answer = 0;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    /**
     * method: solution
     * 2개 이상의 치워야 하는 집이 있을 경우 동시에 치울 수 있도록 한다.
     * 1개가 남을 때 까지 해당 행동을 반복하며,
     * 이후 1개가 남았을 때 단독으로 치울 수 있도록 하고 시간을 계산한다.
     * 최종적으로 1440이 넘게 나왔을 경우 -1로, 이외에는 제 값을 출력한다.
     */
    
    static void solution(){
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if(a == b){
                answer += a;
                continue;
            }
            answer += b;
            pq.add(a - b);
        }
        if(pq.size() == 1){
            answer += pq.poll();
        }
        if(answer > 1440) answer = -1;
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) {
            pq.add(Integer.parseInt(st.nextToken()));
        }
        solution();
    }

}
