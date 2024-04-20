import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 작업의 개수
        Task[] tasks = new Task[N + 1];
        int[] inDegree = new int[N + 1];

        // 그래프 초기화
        for (int i = 1; i <= N; i++) {
            String[] line = br.readLine().split(" ");
            int time = Integer.parseInt(line[0]); // 작업을 완료하는데 걸리는 시간
            int count = Integer.parseInt(line[1]); // 선행 관계의 개수

            tasks[i] = new Task(time);

            // 선행 관계
            for (int j = 0; j < count; j++) {
                int idx = Integer.parseInt(line[2 + j]);
                tasks[idx].list.add(i);
                inDegree[i]++;
            }
        }

        // 위상 정렬을 위한 큐
        Queue<Integer> queue = new LinkedList<>();

        // 진입 차수가 0인 작업들을 큐에 추가
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 각 작업의 최소 완료 시간 계산
        int[] time = new int[N + 1];

        while (!queue.isEmpty()) {
            int current = queue.poll();
            Task currentTask = tasks[current];

            // 현재 작업을 완료하는데 필요한 시간 계산
            time[current] += currentTask.time;

            // 현재 작업과 관련된 작업 탐색
            for (int task : currentTask.list) {
                time[task] = Math.max(time[task], time[current]);

                inDegree[task]--;

                if (inDegree[task] == 0) {
                    queue.offer(task);
                }
            }
        }

        // 최소 완료 시간 중 최대값
        int maxTime = Arrays.stream(time).max().getAsInt();
        System.out.println(maxTime);
    }

    static class Task {
        int time;
        List<Integer> list;

        Task(int time) {
            this.time = time;
            this.list = new ArrayList<>();
        }
    }
}
