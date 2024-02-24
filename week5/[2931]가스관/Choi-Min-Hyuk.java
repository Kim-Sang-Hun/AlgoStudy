package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2931_가스관_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int R, C;
	static char[][] map;
	static final int TOP = 0, BOTTOM = 1, LEFT = 2, RIGHT = 3;
	static int dRow[] = { -1, 1, 0, 0 };
	static int dCol[] = { 0, 0, -1, 1 };
	
	static class Point {
		int row, col;

		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; ++i)
			map[i] = br.readLine().toCharArray();
		
		// 모든 점에 대해서 4방 탐색
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (map[i][j] == '.' && checkPoint(i, j)) {
					System.out.print(sb);
					return;
				}
			}
		}
	}
	
	// 모든 점에 대해서 4방 탐색
	public static boolean checkPoint(int row, int col) {
		Point topPoint = new Point(row + dRow[TOP], col + dCol[TOP]);
		Point bottomPoint = new Point(row + dRow[BOTTOM], col + dCol[BOTTOM]);
		Point leftPoint = new Point(row + dRow[LEFT], col + dCol[LEFT]);
		Point rightPoint = new Point(row + dRow[RIGHT], col + dCol[RIGHT]);
		
		// 4방향에 있는 점들이 연결할 수 있는 가능성이 있는지에 대해 조사
		boolean[] connectable = new boolean[4];
		if (checkTopPoint(topPoint.row, topPoint.col)) 
			connectable[TOP] = true;
		
		if (checkBottomPoint(bottomPoint.row, bottomPoint.col))
			connectable[BOTTOM] = true;
		
		if (checkLeftPoint(leftPoint.row, leftPoint.col))
			connectable[LEFT] = true;
		
		if (checkRightPoint(rightPoint.row, rightPoint.col))
			connectable[RIGHT] = true;
		
		// 모든 경우 체크
		if (connectable[TOP] && connectable[BOTTOM] && connectable[LEFT] && connectable[RIGHT])
			sb.append(row + 1).append(" ").append(col + 1).append("+");
		
		else if (connectable[TOP] && connectable[BOTTOM] && !connectable[LEFT] && !connectable[RIGHT])
			sb.append(row + 1).append(" ").append(col + 1).append("|");
		
		else if (connectable[TOP] && !connectable[BOTTOM] && connectable[LEFT] && !connectable[RIGHT])
			sb.append(row + 1).append(" ").append(col + 1).append("3");
		
		else if (connectable[TOP] && !connectable[BOTTOM] && !connectable[LEFT] && connectable[RIGHT])
			sb.append(row + 1).append(" ").append(col + 1).append("2");
		
		else if (!connectable[TOP] && connectable[BOTTOM] && connectable[LEFT] && !connectable[RIGHT])
			sb.append(row + 1).append(" ").append(col + 1).append("4");
		
		else if (!connectable[TOP] && connectable[BOTTOM] && !connectable[LEFT] && connectable[RIGHT])
			sb.append(row + 1).append(" ").append(col + 1).append("1");
		
		else if (!connectable[TOP] && !connectable[BOTTOM] && connectable[LEFT] && connectable[RIGHT])
			sb.append(row + 1).append(" ").append(col + 1).append("-");
		
		else
			return false;
		
		return true;
	}
	
	public static boolean isInRange(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C)
			return true;
		return false;
	}
	
	public static boolean checkTopPoint(int row, int col) {
		if (!isInRange(row, col))
			return false;
		
		char c = map[row][col];
		if (c == '|' || c == '+' || c == '1' || c == '4')
			return true;
		
		return false;
	}

	public static boolean checkBottomPoint(int row, int col) {
		if (!isInRange(row, col))
			return false;
	
		char c = map[row][col];
		if (c == '|' || c == '+' || c == '2' || c == '3')
			return true;
		
		return false;
	}
	
	public static boolean checkLeftPoint(int row, int col) {
		if (!isInRange(row, col))
			return false;
		
		char c = map[row][col];
		if (c == '-' || c == '+' || c == '1' || c == '2')
			return true;
		
		return false;
	}

	public static boolean checkRightPoint(int row, int col) {
		if (!isInRange(row, col))
			return false;
		
		char c = map[row][col];
		if (c == '-' || c == '+' || c == '3' || c == '4')
			return true;
		
		return false;
	}
}
