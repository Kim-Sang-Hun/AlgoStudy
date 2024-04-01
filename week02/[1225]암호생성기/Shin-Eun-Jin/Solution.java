import java.io.*;
import java.util.*;

public class Solution {
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int test_case = 1; test_case <= 10; test_case++){
            int T = Integer.parseInt(br.readLine());
            queue = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 8; i++){
                queue.offer(Integer.parseInt(st.nextToken()));
            }

            solve();

            System.out.print("#" + T + " ");
            while(!queue.isEmpty()) {
                System.out.print(queue.poll() + " ");
            }
            System.out.println();
        }


    }

    public static void solve() {
        loop:
        while(true) {
            for(int i = 1; i <= 5; i++) {
                int num = queue.poll() - i;

                if(num <= 0) {
                    queue.offer(0);
                    break loop;
                }
                else{
                    queue.offer(num);
                }
            }
        }
    }
}
