import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
트리라면 개념적으로 간선은 무조건 단절선이다. 사이클이 존재하지 않기 때문이다.
점을 없앴을 때 단절점이려면 그 점과 연결된 간선이 두개 이상이어야 한다. 하나만 연결되어있다면(끝점이라면) 단절점이 아니다.
 */
public class JUN14675 {

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      StringTokenizer st;
      int[] count = new int[N + 1];

      for (int i = 1; i < N; i++) {
         st = new StringTokenizer(br.readLine());
         int from = Integer.parseInt(st.nextToken());
         int to = Integer.parseInt(st.nextToken());
         count[from]++;
         count[to]++;
      }
      int q = Integer.parseInt(br.readLine());
      for (int i = 0; i < q; i++) {
         st = new StringTokenizer(br.readLine());
         int type = Integer.parseInt(st.nextToken());
         int target = Integer.parseInt(st.nextToken());
         if (type == 1) {
            if (count[target] == 1) {
               System.out.println("no");
            } else {
               System.out.println("yes");
            }
         } else {
            System.out.println("yes");
         }
      }
   }
}
