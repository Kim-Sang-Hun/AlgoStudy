import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int[] a, b;
	static int t, n, m;
    static long answer = 0;
	static HashMap<Long, Integer> prefix = new HashMap<>();
	
	static void input() throws IOException {
		t = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		a = new int[n];
		for(int i = 0;i < n; ++i) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		b = new int[m];
		for(int i = 0;i < m; ++i) {
			b[i] = Integer.parseInt(st.nextToken());
		}
	}

  //누적 합 2개 만들어서 했습니다.
  //b 배열의 합산을 통해 얻는 t 값을 구할 때, 
  //두 값을 더했을 때 얻을 t 값을 바로 구할 수 없어 t - sum으로 특정 값을 찾아주는 방식으로 진행했습니다.
	static void solution() throws IOException{
		for(int i = 0;i < n; ++i) {
			long sum = 0;
			for(int j = i;j < n; ++j) {
				sum += a[j];
				if(prefix.get(sum) == null) {
					prefix.put(sum, 1);
				} else {
					prefix.put(sum, prefix.get(sum) + 1);
				}
			}
		}
		for(int i = 0;i < m; ++i) {
			long sum = 0;
			for(int j = i;j < m; ++j) {
				sum += b[j];
				if(prefix.get(t - sum) != null) {
					answer += prefix.get(t - sum);
				}
			}
		}
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
}
