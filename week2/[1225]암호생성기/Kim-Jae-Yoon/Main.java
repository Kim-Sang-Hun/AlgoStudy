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
