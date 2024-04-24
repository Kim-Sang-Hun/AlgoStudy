import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN1025_제곱수찾기_김주희 {
	static int N, M, answer;
	static char map[][];
	
	private static StringBuilder makeStr(int diffI, int diffJ, int curI, int curJ) {
		StringBuilder sb = new StringBuilder();
		
		while(curI >= 0 && curI <= N-1 && curJ >= 0 && curJ <= M-1) {
			sb.append(map[curI][curJ]);
			
			curI += diffI;
			curJ += diffJ;
		}
		return sb;
	}
	
	private static void search(String s) {
		int L = s.length();
		
		for (int i = 0; i < L; i++) {
			for (int j = 0; j <= L; j++) {
				if(i >= j) continue;
				
				check(Integer.parseInt(s.substring(i, j)));
			}
		}
	}
	
	private static void check(int target) {
		if(target == Math.pow((int)Math.sqrt(target), 2)) { // 제곱수이면
			answer = Math.max(target, answer);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		answer = -1;
		for (int i = -N+1; i < N; i++) { // i는 행의 공차
			for (int j = -M+1; j < M; j++) { // j는 열의 공차
				
				for (int k = 0; k < N; k++) {
					for (int k2 = 0; k2 < M; k2++) {
						check(map[k][k2]-'0');
						
						if(i==0 && j==0) continue;
						
						StringBuilder sb = makeStr(i,j,k,k2);
						search(sb.toString());	
		        search(sb.reverse().toString());
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}
