import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, answerY, answerX;
	static char answerBlock;
	static int[] dirY = {0, 1, 0, -1};
	static int[] dirX = {1, 0, -1, 0};
	static char[][] map;
	static int[][] gas;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		gas = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// Z와 M이 존재하는지 확인
		boolean isZ = false;
		boolean isM = false;
		
		// 가스관의 방향별로 값을 더해줌
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'Z') {
					isZ = true;
				}
				if (map[i][j] == 'M') {
					isM = true;
				}
				if (map[i][j] == '|') {
					++gas[i + 1][j];
					++gas[i - 1][j];
				}
				if (map[i][j] == '-') {
					++gas[i][j - 1];
					++gas[i][j + 1];
				}
				if (map[i][j] == '+') {
					++gas[i + 1][j];
					++gas[i - 1][j];
					++gas[i][j - 1];
					++gas[i][j + 1];
				}
				if (map[i][j] == '1') {
					++gas[i][j + 1];
					++gas[i + 1][j];
				}
				if (map[i][j] == '2') {
					++gas[i][j + 1];
					++gas[i - 1][j];
				}
				if (map[i][j] == '3') {
					++gas[i - 1][j];
					++gas[i][j - 1];
				}
				if (map[i][j] == '4') {
					++gas[i + 1][j];
					++gas[i][j - 1];
				}
			}
		}
		
		// 만약 가스가 와야 하는 곳인데 .이라면 해커가 지운 곳이므로 좌표는 이곳.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (gas[i][j] != 0 && map[i][j] == '.') {
					answerY = i;
					answerX = j;
				}
			}
		}

		int count = gas[answerY][answerX];
		List<Integer> gasPipe = new ArrayList<>();
		
		// 지옥의 조건문 시작 - gas값이 4라면 +, 2라면 연결된 가스파이프 확인, 1이라면 연결된 가스파이프와 Z 또는 M 확인
		if (!isZ) {
			answerBlock = 'Z';
		} else if (!isM) {
			answerBlock = 'M';
		} else if (count == 4) {
			answerBlock = '+';
		} else if (count == 2) {
			for (int i = 0; i < 4; i++) {
				int nY = answerY + dirY[i];
				int nX = answerX + dirX[i];
				if (nY < 0 || nY >= N || nX < 0 || nX >= M || map[nY][nX] == '.') continue;
				if (i % 2 == 0) {
					if (map[nY][nX] == '-' || map[nY][nX] == '+') {
						gasPipe.add(i);
					} 
					if (i == 0 && (map[nY][nX] == '4' || map[nY][nX] == '3')) {
						gasPipe.add(i);
					} 
					if (i == 2 && (map[nY][nX] == '1' || map[nY][nX] == '2')) {
						gasPipe.add(i);
					}
				} else {
					if (map[nY][nX] == '|' || map[nY][nX] == '+') {
						gasPipe.add(i);
					} 
					if (i == 1 && (map[nY][nX] == '2' || map[nY][nX] == '3')) {
						gasPipe.add(i);
					} 
					if (i == 3 && (map[nY][nX] == '1' || map[nY][nX] == '4')) {
						gasPipe.add(i);
					}
				}
			}
		} else if (count == 1) {
			for (int i = 0; i < 4; i++) {
				int nY = answerY + dirY[i];
				int nX = answerX + dirX[i];
				if (map[nY][nX] == '.') continue;
				if (map[nY][nX] == 'Z' || map[nY][nX] == 'M') {
					gasPipe.add(i);
				} else if (i % 2 == 0) {
					if (map[nY][nX] == '-' || map[nY][nX] == '+') {
						gasPipe.add(i);
					} 
					if (i == 0 && (map[nY][nX] == '4' || map[nY][nX] == '3')) {
						gasPipe.add(i);
					} 
					if (i == 2 && (map[nY][nX] == '1' || map[nY][nX] == '2')) {
						gasPipe.add(i);
					}
				} else {
					if (map[nY][nX] == '|' || map[nY][nX] == '+') {
						gasPipe.add(i);
					} 
					if (i == 1 && (map[nY][nX] == '2' || map[nY][nX] == '3')) {
						gasPipe.add(i);
					} 
					if (i == 3 && (map[nY][nX] == '1' || map[nY][nX] == '4')) {
						gasPipe.add(i);
					}
				}
			}
		}
		
		// 들어온 값(0, 1, 2, 3)을 확인하고 맞는 블록을 저장
		if (!gasPipe.isEmpty()) {
			int a = gasPipe.get(0);
			int b = gasPipe.get(1);
			if (a == 0) {
				if (b == 1) {
					answerBlock = '1';
				} else if (b == 2) {
					answerBlock = '-';
				} else if (b == 3) {
					answerBlock = '2';
				}
			} else if (a == 1) {
				if (b == 2) {
					answerBlock = '4';
				} else if (b == 3) {
					answerBlock = '|';
				}
			} else {
				answerBlock = '3';
			}
		}
		
		System.out.println((answerY + 1) + " " + (answerX + 1) + " " + answerBlock);
	}

}
