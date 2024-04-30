import java.io.*;
import java.util.*;

public class isayaksh {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[][] connect = new boolean[N+1][N+1];
		
		for(int n = 1; n < N+1; n++) {
			connect[n][n] = true;
		}
		
		for(int y = 1; y < N+1; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 1; x < N+1; x++) {
				if(st.nextToken().equals("1")) {
					connect[y][x] = true;
					connect[x][y] = true;
				}
			}
		}
		
		int[] target = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		
		// 플로이드워셜
		for(int x = 1; x < N+1; x++) {
			for(int y = 1; y < N+1; y++) {
				for(int z = 1; z < N+1; z++) {
					if(connect[y][x] && connect[x][z]) connect[y][z] = true;
				}
			}
		}
		
		for(int m = 0; m < M-1; m++) {
			if(!connect[target[m]][target[m+1]]) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
		
	}
	
}
