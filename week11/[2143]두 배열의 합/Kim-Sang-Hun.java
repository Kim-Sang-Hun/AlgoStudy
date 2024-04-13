import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
GG. 누적합 해서 어떻게 어떻게 하는 것 같은데
아이디어가 떠오르질 않습니다

 */
public class JUN2143_두배열의합 {

   public static void main(String[] args) throws IOException {

      // 입력받기
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int t = Integer.parseInt(br.readLine());
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
         a[i] = Integer.parseInt(st.nextToken());
         if (i > 0) {
            a[i] += a[i - 1];
         }
      }
      int m = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      int[] b = new int[m];
      for (int i = 0; i < m; i++) {
         b[i] = Integer.parseInt(st.nextToken());
         if (i > 0) {
            b[i] += b[i - 1];
         }
      }

      // 다 돌면서 경우의 수 구하기 = 백만 * 백만
      int sA, sB, mA, mB, count = 0;
      for (int i = 0; i < n; i++) {
         sA = a[i];
         if (sA == t) {
            ++count;
         }
         for (int j = 0; j < i; j++) {
            mA = a[j];
            if (sA - mA == t) {
               ++count;
            }
            for (int k = 0; k < m; k++) {
               sB = b[k];
               if (sA - mA + sB == t || sB == t || sA + sB == t) {
                  ++count;
               }
               for (int l = 0; l < k; l++) {
                  mB = b[l];
                  if (sA - mA + sB - mA == t || sB - mB == t || sA + sB - mB == t) {
                     ++count;
                  }
               }
            }
         }
      }
      System.out.println(count);
   }
}
