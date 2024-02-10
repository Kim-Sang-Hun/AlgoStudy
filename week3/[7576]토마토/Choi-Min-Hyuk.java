import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String LineSeparator = System.lineSeparator();
	static StringTokenizer st;
    static int M, N;
    static int[][] tomato;
    static int[] dRow = { -1, 1,  0, 0 };
    static int[] dCol = {  0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        tomato = new int[N][M];

        // 익지 않은 토마토 개수
        int unripeTomatoNum = 0;
        // 출력할 정답인 날짜
        int day = 0;
        // bfs를 위한 익은 토마토의 위치를 담을 큐
        Queue<int[]> ripeTomatoLocationQueue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());

                if(tomato[i][j] == 1)
                    ripeTomatoLocationQueue.add(new int[] {i, j});
                
                else if(tomato[i][j] == 0)
                    unripeTomatoNum++;
            }
        }

        /*
         * bfs로 큐에 들어간 익은 토마토에 위치에서 탐색
         * 익지 않은 토마토가 있다면 익은 토마토로 바꾸고 큐에 위치 삽입
         * 큐에 더 이상 탐색할 토마토가 없을 때 익지 않은 토마토가 남아 있는지 확인 후 결과 출력
         */
        while (unripeTomatoNum > 0 && !ripeTomatoLocationQueue.isEmpty()) {
            // 날짜 단위로 세기 위해서 현재 큐에 들어있는 익은 토마토를 다 확인 후 날짜 + 1
            int tomatoNum = ripeTomatoLocationQueue.size();
            for (int i = 0; i < tomatoNum; i++) {
                int[] currentLocation = ripeTomatoLocationQueue.poll();

                for (int j = 0; j < 4; j++) {
                    int nRow = currentLocation[0] + dRow[j];
                    int nCol = currentLocation[1] + dCol[j];
                    if (nRow < 0 || N <= nRow || nCol < 0 || M <= nCol || tomato[nRow][nCol] != 0)
                        continue;
                    
                    unripeTomatoNum--;
                    tomato[nRow][nCol] = 1;
                    ripeTomatoLocationQueue.add(new int[] {nRow, nCol});
                }
            }
    
            day++;
        }

        if(unripeTomatoNum == 0)
            System.out.println(day);
        else
            System.out.println(-1);
    }
}
