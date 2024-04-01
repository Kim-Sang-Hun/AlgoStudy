package Algo_week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2527 {
    
    static int x1, y1, p1, q1;
    static int x2, y2, p2, q2;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            p1 = Integer.parseInt(st.nextToken());
            q1 = Integer.parseInt(st.nextToken());
            
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            p2 = Integer.parseInt(st.nextToken());
            q2 = Integer.parseInt(st.nextToken());
            
            if ((p1 == x2 && q1 == y2) || (x1 == p2 && y1 == q2) || (x1 == p2 && y2 == q1) || (p1 == x2 && y1 == q2)) {
                System.out.println("c");
            } else if (p1 < x2 || p2 < x1 || q1 < y2 || q2 < y1) {
                System.out.println("d");
            } else if (p1 == x2 || p2 == x1 || q1 == y2|| q2 == y1) {
                System.out.println("b");
            } else {
                System.out.println("a");
            }
        }
    }
}
