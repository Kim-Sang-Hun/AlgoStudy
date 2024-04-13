import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2143_두배열의합_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static long T;
	static int n, m;
	
	public static void main(String[] args) throws Exception {
		T = Long.parseLong(br.readLine());
		
		n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			
			if (i != 0)
				A[i] += A[i - 1];
		}

		m = Integer.parseInt(br.readLine());
		int[] B = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
			
			if (i != 0)
				B[i] += B[i - 1];
		}
		
		// 누적합 이용해서 부 배열들의 합을 담은 배열 만들기
		int aSize = n * (n + 1) / 2;
		long[] aPartArraySums = new long[aSize];
		int index = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int aValue = A[j];
				
				if (i > 0)
					aValue -= A[i - 1];
				
				aPartArraySums[index++] = aValue;
			}
		}
		
		int bSize = m * (m + 1) / 2;
		long[] bPartArraySums = new long[bSize];
		index = 0;
		for (int i = 0; i < m; i++) {
			for (int j = i; j < m; j++) {
				int bValue = B[j];
				
				if (i > 0)
					bValue -= B[i - 1];
				
				bPartArraySums[index++] = bValue;
			}
		}

		Arrays.sort(aPartArraySums);
		Arrays.sort(bPartArraySums);
		
		int aPointer = 0;
		int bPointer = bSize - 1;
		long count = 0;
		
		while (aPointer < aSize && bPointer > -1) {
			long aValue = aPartArraySums[aPointer];
			long bValue = bPartArraySums[bPointer];
			long sum = aValue + bValue;
			
			if (sum == T) {
				long aCount = 0;
				while (aPointer < aSize && aValue == aPartArraySums[aPointer]) {
					aPointer++;
					aCount++;
				}
				
				long bCount = 0;
				while (bPointer > -1 && bValue == bPartArraySums[bPointer]) {
					bPointer--;
					bCount++;
				}
				
				count += aCount * bCount;
			}
			
			if (sum > T) {
				bPointer--;
			}
			else if (sum < T) {
				aPointer++;
			}
		}
		
		System.out.print(count);
	}
}
