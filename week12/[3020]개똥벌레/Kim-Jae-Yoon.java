import java.util.*;
import java.io.*;
/*
  Title: 개똥벌레
  Tier: Gold 5
  Algorithm: Binary Search
  Constraint: 1 Second, 128MB
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, h, answer, totalCnt = 0;
    static int[] up, down;

    static void solution() {
        Arrays.sort(up);
        Arrays.sort(down);
        for (int i = 1; i <= h; ++i) {
            int dIdx = (n >> 1) - search(0, i);
            int uIdx = (n >> 1) - search(1, h - i + 1);
            int sum = dIdx + uIdx;
            if (answer > sum) {
                totalCnt = 1;
                answer = sum;
            } else if (answer == sum) {
                ++totalCnt;
            }
        }
        System.out.println(answer + " " + totalCnt);
    }

    static int search(int side, int height) {
        int l = 0, r = n >> 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (side == 0) {
                if(down[mid] >= height) r = mid;
                else l = mid + 1;
            } else{
                if(up[mid] >= height) r = mid;
                else l = mid + 1;
            }
        }
        return r;
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        answer = n;
        up = new int[n >> 1];
        down = new int[n >> 1];
        for (int i = 0; i < n; ++i) {
            if(i % 2 == 1) up[i >> 1] = Integer.parseInt(br.readLine());
            else down[i >> 1] = Integer.parseInt(br.readLine());
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
