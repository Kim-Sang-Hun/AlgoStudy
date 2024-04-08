import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static String s, answer = "";
	static int len;
	static boolean[] vis = new boolean[51];
	
	static void input() throws IOException {
		s = br.readLine();
		len = s.length();
	}
	
	static void bt(String ans, int idx) {
		if(!answer.isEmpty()) return;
		if(s.length() == idx) {
			//2자리수에 대한 계산 편의를 위해 간소화
			int aLen = len;
			if(len >= 10) {
				aLen = ((aLen - 9) >> 1) + 9;
			}
      //1부터 n까지 연속 방문이 깨지면 당연히 문제 조건으로 성립하지 않는다
			for(int i = 1; i <= aLen; ++i) {
				if(!vis[i]) return;
			}
			answer = ans;
			return;
		}
		//한 자리 또는 두 자리에 대해 백트래킹 연산을 수행한다.
		//1. 두 자리에 대해 연산
		if(idx + 1 < len) {
			int num = Integer.parseInt(s.substring(idx, idx + 2));
      //두 자리를 받을 경우 50을 넘는 케이스가 발생할 수 있으므로 사전에 방지한다.
			if(num <= 50 && !vis[num]) {
				vis[num] = true;
				bt(ans + num + " ", idx + 2);
				vis[num] = false;
			}
		}
    //2. 한 자리에 대해 연산
		int num = Integer.parseInt(s.substring(idx, idx + 1));
		if(vis[num]) return;
		vis[num] = true;
		bt(ans + num + " ", idx + 1);
		vis[num] = false;
	}
	
	static void solution() throws IOException{
		bt("", 0);
		System.out.println(answer);
	}

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
}
