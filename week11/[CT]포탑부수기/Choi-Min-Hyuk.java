import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CT_포탑부수기_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n, m, k, time = 1;
	static int[][] map, tempMap, attackerRecentTurns;
	static int[] dx = { 0, 1, 0, -1, 1, -1, 1, -1 }; // 우/하/좌/상 우선순위
	static int[] dy = { 1, 0, -1, 0, 1, 1, -1, -1 };

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Turret {
		int x, y, power;

		Turret(int x, int y, int power) {
			this.x = x;
			this.y = y;
			this.power = power;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		tempMap = new int[n][m];
		attackerRecentTurns = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (time < k + 1) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					tempMap[i][j] = map[i][j];
				}
			}

			Turret attacker = getAttacker();
			Turret strongestTurret = getStrongestTurret();
			map[strongestTurret.x][strongestTurret.y] -= attacker.power;

			if (!doLaserAttack(attacker, strongestTurret)) {
				doShellAttack(attacker, strongestTurret);
			}

			repairTurrets(attacker);

			if (getLeftTurretCount() == 1)
				break;
			
			time++;
		}

		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				max = Math.max(map[i][j], max);
			}
		}
		System.out.println(max);
	}
	
	private static Turret getAttacker() {
		int minPower = Integer.MAX_VALUE;
		int minX = 0;
		int minY = 0;
		int maxTurn = 0;
		
		for (int sum = n + m - 2; sum >= 0; sum--) {
			for (int j = m - 1; j >= 0; j--) {
				int i = sum - j;

				if (i < 0 || i >= n)
					continue;
				
				if (map[i][j] > 0) {
					if (minPower > map[i][j]) {
						minPower = map[i][j];
						minX = i;
						minY = j;
						maxTurn = attackerRecentTurns[i][j];
					}
					
					else if (minPower == map[i][j] && maxTurn < attackerRecentTurns[i][j]) {
						minPower = map[i][j];
						minX = i;
						minY = j;
						maxTurn = attackerRecentTurns[i][j];
					}
				}
			}
		}
		
		attackerRecentTurns[minX][minY] = time;
		map[minX][minY] += n + m;
		
		return new Turret(minX, minY, map[minX][minY]);
	}
	
	private static Turret getStrongestTurret() {
		int maxPower = 0;
		int maxX = 0;
		int maxY = 0;
		int minTurn = Integer.MAX_VALUE;
		
		for (int sum = 0; sum <= n + m - 2; sum++) {
			for (int j = 0; j < m; j++) {
				int i = sum - j;

				if (i < 0 || i >= n)
					continue;
				
				if (map[i][j] > 0) {
					if (maxPower < map[i][j]) {
						maxPower = map[i][j];
						maxX = i;
						maxY = j;
						minTurn = attackerRecentTurns[i][j];
					}
					
					else if (maxPower == map[i][j] && minTurn > attackerRecentTurns[i][j]) {
						maxPower = map[i][j];
						maxX = i;
						maxY = j;
						minTurn = attackerRecentTurns[i][j];
					}
				}
			}
		}
		
		return new Turret(maxX, maxY, map[maxX][maxY]);
	}

	private static boolean doLaserAttack(Turret attacker, Turret strongestTurret) {
		Queue<Turret> q = new LinkedList<>();
		q.add(attacker);
		Node[][] come = new Node[n][m];
		boolean[][] visited = new boolean[n][m];
		visited[attacker.x][attacker.y] = true;

		while (!q.isEmpty()) {
			Turret turret = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = turret.x + dx[i];
				int ny = turret.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || map[nx][ny] <= 0)
					continue;

				come[nx][ny] = new Node(turret.x, turret.y);
				q.add(new Turret(nx, ny, map[nx][ny]));
				visited[nx][ny] = true;
			}
		}

		if (come[strongestTurret.x][strongestTurret.y] == null) 
			return false;

		Node node = come[strongestTurret.x][strongestTurret.y];
		int x = node.x;
		int y = node.y;
		while (true) {
			if (x == attacker.x && y == attacker.y)
				break;
			
			map[x][y] -= attacker.power / 2;
			Node tempNode = come[x][y];
			x = tempNode.x;
			y = tempNode.y;
		}
		
		return true;
	}

	private static void doShellAttack(Turret attacker, Turret strongestTurret) {
		for (int i = 0; i < 8; i++) {
			int nx = (strongestTurret.x + dx[i] + n) % n;
			int ny = (strongestTurret.y + dy[i] + m) % m;

			if (nx == attacker.x && ny == attacker.y)
				continue;

			if (map[nx][ny] <= 0)
				continue;
			
			map[nx][ny] -= (attacker.power / 2);
		}
	}

	private static void repairTurrets(Turret attacker) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tempMap[i][j] == map[i][j] && map[i][j] > 0 && (i != attacker.x && j != attacker.y))
					map[i][j] += 1;
			}
		}
	}

	private static int getLeftTurretCount() {
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] > 0)
					count++;
			}
		}
		
		return count;
	}
}
