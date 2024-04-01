package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 투포인터..
public class JUN12100_2048_김주희 {
	static int N, map[][], result[], origin[][], answer;
	
	private static void left() {
		for (int i = 0; i < N; i++) {
			int p1 = 0;
			int p2 = 0;
			int idx = 0; // 값 새로 채우기 위한 idx
			
			while(p1 < N-1 && p2 < N) {
				while( p1 < N && map[i][p1] == 0) p1++; // 0이 아닌 값 찾을 때까지 포인터 갱신
				p2 = p1 + 1;
				while( p2 < N &&map[i][p2] == 0) p2++;
				
				if(p1 > N || p2 > N) break; // 넘어가면 끝냄
				
				if(p2 < N && map[i][p1] == map[i][p2]) { // 두 값이 같으면 *2한 거 새로 넣고 포인터 갱신
					map[i][idx] = map[i][p1]*2;
					idx++;
					p1 = p2 + 1;
					p2 = p1 + 1;
				}else { // 아니면 현재 p2를 p1으로 포인터 갱신
					map[i][idx] = map[i][p1];
					idx++;
					p1 = p2;
					p2 = p1+1;
				}
			}
			
			if(p1 < N) { // p1에 값 남은 상태에서 p2가 범위 넘어가서 끝났을 경우 p1값으로 채움
				map[i][idx] = map[i][p1];
				idx++;
			}
			
			for (int j = idx; j < N; j++) { // 나머지 칸 0으로
				map[i][j] = 0;
			}
		}
	}
	
	private static void right() {
		for (int i = 0; i < N; i++) {
			int p1 = N-1;
			int p2 = 0;
			int idx = N-1;
			
			while(p1 >= 1 && p2 >= 0) {
				while( p1 >= 0 && map[i][p1] == 0) p1--;
				p2 = p1 - 1;
				while( p2 >= 0 &&map[i][p2] == 0) p2--;
				
				if(p1 < 0 || p2 < 0) break;
				
				if(p2 >= 0 && map[i][p1] == map[i][p2]) {
					map[i][idx] = map[i][p1]*2;
					idx--;
					p1 = p2 - 1;
					p2 = p1 - 1;
				}else {
					map[i][idx] = map[i][p1];
					idx--;
					p1 = p2;
					p2 = p1-1;
				}
			}
			
			if(p1 >= 0) {
				map[i][idx] = map[i][p1];
				idx--;
			}
			
			for (int j = idx; j >= 0; j--) {
				map[i][j] = 0;
			}
		}
	}
	
	private static void down() {
		for (int j = 0; j < N; j++) {
			int p1 = N-1;
			int p2 = 0;
			int idx = N-1;
			
			while(p1 >= 1 && p2 >= 0) {
				while( p1 >= 0 && map[p1][j] == 0) p1--;
				p2 = p1 - 1;
				while( p2 >= 0 &&map[p2][j] == 0) p2--;
				
				if(p1 < 0 || p2 < 0) break;
				
				if(p2 >= 0 && map[p1][j] == map[p2][j]) {
					map[idx][j] = map[p1][j]*2;
					idx--;
					p1 = p2 - 1;
					p2 = p1 - 1;
				}else {
					map[idx][j] = map[p1][j];
					idx--;
					p1 = p2;
					p2 = p1-1;
				}
			}
			
			if(p1 >= 0) {
				map[idx][j] = map[p1][j];
				idx--;
			}
			
			for (int i = idx; i >= 0; i--) {
				map[i][j] = 0;
			}
		}
	}
	
	private static void up() {
		for (int j = 0; j < N; j++) {
			int p1 = 0;
			int p2 = 0;
			int idx = 0;
			
			while(p1 < N-1 && p2 < N) {
				while( p1 < N && map[p1][j] == 0) p1++;
				p2 = p1 + 1;
				while( p2 < N &&map[p2][j] == 0) p2++;
				
				if(p1 > N || p2 > N) break;
				
				if(p2 < N && map[p1][j] == map[p2][j]) {
					map[idx][j] = map[p1][j]*2;
					idx++;
					p1 = p2 + 1;
					p2 = p1 + 1;
				}else {
					map[idx][j] = map[p1][j];
					idx++;
					p1 = p2;
					p2 = p1+1;
				}
			}
			
			if(p1 < N) {
				map[idx][j] = map[p1][j];
				idx++;
			}
			
			for (int i = idx; i < N; i++) {
				map[i][j] = 0;
			}
		}
	}
	
	private static void game() {
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) { // 새로운 map에서 실행
			for (int j = 0; j < N; j++) {
				map[i][j] = origin[i][j];
			}
		}
		
		for(int order : result) {
			if (order == 0) left();
			else if (order == 1) right();
			else if (order == 2) up();
			else if (order == 3) down();
		}
		
		int tempMax = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tempMax = Math.max(tempMax, map[i][j]);
			}
		}
		answer = Math.max(answer, tempMax);
	}
	
	private static void perm(int depth) { // 0-4에서 중복을 허용하여 5개 뽑음
		if(depth == 5) {
			game();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			result[depth] = i;
			perm(depth+1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		origin = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 게임
		result = new int[5];
		perm(0);
		// 출력
		System.out.println(answer);
	}
}
