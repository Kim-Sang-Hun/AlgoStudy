import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
석순과 종유석이 번갈아 나오므로 해당 위치(석순의 경우 끝점, 종유석의 경우에도 끝점)에 값을 더해준다.
위치가 0일 때 부수는 석순은 무조건 N/2이다. 해당 값을 min으로, 현재 장애물 개수를 N/2개로 잡고 올라가면서 확인한다.
위치가 M일 때 석순 배열의 값이 K라면 해당 위치부터 장애물이 K개 사라진다는 말이므로 현재 장애물 개수에서 빼준다.
위치가 M일 때 종유석 배열의 값이 T라면 해당 위치부터 장애물이 T개 추가된다는 말이므로 T만큼 현재 장애물 개수에 더해준다.
min값보다 그 값이 작다면 cnt를 1로 초기화하고 min값을 바꿔주고
min값과 그 값이 같다면 cnt를 늘려준다.
 */
public class JUN3020_개똥벌레 {

   static int min, cnt;
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int H = Integer.parseInt(st.nextToken());

      int[] obUp = new int[H + 1];
      int[] obDown = new int[H + 1];
      min = N / 2;
      cnt = 1;
      int obstacle = N / 2;
      boolean even = true;
      for (int i = 0; i < N; i++) {
         int height = Integer.parseInt(br.readLine());
         if (even) {
            obDown[height]++;
         } else {
            obUp[H - height]++;
         }
         even = !even;
      }

      for (int i = 1; i < H; i++) {
         obstacle = obstacle - obDown[i] + obUp[i];
         if (obstacle < min) {
            min = obstacle;
            cnt = 1;
         } else if (obstacle == min) {
            ++cnt;
         }
      }
      System.out.println(min + " " + cnt);
   }
}
