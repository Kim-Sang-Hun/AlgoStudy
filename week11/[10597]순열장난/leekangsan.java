package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <순열장난> G5
* 링크
* https://www.acmicpc.net/problem/10597
* 요약
* 챗GPT 한테 테스트케이스 만들어달라고 하지 맙시다 개멍청함ㄹㅇ
* 풀이
* 1~N 으로 이루어진 순열, N 최대 50
* case 1: 길이가 9 이하인 경우 : 순서대로 출력 (1~9)
* case 2: 길이가 10 이상인 경우 : (length-9)/2+9 개의 숫자로 이루어져 있다.
*/
public class boj_10597 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static String input;
	static int N;
	static ArrayDeque<Integer> dq = new ArrayDeque<>();
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		// 입력
		input = br.readLine().trim();
		int length = input.length();
		// 풀이
		if (length < 10) { // case 1: 길이가 9 이하인 경우 : 한자리씩 끊어서 출력 (1~9)
			for (int i = 0; i < length; i++)
				sb.append(input.charAt(i)).append(" ");
		} else { // case 2: 길이가 10 이상인 경우 : (length-9)/2 +9+1 개의 숫자로 이루어져 있다.
			N = (length - 9) / 2 + 9;
			dfs(0, 0); // 개수, idx
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	// pos 위치에서 length만큼의 길이를 추출해서 숫자로 만든다.
	static boolean fin = false;
	private static void dfs(int depth, int pos) {
		if (depth == N) {
			while (!dq.isEmpty())
				sb.append(dq.poll()).append(" ");
			fin = true;
			return;
		}
		int length = 0;
		while (true) {
			length++;
			if (pos + length > input.length())
				return;
			String str = input.substring(pos, pos + length);
			int cur = Integer.parseInt(str);
			if (cur == 0) // 0은 순열에 존재 할 수 없음
				continue;
			if (set.contains(cur)) // 이미 생성한 숫자면 continue
				continue;
			if (cur > N) // N보다 커지면 더 탐색할 필요 x
				break;
			set.add(cur);
			dq.addLast(cur);
			dfs(depth + 1, pos + length);
			if (fin)
				return;
			set.remove(cur);
			dq.removeLast();
		}
	}
}
