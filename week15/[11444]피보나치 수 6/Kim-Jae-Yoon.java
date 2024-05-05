import java.util.*;
import java.io.*;

/*
  Title: 피보나치 수 6
  Tier: Gold 2
  Algorithm: Divide & Conquer
  Constraint: 1 Second, 256MB
  Solution Flow
* 1. 나머지 연산으로는 .. 딱히 별 패턴이 보이지 않는다.
* 2. 패턴 -> 인덱스 기준 [n]^2 + [n+1]^2 = [2*n+1] 값이 나오는 것을 확인(단, n > 2)
* 2-1. n이 짝수일 경우, n/2 - 1, n/2, n/2 + 1 의 적절한 곱, 합 연산으로 구성 가능한 패턴 확인
* 2-2. n이 홀수일 경우, n^2+(n+1)^2 전략 적용 가능
* 2-3. 그렇다면 Top-Down으로 끌어올릴 수 있겠다.
*/

//제 경우 직접 어떤 패턴이 있는지 손으로 끄적이며 찾아봤습니다.
//그렇기에 다른 똑똑한 분들의 풀이(2차원 배열 풀이가 있더라고요)도 참고하면 좋을 듯 합니다.

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long n;
    static final int mod = (int)1e9 + 7;
    static Map<Long, Long> map = new HashMap<>();

    static void input() throws IOException {
        n = Long.parseLong(br.readLine());
        map.put(0L, 0L);
        map.put(1L, 1L);
        map.put(2L, 1L);
        map.put(3L, 2L);
    }

    static long fib(long n) {
        if (map.containsKey(n)) {
            return map.get(n) % mod;
        }
        //n이 짝수일 때, 홀수일 때 구분해줘야 한다.
        //n이 홀수일 때는 n^2+(n+1)^2 전략을
        //n이 짝수일 때는 (k-1)*k + k*(k+1) 전략을 적용한다.
        if (n % 2 == 0) {
            long left = fib((n >> 1) - 1) % mod;
            long mid = fib(n >> 1) % mod;
            long right = fib((n >> 1) + 1) % mod;
            map.put(n, (left * mid) % mod + (mid * right) % mod);
        }
        else{
            long left = fib(n >> 1) % mod;
            left = (left * left) % mod;
            long right = fib((n >> 1) + 1) % mod;
            right = (right * right) % mod;
            map.put(n, left + right);
        }
        return map.get(n) % mod;
    }

    static void solution(){
        System.out.println(fib(n));
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
