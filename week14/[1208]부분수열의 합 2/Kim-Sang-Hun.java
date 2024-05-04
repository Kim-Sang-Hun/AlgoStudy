import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
배열의 길이가 40이므로 부분집합을 구해버리면 2^40이므로 시간초과가 나게 된다.
그래서 두 배열로 나누고 부분집합을 따로따로 구한 다음 투포인터로 그 두 배열의 합이 S와 같은지 확인해야 한다.
경우의 수가 2^40까지도 나올 수 있어서 long타입으로 초기화해줘야 한다.
이 때 구한 두 부분집합의 배열에는 무조건 0이 존재한다.
S가 0일 경우 0+0(두 부분집합에서 모두 아무 것도 안 고른 경우)라는 존재해선 안 되는 경우의 수가 추가되므로
--cnt를 해줘야 한다.
 */

public class JUN1208_부분수열의합2 {
    static int n, s;
    static long cnt;
    static int[] arr;
    static List<Integer> left;
    static List<Integer> right;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        left = new ArrayList<>();
        right = new ArrayList<>();
        makeList(0, n / 2, 0, left);
        makeList(n / 2, n, 0, right);
        Collections.sort(left);
        Collections.sort(right);
        twoPointer();
        System.out.println(cnt);
    }

    private static void makeList(int idx, int end, int sum, List<Integer> list) {
        if (idx == end) {
            list.add(sum);
            return;
        }

        makeList(idx+1, end, sum + arr[idx], list);
        makeList(idx+1, end, sum, list);
    }

    private static void twoPointer() {
        int l = 0;
        int r = right.size() - 1;

        while (l < left.size() && r >= 0) {
            int lv = left.get(l);
            int rv = right.get(r);
            int sum = lv + rv;

            if (sum > s) {
                --r;
            } else if (sum < s) {
                ++l;
            } else {
                long cnt1 = 0;
                while (l < left.size() && left.get(l) == lv) {
                    ++l;
                    ++cnt1;
                }
                long cnt2 = 0;
                while (r >= 0 && right.get(r) == rv) {
                    --r;
                    ++cnt2;
                }
                cnt += cnt1 * cnt2;
            }
        }
        if (s == 0) --cnt;
    }
}
