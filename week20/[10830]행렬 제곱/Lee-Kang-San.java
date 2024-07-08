package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
<행렬 제곱> G4
* 링크
https://www.acmicpc.net/problem/10830
* 요약
크기가 N*N인 행렬 A가 주어진다.
이때, A의 B제곱을 구하는 프로그램을 작성하시오.
수가 매우 커질 수 있으니, A^B의 각 원소를 1,000으로 나눈 나머지를 출력한다.
* 풀이
Divide And Conquer + Memoization
최종 출력이 %1000 된 값임에 주의하기
* 메모리
14332 KB
* 시간
132 ms
*/

public class boj_10830 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N; // 행렬의 크기
    static long B; // A의 B제곱
    static long[][] A; // N * N 행렬 A
    static long[][] result;

    static HashMap<Long, long[][]> memoi;

    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        A = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Long.parseLong(st.nextToken());
            }
        }

        memoi = new HashMap<>();
        memoi.put(1L, A);

        // 풀이
        long[][] answer = divideAndConquer(B);

        // 출력
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                sb.append(answer[i][j]%1000).append(' ');
            }
            sb.append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static long[][] divideAndConquer(long b) {
        if(memoi.containsKey(b)) { // 이미 있는 결과면 memoi에서 가져다 쓰기
            return memoi.get(b);
        } else { // 없으면 새로 결과 만들어서 삽입 후 리턴
            long half = b/2;
            long[][] first = divideAndConquer(half);
            long[][] second = divideAndConquer(b-half);
            long[][] temp = new long[N][N];

            // temp에 first, second 곱한 결과 저장
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    for(int k=0; k<N; k++) {
                        temp[i][j] += (first[i][k]%1000) * (second[k][j]%1000);
                        temp[i][j] %= 1000;
                    }
                }
            }

            // memoi에 temp 삽입
            memoi.put(b, temp);
            return temp;
        }
    }
}
