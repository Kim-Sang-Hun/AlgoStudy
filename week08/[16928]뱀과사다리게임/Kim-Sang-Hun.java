import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
 
/*
 * bfs와 dp로 풀어주었다. 뱀과 사다리를 event라는 맵에 넣어주고
 * 해당 인덱스에 도착했을 때 event에 존재하는 값이면 인덱스를 바꿔주었다.
 * 주사위는 1에서 6까지이므로 bfs 내부에서 그만큼을 더해보는 방식으로 새 idx를 구했다.
 * 해당 위치가 dp[]의 값보다 크다면 최소값이 아니므로 백트래킹해준다.
 */

public class JUN16928_뱀과사다리게임 {

	static Map<Integer, Integer> event = new HashMap<>();
	static int[] dp = new int[101];
	static class Player {
		int idx;
		int time;
		public Player(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			event.put(from, to);
		}
		bfs();
		System.out.println(dp[100]);
	}
	private static void bfs() {
		Player player = new Player(1, 0);
		Queue<Player> qu = new LinkedList<>();
		qu.add(player);
		
		while (!qu.isEmpty()) {
			player = qu.poll();
			
			for (int i = 1; i <= 6; i++) {
				int newIdx = player.idx + i;
				int newTime = player.time + 1;
				if (newIdx > 100) continue;
				if (event.containsKey(newIdx)) {
					newIdx = event.get(newIdx);
				}
				if (dp[newIdx] < newTime) continue;
				dp[newIdx] = newTime;
				if (newIdx == 100) continue;
				qu.add(new Player(newIdx, newTime));
			}
		}
	}
}
