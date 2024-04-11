package april2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
// Long 주의할 문제
public class JUN2143_두배열의합_김주희 {
	static int T, N, M, n[], m[];
	
	private static HashMap<Integer, Long> makeSum(int[] target) {
		HashMap<Integer, Long> map = new HashMap<Integer, Long>();

		int L = target.length;
		
		for (int i = 0; i < L; i++) {
			
			
			if(map.containsKey(target[i])) {
				map.replace(target[i], map.get(target[i])+1);
			}else{
				map.put(target[i], (long) 1);
				
			}
			
			for (int j = 0; j < i; j++) {
				if(i==j) continue;
				int value = target[i] - target[j];
				
				if(map.containsKey(value)) {
					map.replace(value, map.get(value)+1);
				}else{
					map.put(value, (long) 1);
				}
				
			}
		}
		
		return map;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		n = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			n[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		m = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}
		
		// 누적합 배열 만들기
		for (int i = 1; i < N; i++) {
			n[i] += n[i-1];
		}
		
		for (int i = 1; i < M; i++) {
			m[i] += m[i-1];
		}

		// key값이 합이 되는 경우의 수 value 
		HashMap<Integer, Long> sumN = makeSum(n);
		HashMap<Integer, Long> sumM = makeSum(m);
		

		// 계산
		long answer = 0;
		for (int keyN : sumN.keySet()) {

			if(sumM.containsKey(T-keyN))
				answer += sumN.get(keyN)*sumM.get(T-keyN);
		}

		System.out.println(answer);
		
	}

}
