import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class JUN16922_로마숫자만들기_김주희 {
	static int N;
	static int[] nums = new int[] {1,5,10,50};
	static Set<Integer> sums;
	
	private static void combi(int start, int depth, int sum) {
		if(depth == N) {
			sums.add(sum);
			return;
		}
		
		for(int i = start; i < 4; i++) {
			combi(i, depth + 1, sum + nums[i]);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		sums = new HashSet<>();
		combi(0, 0, 0);
		
		System.out.println(sums.size());

	}

}
