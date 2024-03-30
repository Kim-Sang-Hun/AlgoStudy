import java.io.*;
import java.util.*;

// santa ArrayList를 santa 배열로 바꿔서 최적화하려고 했는데 시간이 부족한 관계로 패스
public class codetree_루돌프의반란_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, P, C, D;
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0, -1, 1, 1, -1 }; // 상우하좌 우선순위 고려
	static int[] dy = { 0, 1, 0, -1, 1, 1, -1, -1 };
	static int rx, ry;
	static ArrayList<Santa> santas;

	static class Santa implements Comparable<Santa> {
		int index;
		int x;
		int y;
		int grade;
		boolean isAlive;
		int faintCount;

		public Santa(int index, int x, int y, int grade, boolean isAlive, int faintCount) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.grade = grade;
			this.isAlive = isAlive;
			this.faintCount = faintCount;
		}

		@Override
		public int compareTo(Santa o) {
			return index - o.index; // 오름차순 정렬
		}
	}

	static boolean isGameOver = false;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		santas = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		rx = Integer.parseInt(st.nextToken());
		ry = Integer.parseInt(st.nextToken());
		map[rx][ry] = -1;

		santas.add(new Santa(0, 0, 0, 0, false, 0));
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			santas.add(new Santa(idx, sx, sy, 0, true, 0));
			map[sx][sy] = idx;
		}
		
		Collections.sort(santas);
		
		for (int m = 1; m <= M; m++) {
			rudolfMove();
			if (isGameOver)
				break;
			
			santaMove();
			if (isGameOver)
				break;
			
			for (int p = 1; p <= P; p++) {
				Santa santa = santas.get(p);
				if (santa.isAlive) {
					if (santa.faintCount > 0)
						santas.set(p, new Santa(p, santa.x, santa.y, santa.grade + 1, santa.isAlive, --santa.faintCount));
					else
						santas.set(p, new Santa(p, santa.x, santa.y, santa.grade + 1, santa.isAlive, santa.faintCount));
				}
			}
			
			if (isGameOver)
				break;
		}

		for (int i = 1; i <= P; i++) {
			Santa santa = santas.get(i);
			sb.append(santa.grade).append(" ");
		}
		
		System.out.print(sb);
	}

	private static boolean isAllSantaOut() {
		boolean isSantaLeft = false;
		
		for (int i = 1; i <= P; i++) {
			Santa santa = santas.get(i);
			if (santa.isAlive) {
				isSantaLeft = true;
				break;
			}
		}
		
		return !isSantaLeft;
	}

	public static void santaMove() {
		for (int p = 1; p <= P; p++) {
			Santa santa = santas.get(p);

			
			if (!santa.isAlive || santa.faintCount > 0)
				continue;
			
			double curDir = Math.pow((santa.x - rx), 2) + Math.pow((santa.y - ry), 2);
			int santaDirIdx = -1;
			
			for (int i = 0; i < 4; i++) {
				int nx = santa.x + dx[i];
				int ny = santa.y + dy[i];
				
				if (nx < 1 || ny < 1 || nx > N || ny > N)
					continue;
				
				if (map[nx][ny] > 0)
					continue;
				
				double moveDir = Math.pow(nx - rx, 2) + Math.pow(ny - ry, 2);
				if (moveDir < curDir) {
					curDir = moveDir;
					santaDirIdx = i;
				}
			}
			
			if (santaDirIdx != -1) {
				map[santa.x][santa.y] = 0;
				int nx = santa.x + dx[santaDirIdx];
				int ny = santa.y + dy[santaDirIdx];
				
				if (map[nx][ny] == -1) {
					int newX = nx - D * dx[santaDirIdx];
					int newY = ny - D * dy[santaDirIdx];
					int newGrade = santa.grade + D;
					
					if (newX < 1 || newY < 1 || newX > N || newY > N) {
						santas.set(p, new Santa(santa.index, newX, newY, newGrade, false, 0));
						if (isAllSantaOut()) {
							isGameOver = true;
							return;
						}
					}
					
					else {
						if (map[newX][newY] > 0) {
							interaction(map[newX][newY], newX, newY, (santaDirIdx + 2) % 4); // 산타가 이동한 반대방향으로 상호작용
						}
						
						map[newX][newY] = santa.index;
						santas.set(p, new Santa(santa.index, newX, newY, newGrade, true, 2));
					}
				}
				
				else {
					map[nx][ny] = santa.index;
					santas.set(p, new Santa(santa.index, nx, ny, santa.grade, true, 0));
				}
			}
		}
	}

	private static void interaction(int santaIdx, int x, int y, int rudolphDirIdx) {
		Santa santa = santas.get(santaIdx);
		int nx = santa.x + dx[rudolphDirIdx];
		int ny = santa.y + dy[rudolphDirIdx];
		
		if (nx < 1 || ny < 1 || nx > N || ny > N) {
			santas.set(santaIdx, new Santa(santaIdx, nx, ny, santa.grade, false, 0));
			if (isAllSantaOut()) {
				isGameOver = true;
			}
		}
		
		// 또 산타가 있으면 연쇄적으로
		if (map[nx][ny] > 0) {
			interaction(map[nx][ny], nx, ny, rudolphDirIdx);
		}
		
		map[nx][ny] = santaIdx;
		santas.set(santaIdx, new Santa(santaIdx, nx, ny, santa.grade, true, santa.faintCount));
	}

	private static void rudolfMove() {
		int selectedSanta = 0;
		double minDistance = 5000;
		int r = 0;
		int c = 0;
		
		for (int i = 1; i <= P; i++) {
			Santa santa = santas.get(i);
			
			if (santa.isAlive) {
				double rsDistance = Math.pow((rx - santa.x), 2) + Math.pow((ry - santa.y), 2);
				
				if (rsDistance > minDistance)
					continue;
				
				else {
					if (rsDistance == minDistance) {
						if (santa.x > r) {
							selectedSanta = i;
							r = santa.x;
							c = santa.y;
						}
						
						else {
							if (santa.x == r) {
								if (santa.y > c) {
									selectedSanta = i;
									c = santa.y;
								}
							}
						}
					}
					
					else {
						selectedSanta = i;
						minDistance = (int) rsDistance;
						r = santa.x;
						c = santa.y;
					}
				}
			}
			
			else
				continue;
		}

		if (selectedSanta != 0) {
			Santa santa = santas.get(selectedSanta);
			int x = santa.x;
			int y = santa.y;
			int rudolphDirIdx = -1;
			double curDir = Math.pow((rx - x), 2) + Math.pow((ry - y), 2);
			
			for (int i = 0; i < 8; i++) {
				int nx = rx + dx[i];
				int ny = ry + dy[i];
				double moveDir = Math.pow((nx - x), 2) + Math.pow((ny - y), 2);
				if (moveDir < curDir) {
					curDir = moveDir;
					rudolphDirIdx = i;
				}
			}
			
			map[rx][ry] = 0;
			rx = rx + dx[rudolphDirIdx];
			ry = ry + dy[rudolphDirIdx];
			
			// 산타가 있는 경우
			if (map[rx][ry] > 0) {
				int newX = santa.x + C * dx[rudolphDirIdx];
				int newY = santa.y + C * dy[rudolphDirIdx];
				int newGrade = santa.grade + C;
				
				if (newX < 1 || newY < 1 || newX > N || newY > N) {
					santas.set(santa.index, new Santa(santa.index, newX, newY, newGrade, false, 0));
					if (isAllSantaOut()) {
						isGameOver = true;
						return ;
					}
				}
				
				else {
					// 산타끼리 충돌하는 경우
					if (map[newX][newY] > 0) {
						interaction(map[newX][newY], newX, newY, rudolphDirIdx);
					}
					
					map[newX][newY] = santa.index;
					santas.set(santa.index, new Santa(santa.index, newX, newY, newGrade, true, 2));
				}
			}
			
			map[rx][ry] = -1;
		}
	}
}
