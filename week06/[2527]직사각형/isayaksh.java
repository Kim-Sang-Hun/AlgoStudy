import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int x, y, p, q;
        for(int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            Square s1 = new Square(x, y, p, q);

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            Square s2 = new Square(x, y, p, q);

            sb.append(solution(s1, s2) + "\n");
        }

        System.out.println(sb);

    }

    private static char solution(Square s1, Square s2) {

        /*
         * (s1.x, s1.q)  (s1.p, s1.q)  (s2.x, s2.q)  (s2.p, s2.q)
         *       ┌────────────┐              ┌────────────┐ 
         *       │            │              │            │
         *       │     s1     │              │     s2     │
         *       │            │              │            │
         *       └────────────┘              └────────────┘
         * (s1.x, s1.y)  (s1.p, s1.y)  (s2.x, s2.y)  (s2.p, s2.y)
         */

        // 공통부분이 없음
        if(s1.q < s2.y || s1.p < s2.x || s1.y > s2.q || s1.x > s2.p) return 'd';

        // 점
        if(s1.p == s2.x && s1.q == s2.y) return 'c';
        if(s1.p == s2.x && s1.y == s2.q) return 'c';
        if(s1.x == s2.p && s1.y == s2.q) return 'c';
        if(s1.x == s2.p && s1.q == s2.y) return 'c';

        // 선분
        if(s1.q == s2.y && ((s1.x <= s2.x && s2.x <= s1.p) || (s1.x <= s2.p && s2.p <= s1.p))) return 'b';
        if(s1.q == s2.y && ((s2.x <= s1.x && s1.x <= s2.p) || (s2.x <= s1.p && s1.p <= s2.p))) return 'b';
        if(s1.p == s2.x && ((s1.y <= s2.y && s2.y <= s1.q) || (s1.y <= s2.q && s2.q <= s1.q))) return 'b';
        if(s1.p == s2.x && ((s2.y <= s1.y && s1.y <= s2.q) || (s2.y <= s1.q && s1.q <= s2.q))) return 'b';
        if(s1.y == s2.q && ((s1.x <= s2.x && s2.x <= s1.p) || (s1.x <= s2.p && s2.p <= s1.p))) return 'b';
        if(s1.y == s2.q && ((s2.x <= s1.x && s1.x <= s2.p) || (s2.x <= s1.p && s1.p <= s2.p))) return 'b';
        if(s1.x == s2.p && ((s1.y <= s2.y && s2.y <= s1.q) || (s1.y <= s2.q && s2.q <= s1.q))) return 'b';
        if(s1.x == s2.p && ((s2.y <= s1.y && s1.y <= s2.q) || (s2.y <= s1.q && s1.q <= s2.q))) return 'b';

        // 직사각형
        return 'a';
    }

    static class Square {
        int x, y, p, q;

        Square(int x, int y, int p, int q) {
            this.x = x;
            this.y = y;
            this.p = p;
            this.q = q;
        }

    }

}
