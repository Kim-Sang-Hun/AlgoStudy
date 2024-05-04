import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
circle이 존재하는지 확인한다
첫 바퀴때는 방문처리하고
두 바퀴때는 circle이 존재하는 것들을 made처리한다
(시작해서 circle은 발생했지만 시작점에 돌아오지 않는 경우 방지)

 */
public class JUN9466_텀프로젝트 {

    static int count;
    static int[] arr;
    static boolean[] visited, made;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            count = 0;
            int n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            visited = new boolean[n+1];
            made = new boolean[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < arr.length; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
                if (arr[j] == j) {
                    made[j] = true;
                    count++;
                }
            }
            for (int j = 1; j < arr.length; j++) {
                if (made[j]) continue;
                findCircle(j);
            }
            sb.append(n - count).append(System.lineSeparator());
        }
        System.out.println(sb.toString().trim());
    }

    private static void findCircle(int cur) {
        if (visited[cur]) { // 이미 방문한 곳에 도착한 경우(circle이 존재하는 경우)
            made[cur] = true;
            count++;
        } else {
            visited[cur] = true;
        }

        int next = arr[cur];
        if (!made[next]) { // circle이 존재하는지 다음 것도 확인
            findCircle(next);
        }
        
        made[cur] = true;
    }
}
