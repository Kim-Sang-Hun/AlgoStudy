import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN2527_직사각형 {

    public static class Square {
        int left, bottom, right, top;

        public Square(int left, int bottom, int right, int top) {
            this.left = left;
            this.bottom = bottom;
            this.right = right;
            this.top = top;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Square s1 = new Square(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Square s2 = new Square(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            // 아예 만나지 않는 경우 d 출력
            if (s1.right < s2.left || s2.right < s1.left || s1.top < s2.bottom || s2.top < s1.bottom) System.out.println("d");
            // 한 점에서 만나는 경우 c 출력
            else if ((s1.left == s2.right || s1.right == s2.left) && (s1.top == s2.bottom || s1.bottom == s2.top)) System.out.println("c");
            // 선분으로 만나는 경우 b 출력
            else if (s1.top == s2.bottom || s1.bottom == s2.top || s1.left == s2.right || s2.left == s1.right) System.out.println("b");
            else System.out.println("a");
        }

    }
}
