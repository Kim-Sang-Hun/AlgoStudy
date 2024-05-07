import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * 도가뉴 항등식 : f(m+n) = f(m-1)f(n) + f(m)f(n+1)
 * (짝수항) m=n, f(2n) = f(n) * (f(n) + 2 * f(n-1))
 * (홀수항) m=n+1, f(2n+1) = f(n)^2 + f(n+1)^2
 * 을 활용해서 푼다.
 * fibo값을 저장하기 위해 Map을 사용했다. 위의 공식을 코드로 바꾸면 끝이다.
 */
public class JUN11444_피보나치수6 {

   static Map<Long, Long> fibo = new HashMap<>();
   static int dividend = 1_000_000_007;
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      long n = sc.nextLong();

      fibo.put(0L, 0L);
      fibo.put(1L, 1L);
      fibo.put(2L, 1L);
      fibo.put(3L, 2L);
      System.out.println(getFibo(n));
   }

   private static long getFibo(long n) {
      if (fibo.get(n) != null) return fibo.get(n);
      if (n % 2 == 0) {
         long fb = ((getFibo(n / 2) % dividend) * (getFibo(n/2) % dividend + 2 * getFibo(n/2-1) % dividend) % dividend) % dividend;
         fibo.put(n, fb);
         return fb;
      } else {
         long fb = ((getFibo(n / 2) % dividend) * (getFibo(n/2) % dividend) % dividend
             + (getFibo(n/2 + 1) % dividend) * (getFibo(n/2 + 1) % dividend) % dividend) % dividend;
         fibo.put(n, fb);
         return fb;
      }
   }
}
