import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int t, idx;
    static int[] a;
    static StringBuilder sb = new StringBuilder();

    static void solution(){
        int val = 1;
        idx = 0;
        /**
         * 귀찮 + 시간 여유로워 생략했지만,
         * 이 문제의 경우 1~5 사이클을 반복하게 되는데
         * 8개의 배열에서 15의 배수만큼씩 뺄 수 있는 만큼 빼고 시작하면
         * 훨씬 속도 측면에서 유리하다.
         * int q = min(a) / 15 + 1;
         * each a[] -= q * 15;
         */
        while(true){
            a[idx] -= val++;
            if(a[idx] <= 0){
                a[idx] = 0;
                idx = (idx + 1) % 8;
                break;
            }
            idx = (idx + 1) % 8;
            if (val == 6) {
                val = 1;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        a = new int[8];
        for(int x = 0;x < 10; ++x) {
            t = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; ++i) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            solution();
            sb.append("#").append(t).append(" ");
            for(int i = 0;i < 8; ++i){
                sb.append(a[(i + idx) % 8]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
