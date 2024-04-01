import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        //입력받은 배열을 시작시간 순서대로 정렬합니다.
        Arrays.sort(arr, Comparator.comparingInt(i -> i[0]));
        // 종료시간을 기준으로 하는 PriorityQueue를 만듭니다.
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
        int time;
        int answer = -1;
        //시작시간 순서대로 queue에 집어넣으며 현재시간은 시작시간으로 바꿔줍니다.
        //만약 queue 맨 위의 종료시간이 현재시간보다 작거나 같다면 수업이 종료되었다는 뜻이므로 빼줍니다.
        //queue의 사이즈가 바로 현재진행중인 수업의 개수이므로 max값을 계속 확인해줍니다.
        for (int i = 0; i < N; i++) {
            queue.add(arr[i]);
            time = arr[i][0];
            while (!queue.isEmpty() && queue.peek()[1] <= time) {
                queue.poll();
            }
            answer = Math.max(answer, queue.size());
        }
        System.out.println(answer);
    }
}
