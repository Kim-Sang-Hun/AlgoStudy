import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
한 건물당 영향주는 건물이 최대 3개이므로
건물이 생성되고 삭제될 때 0에서 벗어나거나 0이 될 때 다음 건물들에 영향을 주면서 진행한다.
메모리 94204kb, 시간 716ms
 */
public class JUN14676_영우는사기꾼 {
   static int n, m, k;
   static boolean[] valid;
   static St[] sts;
   static List<List<Integer>> next;
   static class St {
      // 건물 개수, 만들기 위해 필요한 건물 수
      int cnt, need;
      // 만들어진 이전 건물들(중복 제거위해 Set)
      Set<Integer> set;

      public St(int cnt, int need, Set<Integer> set) {
         this.cnt = cnt;
         this.need = need;
         this.set = set;
      }
   }
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());
      sts = new St[n + 1];
      valid = new boolean[n + 1];
      next = new ArrayList<>();
      for (int i = 0; i <= n; i++) {
         sts[i] = new St(0, 0, new HashSet<>());
         next.add(new ArrayList<>());
      }
      // sts[to].need는 해당 건물이 만들어지기 위해 필요한 건물들의 개수이다
      for (int i = 1; i <= m; i++) {
         st = new StringTokenizer(br.readLine());
         int from = Integer.parseInt(st.nextToken());
         int to = Integer.parseInt(st.nextToken());
         next.get(from).add(to);
         ++sts[to].need;
      }

      for (int i = 0; i < k; i++) {
         st = new StringTokenizer(br.readLine());
         int op = Integer.parseInt(st.nextToken());
         int target = Integer.parseInt(st.nextToken());
         if (op == 1) {
            // 만들어질 수 있는 건물이라면 만들고 다음 건물들이 만들어질  수 있는지 확인해본다
            if (valid[target] || sts[target].need == 0) {
               ++sts[target].cnt;
               valid[target] = true;

               List<Integer> list = next.get(target);
               for (int j = 0; j < list.size(); j++) {
                  St n = sts[list.get(j)];
                  n.set.add(target);
                  if (n.need == n.set.size()) {
                     valid[list.get(j)] = true;
                  }
               }
            } else {
               System.out.println("Lier!");
               return;
            }
         } else {
            if (sts[target].cnt == 0) {
               System.out.println("Lier!");
               return;
            } else {
               sts[target].cnt--;
            }

            // 건물의 개수가 0이 되었을 때 이 건물이 필요한 건물들이 더이상 지어질 수 없다. valid를 해제해준다
            if (sts[target].cnt == 0) {
               for (int next : next.get(target)) {
                  St n = sts[next];
                  n.set.remove(target);
                  valid[next] = false;
               }
            }
         }
      }
      System.out.println("King-God-Emperor");
   }
}
