package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <피보나치 수 6> G2
 * 링크
 * https://www.acmicpc.net/problem/11444
 * 요약
 * 
 * 풀이
 * 도가뉴 항등식 : f(m+n) = f(m-1)f(n) + f(m)f(n+1)
 * (짝수항) m=n, f(2n) = f(n) * (f(n) + 2 * f(n-1))
 * (홀수항) m=n+1, f(2n+1) = f(n)^2 + f(n+1)^2
 * https://www.acmicpc.net/blog/view/28
 */
public class boj_11444 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long modVal = 1000000007L;

    static long n; // n번째 피보나치 수 (n=1~1,000,000,000,000,000,000)
    static HashMap<Long, Long> fib = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // 입력
        n = Long.parseLong(br.readLine());

        // 풀이
        fib.put(0L, 0L);
        fib.put(1L, 1L);
        fib.put(2L, 1L);
        long ans = fibonacci(n);
        // 출력
        bw.write(ans + "");
        bw.flush();
    }

    private static long fibonacci(long n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else if (fib.containsKey(n)) {
            return fib.get(n);
        } else {
            long isOdd = n % 2;
            if (isOdd == 1L) {
                long val1 = fibonacci((n + 1) / 2) % modVal;
                long val2 = fibonacci((n + 1) / 2 - 1) % modVal;
                fib.put(n, ((val1 * val1) % modVal + (val2 * val2) % modVal) % modVal);
                return fib.get(n);
            } else {
                long val1 = fibonacci(n / 2 - 1) % modVal;
                long val2 = fibonacci(n / 2) % modVal;
                fib.put(n, (((2 * val1) % modVal + (val2) % modVal) * val2) % modVal);
                return fib.get(n);
            }
        }
    }
}
