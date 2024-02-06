import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int[][] mbtis;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 입력
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			
			// 학생 수 입력
			N = Integer.parseInt(br.readLine());
			
			// mbti 입력
			mbtis = new int[N][4];
			String[] inputMbti = br.readLine().split(" ");
			
			// 학생 수 32명 초과 시 어차피 0이므로 continue
			if(N > 32) {
				sb.append(0).append("\n");
				continue;
			}
			
			for(int i = 0; i < N; i++) {
				setMbti(i, inputMbti[i]);
			}
			
			// 최소 거리 합 계산
			int minDistance = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				for(int j = i+1; j < N; j++) {
					for(int r = j+1; r < N; r++) {
						int sumD = getDistance(i,j) + getDistance(j,r) + getDistance(r,i);
						minDistance = Math.min(minDistance, sumD);
					}
				}
			}
			sb.append(minDistance).append("\n");
		}
		System.out.println(sb);
	}
	
	static int getDistance(int student1, int student2) {
		int distance = 0;
		for(int i = 0; i < 4; i++) {
			distance += mbtis[student1][i] ^ mbtis[student2][i];
		}
		return distance;
	}
	
	static void setMbti(int row, String mbti) {
		int idx = 0;
		for(char c : mbti.toCharArray()) {
			if(c == 'E' || c == 'S' || c == 'T' || c == 'J') {
				mbtis[row][idx++] = 1;
			}
			else {
				mbtis[row][idx++] = 0;
			}
		}
	}
}
