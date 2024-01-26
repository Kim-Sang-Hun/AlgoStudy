import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static long minAnswer = (long)1e10, maxAnswer = 0;
	static char[] a;
	static boolean vis[] = new boolean[10];
	
	static void bt(int idx, long number) {
		if(idx == n) {
			if(number < minAnswer) {
				minAnswer = number;
			}
			else if(number > maxAnswer) {
				maxAnswer = number;
			}
			return;
		}
		int num = (int) (number % 10);
		for(int i = 0;i < 10; ++i) {
			if(vis[i]) continue;
			if((a[idx] == '<' && i > num) || (a[idx] == '>' && i < num)) {
				vis[i] = true;
				bt(idx + 1, number * 10 + i);
				vis[i] = false;
			}
		}
	}
	
	static void solution() throws IOException{
		for(int i = 0; i < 10; ++i) {
			vis[i] = true;
			bt(0, i);
			vis[i] = false;
		}
		String max = String.valueOf(maxAnswer);
		System.out.println(max);
		String min = String.valueOf(minAnswer), willAdd = "";
		while(max.length() > min.length() + willAdd.length()) {
			willAdd += "0";
		}
		System.out.println(willAdd + min);
	}
	
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		a = new char[n];
		for(int i = 0;i < n; ++i) {
			a[i] = st.nextToken().charAt(0);
		}
		solution();
	}

}
