import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static boolean[] isVisited;
	static int count;
	static List<List<Integer>> list;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			isVisited = new boolean[N+1];  
			count = 0;
			
			// 리스트 초기화
			list = new LinkedList<>();
			for(int i = 0; i <= N; i++) {
				list.add(new LinkedList<>());
			}
			
			// 리스트 입력
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				
				list.get(num1).add(num2);
				list.get(num2).add(num1);
			}
			
			// 탐색
			for(int i = 1; i <= N; i++) {
				if(!isVisited[i]) {
					count++;
					func(i);
				}
			}
			System.out.println("#" + tc + " " + count);
		}
	}
	
	static void func(int idx) {
		isVisited[idx] = true;
		
		for(int num : list.get(idx)) {
			if(!isVisited[num]) {
				func(num);
			}
		}
	}
}
