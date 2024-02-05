import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA7465_창용_마을_무리의_개수_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String lineSeparator = System.lineSeparator();
	static StringTokenizer st;
	static int T, N, M;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int answer = 0;
			
			/* 
			 * 해당 인덱스의 사람이 서로 알고 있는 사람들 중 하나를 저장하는 배열
			 * 사람들의 관계가 입력될 때 가장 먼저 주어진 관계를 기반으로 저장됨
			 * 초기 값은 자기 자신
			 */
			int[] relationships = new int[N + 1];
			for (int i = 1; i <= N; i++)
				relationships[i] = i;
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int person1 = Integer.parseInt(st.nextToken());
				int person2 = Integer.parseInt(st.nextToken());
				// person1 - person2는 마치 자식-부모 관계처럼 person1 인덱스에 담길 값이 person2과 연결된 최상단 부모와 같은 관계가 담김
				relationships[findrelationships(relationships, person1)] = findrelationships(relationships, person2);
			}
			
			// 결국 초기 값에서 변화가 없는 인덱스가 무리들 중 대표
			for (int i = 1; i <= N; i++) {
				if (relationships[i] == i)
					answer++;
			}
			
			sb.append("#" + testcase + " " + answer + lineSeparator);
		}
		
		System.out.println(sb);
	}
	
	public static int findrelationships(int[] relationships, int person) {
		if (relationships[person] == person) {
			return person;
		}
		else {
			return findrelationships(relationships, relationships[person]);
		}
	}
}
