import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN3020_개똥벌레_김주희 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[] height = new int[H];
		int[] plus = new int[H+1];
		int[] minus = new int[H+1];
		
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(br.readLine());
			if(i%2 == 0) { // 석순
				minus[h+1]++;
				height[0]++;}
			else // 종유석
				plus[H-h+1]++;
		}

		for (int i = 1; i < H; i++) {
			height[i] = height[i-1] + plus[i] - minus[i]; 
		}
		
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < H; i++) {
			minValue = Math.min(minValue, height[i]);
		}
		
		int caseCnt = 0;
		for (int i = 0; i < H; i++) {
			if(height[i] == minValue) caseCnt++;
		}
		
		System.out.printf("%d %d",minValue, caseCnt);

	}

}
