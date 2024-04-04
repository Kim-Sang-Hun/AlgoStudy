import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder(); 
	static int n;
	static int[] a;
	
	static void input() throws IOException{
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		st = new StringTokenizer(br.readLine());
		//문제 조건에 알아서 정렬되어있음이 명시되어있다.
		for(int i = 0;i < n; ++i) {
			a[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void solution() {
		//Two Pointer Process
		int st = 0, en = n - 1, answer = a[st] + a[en];
		while(st < en) {
			int sum = a[st] + a[en];
			if(Math.abs(sum) < Math.abs(answer)) {
				answer = sum;
			}
			if(sum == 0) {
				break;
			} else if(sum < 0) {
				++st;
			} else {
				--en;
			}
		}
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
}
