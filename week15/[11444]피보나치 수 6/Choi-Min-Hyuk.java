import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class BOJ1444_피보나치수6_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long n;
	static final long MOD = 1_000_000_007;
	static final long[][] origin = {{1, 1}, {1, 0}};
	
	public static void main(String[] args) throws Exception {
		n = Long.parseLong(br.readLine());
		long[][] A = {{1, 1}, {1, 0}};
		long result = pow(A, n - 1)[0][0];
		
		System.out.println(result);
	}
	
	static long[][] pow(long[][] A, long exp) {
		if(exp == 1 || exp == 0)
			return A;
		
		long[][] ret = pow(A, exp / 2);
		
		ret = multiply(ret, ret);
		if((exp & 1) == 1) ret = multiply(ret, origin);
		
		return ret;
	}
	
	static long[][] multiply(long[][] o1, long[][] o2) {
		long[][] ret = new long[2][2];
		
		ret[0][0] = (o1[0][0] * o2[0][0] + o1[0][1] * o2[1][0]) % MOD;
		ret[0][1] = (o1[0][0] * o2[0][1] + o1[0][1] * o2[1][1]) % MOD;
		ret[1][0] = (o1[1][0] * o2[0][0] + o1[1][1] * o2[1][0]) % MOD;
		ret[1][1] = (o1[1][0] * o2[0][1] + o1[1][1] * o2[1][1]) % MOD;
		
		return ret;
	}
}
