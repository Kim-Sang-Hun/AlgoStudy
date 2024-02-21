import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1941_소문난_칠공주_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char places[][] = new char[5][];
	static int selectedStudentSeats[] = new int[7];
	static int dRow[] = { 0, 0, 1, -1 };
	static int dCol[] = { 1, -1, 0, 0 };
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 5; i++)
			places[i] = br.readLine().toCharArray();
		
		// 25명 중에 7명을 뽑는 조합 계산
		getStudentCombination(0, 0, 0);
		System.out.print(answer);
	}
	
	public static void getStudentCombination(int studentCount, int index, int YStudentCount) {
		// 임도연파가 4명 이상이면 X
		if (YStudentCount >= 4)
			return ;
		
		// 각 자리의 번호를 행 x 5 + 열로 생각하고 0 ~ 24에서 7개의 숫자를 선택해 각 자리가 서로 가로나 세로로 인접해 있으면 답을 하나 늘려줌
		if (studentCount == 7) {
			if (isStudentsConnected())
				answer++;
			return ;
		}
		
		for (int i = index; i < 25; i++) {
			selectedStudentSeats[studentCount] = i;
			getStudentCombination(studentCount + 1, i + 1, YStudentCount + (places[i / 5][i % 5] == 'Y' ? 1 : 0));
		}
	}
	
	// BFS
	public static boolean isStudentsConnected() {
		int[][] students = new int[5][5];
		boolean[][] visited = new boolean[5][5];
		
		for (int i = 0; i < 7; i++)
			students[selectedStudentSeats[i] / 5][selectedStudentSeats[i] % 5] = 1;
		
		int count = 1;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(selectedStudentSeats[0]);
		visited[selectedStudentSeats[0] / 5][selectedStudentSeats[0] % 5] = true;
		
		while (!queue.isEmpty()) {
			int seatNumber = queue.poll();
			int currentSeatRow = seatNumber / 5;
			int currentSeatCol = seatNumber % 5;
			
			for (int i = 0; i < 4; i++) {
				int nextSeatRow = currentSeatRow + dRow[i];
				int nextSeatCol = currentSeatCol + dCol[i];
				if (0 <= nextSeatRow && nextSeatRow < 5 && 0 <= nextSeatCol && nextSeatCol < 5 && !visited[nextSeatRow][nextSeatCol] && students[nextSeatRow][nextSeatCol] == 1) {
					count++;
					visited[nextSeatRow][nextSeatCol] = true;
					queue.offer(nextSeatRow * 5 + nextSeatCol);
				}
			}
		}
		
		if (count == 7)
			return true;
		
		return false;
	}
}
