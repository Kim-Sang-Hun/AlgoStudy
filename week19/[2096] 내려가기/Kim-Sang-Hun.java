import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
메모리 42552kb, 시간 336ms
dp로 풀어주었다.
바로 전 배열과 현재 배열 두 개만 있으면 dp를 문제없이 사용 가능하므로
dp의 크기를 [3][2]로 만들어주었다.
max, min 두가지 경우가 있으므로 dp배열도 두 가지로 만들었다.

*/
public class N2096_내려가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dpMax = new int[3][2];
        int[][] dpMin = new int[3][2];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            int cur = i % 2;
            int prev = (i + 1) % 2;
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int third = Integer.parseInt(st.nextToken());

            dpMax[0][cur] = Math.max(dpMax[0][prev], dpMax[1][prev]) + first;
            dpMax[1][cur] = Math.max(Math.max(dpMax[0][prev], dpMax[1][prev]), dpMax[2][prev]) + second;
            dpMax[2][cur] = Math.max(dpMax[1][prev], dpMax[2][prev]) + third;

            dpMin[0][cur] = Math.min(dpMin[0][prev], dpMin[1][prev]) + first;
            dpMin[1][cur] = Math.min(Math.min(dpMin[0][prev], dpMin[1][prev]), dpMin[2][prev]) + second;
            dpMin[2][cur] = Math.min(dpMin[1][prev], dpMin[2][prev]) + third;
        }
        int idx = (N - 1) % 2;
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dpMax[i][idx]);
            min = Math.min(min, dpMin[i][idx]);
        }

        System.out.println(max + " " + min);
    }
}
