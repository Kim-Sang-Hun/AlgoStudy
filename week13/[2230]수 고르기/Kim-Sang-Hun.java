import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
투 포인터로 둘다 0을 가리키고 있다가
diff가 m보다 작으면 right를, m보다 크면 left를 늘려준다.
minDiff가 m이라면 끝낸다.
*/
public class JUN2230_수고르기 {

    static int minDiff, n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        minDiff = Integer.MAX_VALUE;
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int left = 0, right = 0;
        while (left < n) {
            int diff = arr[right] - arr[left];
            if (diff == m) {
                minDiff = m;
                break;
            }
            if (diff < m) {
                right++;
                if (right >= n) break;
            } else {
                minDiff = Math.min(minDiff, diff);
                left++;
            }
        }
        System.out.println(minDiff);
    }

}
