package april2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class JUN10597_순열장난_김주희 {
	static String input;
	static int L, N, answer[];
	static boolean visited[], flag;
	
	private static void backtrack(int depth, int pt) {
		if(flag) return;
		
		if(depth >= N) {
			flag = true; // 하나 만들었으면 그만하려고
			return;
		}
		
		int cur = input.charAt(pt) - '0';
		if(cur == 0) return; // 한 자리 0은 없고 두 자리라도 10,20 같은 경우는 되는데 01,02는 안 되니까
		
		// 한 자리로 들어가는 경우
		if(!visited[cur]) {
			visited[cur] = true;
			answer[depth] = cur;
			backtrack(depth + 1, pt + 1);
			visited[cur] = false;
		}
		
		// 두 자리로 들어가는 경우
		if(pt >= L-1) return; // 뒤에 남은 수 없으면 못 함
		if(flag) return; // 이미 끝났으면 안 함
		
		int digit2 = (cur)*10 + (input.charAt(pt+1) - '0');

		if( digit2 <= N && !visited[digit2]) { // 만들어진 숫자가 최대 숫자보다 크면 못 넣음
			visited[digit2] = true;
			answer[depth] = digit2;
			backtrack(depth + 1, pt + 2);
			visited[digit2] = false;
		}

	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		L = input.length();
		
		// 총 숫자 개수 계산
		if(L <= 9) N = L;
		else N = (L - 9) / 2 + 9;
		
		visited = new boolean[N+1];
		answer = new int[N];
		
		backtrack(0, 0);
		
		for (int i = 0; i < N; i++) {
			System.out.printf("%d ", answer[i]);
		}
		
	}

}
