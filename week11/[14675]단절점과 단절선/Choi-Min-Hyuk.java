package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14675_단절점과단절선_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static String lineSeparator = System.lineSeparator();
	static int N, q, adjNodeCounts[];
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		adjNodeCounts = new int[N + 1];
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjNodeCounts[a]++;
			adjNodeCounts[b]++;
		}
		
		q = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if (t == 1) {
				if (adjNodeCounts[k] > 1)
					sb.append("yes").append(lineSeparator);
				else
					sb.append("no").append(lineSeparator);
			}
			
			else if (t == 2) {
				sb.append("yes").append(lineSeparator);
			}
		}

		System.out.print(sb);
	}
}
