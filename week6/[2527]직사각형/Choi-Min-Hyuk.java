import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2527_직사각형_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static String lineSeparator = System.lineSeparator();

	public static void main(String[] args) throws Exception {
		int[][] coords = new int[2][4];

		for (int testcase = 0; testcase < 4; testcase++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 4; j++) {
					coords[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 공통부분이 없는 경우
			if (coords[0][0] > coords[1][2] || coords[0][1] > coords[1][3] || coords[0][2] < coords[1][0] || coords[0][3] < coords[1][1])
				sb.append("d").append(lineSeparator);
			
			// 점으로 겹치는 경우
			else if ((coords[0][0] == coords[1][2] && coords[0][1] == coords[1][3]) || (coords[0][0] == coords[1][2] && coords[0][3] == coords[1][1])
					|| (coords[0][2] == coords[1][0] && coords[0][1] == coords[1][3]) || (coords[0][2] == coords[1][0] && coords[0][3] == coords[1][1]))
				sb.append("c").append(lineSeparator);
			
			// 선분으로 겹치는 경우
			else if (coords[0][0] == coords[1][2] || coords[0][1] == coords[1][3] || coords[0][2] == coords[1][0] || coords[0][3] == coords[1][1])
				sb.append("b").append(lineSeparator);
			
			// 직사각형으로 겹치는 경우
			else
				sb.append("a").append(lineSeparator);

		}

		System.out.println(sb);
	}
}
