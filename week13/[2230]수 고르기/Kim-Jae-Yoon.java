import java.util.*;
import java.io.*;
/*
  Title: 수 고르기
  Tier: Gold 5
  Algorithm: Two Pointer
  Constraint: 2 Second, 128MB
*/
public class Main {

	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Vector<Integer> a = new Vector<>();
	static int n, m;
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for(int i = 0;i < n; ++i) {
			a.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(a);
		query();
		System.out.print(sb);
	}
	
	static void query() throws IOException {
		int answer = (int)2e9;
		int s = 0, e = 0;
		while(e < n){
			int val = a.get(e) - a.get(s);
			if(val < m) {
				++e;
				continue;
			}
			if(val == m) {
				sb.append(m);
				return;				
			}
			if(val < answer) {
				answer = val;
			}
			++s;
		}
		sb.append(answer);
	}
}
