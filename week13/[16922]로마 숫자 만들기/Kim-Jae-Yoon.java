import java.io.*;
import java.util.*;
/*
  Title: 로마 숫자 만들기
  Tier: Silver 3
  Algorithm: BackTracking
  Constraint: 2 Second, 512MB
  Comment: 순열, 조합, 부분집합 코드로 풀 수 있을 것 같긴 합니다.
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb = new StringBuffer();
	static StringTokenizer st;
	static int n, answer = 0;
	static boolean[] num;
	static final int[] val = {1, 5, 10, 50};
	
	static void input() throws IOException {
		n = Integer.parseInt(br.readLine());
		num = new boolean[1001];
	}
	
	static void bt(int cnt, int idx, int sum) {
		if(cnt == n) {
			if(!num[sum]) ++answer;
			num[sum] = true; 
			return;
		}
		for(int i = idx;i < 4; ++i) {
			int adder = sum + val[i];
			bt(cnt + 1, i, adder);
		}
	}
	
	static void solution() {
		bt(0, 0, 0);
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
}
