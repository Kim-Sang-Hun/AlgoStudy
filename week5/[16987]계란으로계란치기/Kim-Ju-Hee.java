package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN16987 {
	static int N, count, answer;
	static Egg[] eggs;
	static boolean broken[];
	
	static class Egg {
		int strong, weight;

		public Egg(int strong, int weight) {
			this.strong = strong;
			this.weight = weight;
		}
	}
	
	private static void backtrack(int idx) {
		
		if (count == N || idx == N) { // 계란이 다 깨졌거나 마지막 계란일 때 종료
			answer = Math.max(answer, count);
			return;
		}
		
		if (eggs[idx].strong <= 0 || count == N-1) { // 내가 깨져있거나 나머지 다 깨졌으면 안 깨고 넘어감
			backtrack(idx + 1);
			answer = Math.max(answer, count);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(i == idx || broken[i]) continue; // 같은 계란이거나 깨진 계란이면
			
			eggs[i].strong -= eggs[idx].weight;
			eggs[idx].strong -= eggs[i].weight; // 계란 서로 깨기
			
			if (eggs[idx].strong <= 0 && eggs[i].strong <= 0) { // 둘다 깨짐
				broken[i] = broken[idx] = true;
				count += 2;
				backtrack(idx + 1);
				broken[i] = broken[idx] = false;
				count -= 2;
			}else if(eggs[idx].strong <= 0) { // 나만 깨짐
				broken[idx] = true;
				count += 1;
				backtrack(idx + 1);
				broken[idx] = false;
				count -= 1;
			}else if(eggs[i].strong <= 0) { // 상대만 깨짐
				broken[i] = true;
				count += 1;
				backtrack(idx + 1);
				broken[i] = false;
				count -= 1;
			}else { // 둘다 안 깨짐
				backtrack(idx + 1);
			}
			
			eggs[i].strong += eggs[idx].weight;
			eggs[idx].strong += eggs[i].weight; // 원상복구
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];
		broken = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// 백트래킹
		backtrack(0);
		
		// 출력
		System.out.println(answer);
	}
}
