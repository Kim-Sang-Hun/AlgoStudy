import java.util.*;
import java.io.*;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m;
	static char[] a;
	
	static boolean isRight(String s) {
		int p = 0, q = 0;
		for(char c : s.toCharArray()) {
			if(c == 'a' || c == 'i' || c == 'e' || c == 'o' || c == 'u') {
				++p;
			}
			else {
				++q;
			}
		}
		if(p >= 1 && q >= 2) return true;
		return false;
	}
	
	static void rec(int idx, String s) {
		if(s.length() == n) {
			if(isRight(s)) {
				sb.append(s).append("\n");
			}
			return;
		}
		for(int i = idx + 1;i < m; ++i) {
			rec(i, s + a[i]);
		}
	}
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		a = new char[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; ++i) {
			a[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(a);
		rec(-1, "");
		sb.deleteCharAt(sb.length() - 1);
		System.out.print(sb);
	}
}
