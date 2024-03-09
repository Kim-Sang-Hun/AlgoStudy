import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN3190_뱀_김주희 {
	static int N,K,L, map[][];
	static Turn[] turns;
	static int dr[] = new int[] {0, 1, 0, -1}; // 인덱스 +1이면 오른쪽회전, -1이면 왼쪽 회전
	static int dc[] = new int[] {1, 0, -1, 0};
	
	static class Turn{
		int time;
		char dir;
		
		public Turn(int time, char dir) {
			this.time = time;
			this.dir = dir;
		}
	}
	
	private static int changeDir(int dirNow, char flag) {
		if(flag == 'D') {
			return (dirNow+1)%4;
		}else {
			if(dirNow-1 < 0) return dirNow-1+4;
			else return dirNow - 1;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		int[][] dirMap = new int[N+1][N+1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		Queue<Turn> turns = new LinkedList<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			turns.add(new Turn(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}
		
		int time = 0;
		map[1][1] = 2;
		int dirNow = 0;
		int headR = 1;
        int headC = 1;
		int tailR = 1;
        int tailC = 1;

        while (true) {
            // 그 시간에 방향 전환 하는지
            if (!turns.isEmpty() && turns.peek().time == time) {
                dirNow = changeDir(dirNow, turns.poll().dir);
            }

            // 다음 위치
            dirMap[headR][headC] = dirNow; // 이동한 방향 기록
            headR += dr[dirNow];
            headC += dc[dirNow];
            

            // 범위 넘어가거나 몸에 닿으면 끝남
            if (headR < 1 || headR > N || headC < 1 || headC > N || map[headR][headC] == 2) break;

            // 사과 없으면 꼬리 줄어든다
            if (map[headR][headC] != 1) {
                map[tailR][tailC] = 0;
                
                int move = dirMap[tailR][tailC];
 
                tailR += dr[move];
                tailC += dc[move];
            }

            map[headR][headC] = 2;
            time++;
        }

        System.out.println(time + 1);
    }
}
