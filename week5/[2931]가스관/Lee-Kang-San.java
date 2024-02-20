import java.awt.Point;
import java.io.*;
import java.util.*;
/*
 * 제목
 * <가스관> G2
 * 링크
 * https://www.acmicpc.net/problem/2931
 * 요약
 * 파이프 연결하기
 * 풀이
 * 시뮬레이션
 */

public class boj_2931 {
	static class Pipe { // 파이프 클래스, [type]파이프가 가스를 보낼 수 있는 방향 정보 보유
		int type;
		boolean up, down, left, right;

		public Pipe(int type, boolean up, boolean down, boolean left, boolean right) {
			this.type = type; this.up = up; this.down = down; this.left = left; this.right = right;
		}	
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static final Pipe[] pipe = new Pipe[]{new Pipe(0, false, false, false, false), 	// 사용x
											new Pipe(1, false, true, false, true),	// ┌
											new Pipe(2, true, false, false, true),	// └
											new Pipe(3, true, false, true, false),	// ┘
											new Pipe(4, false, true, true, false),	// ┐
											new Pipe(5, true, true, false, false),	// |
											new Pipe(6, false, false, true, true),	// -
											new Pipe(7, true, true, true, true)};	// +				
	static int R, C; // 지도 크기 R*C
	static int[][] eu; // 유럽!
	static ArrayList<Point> city = new ArrayList<>();
	static int[] dy = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dx = {0, 0, -1 ,1};
	
    public static void main(String[] args) throws IOException {
		// 입력
    	st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		eu = new int[R+1][C+1]; // [0] 제외
		
		for(int i=1; i<=R; i++) {
			String str = br.readLine();
			for(int j=1; j<=C; j++) {
				char c = str.charAt(j-1);
				if(c=='M' || c=='Z') city.add(new Point(j, i)); // 도시 좌표 저장
				setNum(c, eu, i, j);
			}
		}
		// 풀이
		considerCityAsPipe(); // 도시를 5, 6번 파이프로 설정
		findErasedPipe();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void considerCityAsPipe() {
		// 문제 조건에 의해 도시는 항상 연결된 파이프 존재. 도시와 연결된 파이프의 방향 이용하여 도시를 '|' 또는 '-' 로 변경 (cnt==3 예외처리위해)
		int ny, nx;
		Point p1 = city.get(0); // 도시1
		for(int d=0; d<4; d++) {
			ny = p1.y + dy[d]; 
			nx = p1.x + dx[d]; 
			if(!isIn(ny ,nx)) continue; // 범위 외 제거
			if(1 <= eu[ny][nx] && eu[ny][nx] <=7) {	// 파이프 일 때
					if(d == 0 && pipe[eu[ny][nx]].down==true)	eu[p1.y][p1.x] = 5; // 도시 기준 파이프 위치와 가스방향(도시쪽인지) 확인
					if(d == 1 && pipe[eu[ny][nx]].up==true)	eu[p1.y][p1.x] = 5;
					if(d == 2 && pipe[eu[ny][nx]].right==true)	eu[p1.y][p1.x] = 6;
					if(d == 3 && pipe[eu[ny][nx]].left==true)	eu[p1.y][p1.x] = 6;
			}
		}
		
		Point p2 = city.get(1); // 도시2
		for(int d=0; d<4; d++) {
			ny = p1.y + dy[d]; 
			nx = p1.x + dx[d]; 
			if(!isIn(ny ,nx)) continue; // 범위 외 제거
			if(1 <= eu[ny][nx] && eu[ny][nx] <=7) {	// 파이프 일 때
					if(d == 0 && pipe[eu[ny][nx]].down==true)	eu[p2.y][p2.x] = 5; // 도시 기준 파이프 위치와 가스방향(도시쪽인지) 확인
					if(d == 1 && pipe[eu[ny][nx]].up==true)	eu[p2.y][p2.x] = 5;
					if(d == 2 && pipe[eu[ny][nx]].right==true)	eu[p2.y][p2.x] = 6;
					if(d == 3 && pipe[eu[ny][nx]].left==true)	eu[p2.y][p2.x] = 6;
			}
		}
	}

	private static void findErasedPipe() {
		int cnt; // 빈칸에서 4방향 탐색하며 연결 필요한 파이프가 2개 또는 4개 존재하는지 확인
		boolean[] flag;  
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				cnt = 0;
				flag = new boolean[4];
				if(eu[i][j] == 0) { // 현 위치 빈칸일 때 
					for(int d=0; d<4; d++) { // 4방탐색
						int ny = i + dy[d]; 
						int nx = j + dx[d]; 
						if(!isIn(ny ,nx)) continue; // 범위 외 제거
						if(eu[ny][nx]!=0) {	// 4방 중 파이프 인 곳이 존재할 때 해당 파이프가 [i][j] 로 향할 수 있는 지 확인
							if(d == 0 && pipe[eu[ny][nx]].down==true) {
								cnt++;
								flag[0] = true; // [ny][nx]에 [i][j]로 향하는 파이프 존재 
							}
							else if(d == 1 && pipe[eu[ny][nx]].up==true) {
								cnt++;
								flag[1] = true;
							}
							else if(d == 2 && pipe[eu[ny][nx]].right==true) {
								cnt++;
								flag[2] = true;
							}
							else if(d == 3 && pipe[eu[ny][nx]].left==true) {
								cnt++;
								flag[3] = true;
							}
						}
					}	
					if(cnt==2 || cnt==4) {
						sb.append(i+" "+j+" ");
						if(cnt==4) {
							sb.append("+");
						} else {
							if(flag[0] && flag[1]) sb.append("|");
							else if(flag[0] && flag[2]) sb.append("3");
							else if(flag[0] && flag[3]) sb.append("2");
							else if(flag[1] && flag[2]) sb.append("4");
							else if(flag[1] && flag[3]) sb.append("1");
							else if(flag[2] && flag[3]) sb.append("-");
						}
						return;
					} 
				}
			}
		}
	}
	
	private static void setNum(char c, int[][] eu, int i, int j) {
		if(c=='.') 	eu[i][j] = 0;
		else if(c=='|') eu[i][j] = 5;
		else if(c=='-') eu[i][j] = 6;
		else if(c=='+') eu[i][j] = 7;
		else eu[i][j] = c - '0'; // 1, 2, 3, 4 파이프, M(29), Z(42)
	}

	// 범위 체크 
	private static boolean isIn(int ny, int nx) {
		return 1<=ny && ny <=R && 1 <= nx && nx <= C;
	}
}
