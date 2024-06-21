import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
bfs로 가장 가까운 친구들부터 가장 멀리 있는 친구를 세어주면 되는 문제.
메모리 16000Kb, 시간 132ms
LinkedList 이용해서 양방향 결합 시켜주고
가장 가까운 차수를 가진 친구를 후보자에 넣어준다.
*/
public class Main {

   static boolean[] visited;
   static int n, minPoint;
   static List<Integer> candidates;
   static List<List<Integer>> friends;
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      candidates = new LinkedList<>();
      friends = new ArrayList<>();
      minPoint = Integer.MAX_VALUE;
      visited = new boolean[n + 1];
      for (int i = 0; i <= n; i++) {
         friends.add(new LinkedList<>());
      }
      while (true) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         int p1 = Integer.parseInt(st.nextToken());
         int p2 = Integer.parseInt(st.nextToken());
         if (p1 == -1 || p2 == -1) break;
         friends.get(p1).add(p2);
         friends.get(p2).add(p1);
      }
      for (int i = 1; i <= n; i++) {
         getPoint(i);

      }
      System.out.println(minPoint + " " + candidates.size());
      for (int i : candidates) {
         System.out.print(i + " ");
      }
   }

   private static void getPoint(int member) {
      Queue<int[]> qu = new LinkedList<>();
      qu.add(new int[] {member, 0});
      visited = new boolean[n + 1];
      visited[member] = true;
      int max = 0;
      while (!qu.isEmpty()) {
         int[] cur = qu.poll();
         max = cur[1];
         if (max > minPoint) {
            break;
         }
         for (int i : friends.get(cur[0])) {
            if (visited[i]) continue;
            visited[i] = true;
            qu.add(new int[] {i, cur[1] + 1});
         }
      }
      if (max < minPoint) {
         minPoint = max;
         candidates = new LinkedList<>();
      }
      if (max == minPoint) {
         candidates.add(member);
      }
   }
}
