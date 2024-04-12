package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class codeTree2 {
    
    static int N,M,K;
    static turret[][] arr;
    static int plus;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int[] dx2 = {0,-1,-1,-1,0,1,1,1};
    static int[] dy2 = {-1,-1,0,1,1,1,0,-1};
    static int t;    //시간
    static int ax,ay,aPower,aTime;
    static boolean[][] visited;
    static Queue<int[]> q;
    static Queue<ArrayList<Integer>> subQ;
    
    static class turret {
        int power;
        int time;
        
        public turret(int power, int time) {
            this.power = power;
            this.time = time;    
        }
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        plus = N+M;
        
        arr = new turret[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int p = Integer.parseInt(st.nextToken());
                arr[i][j] = new turret(p, 0);
            }
        }
        
        for (int i = 0; i < K; i++) {
            t = i+1;        //현재 진행중인 시간
            case1();
            case4();
            
//            System.out.println("---------------------");
//            for (int j = 0; j < N; j++) {
//                for (int j2 = 0; j2 < M; j2++) {
//                    System.out.print(arr[j][j2].power + " ");
//                }
//                System.out.println("");
//            }
        }
        
        ans();
    }

    private static void ans() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j].power > cnt) {
                    cnt = arr[i][j].power;
                }
            }
        }
        System.out.println(cnt);
    }

    // 1. 공격자선정
    // 1이상포탑중 공격력 가장 낮은 포탑이 공격자 -> N+M만큼 공격력 증가
    // 중복 -> 가장 최근에 공격한 포탑선정 -> 행+열 합이 가장 큰놈 -> 열이 가장 큰놈
    private static void case1() {
        
        ax = 0;
        ay = 0;
        aPower = Integer.MAX_VALUE;
        aTime = 0;
        int sub_cnt = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j].power <= 0) {
                    sub_cnt++;
                    continue;
                }
                else if (arr[i][j].power < aPower) {
                    ax = i;
                    ay = j;
                    aPower = arr[i][j].power;
                    aTime = arr[i][j].time;
                } else if (arr[i][j].power == aPower) {
                    if (arr[i][j].time > aTime ) {
                        ax = i;
                        ay = j;
                        aTime = arr[i][j].time;
                    } else if (arr[i][j].time == aTime) {
                        if ((i+j) > (ax+ay)) {
                            ax = i;
                            ay = j;
                        } else if ((i+j) == (ax+ay) && j > ay) {
                            ax = i;
                            ay = j;
                        }
                    }
                } 
            }
        }
        
        if (sub_cnt == (N*M)-1) return;
        
        
        arr[ax][ay].time = t;
        arr[ax][ay].power += plus;
        //time 이 클수록 최신놈
        case2(ax,ay);
    }
    
    private static void case2(int x, int y) {
        // 2. 공격자의 공격
        // 1번에서 선정된 놈을 제외한 가장 강한 포탑 공격
        // 중복 -> 공격한지 가장 오래된놈 선정 -> 행+열 가장 작은놈 -> 열 가장 작은놈
        ax = 0;
        ay = 0;
        aPower = 0;
        aTime = 0;
        
        // 지금 공격 받을놈 선정하는거임 -> ax, ay
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == x && j == y || arr[i][j].power <= 0) continue;
                
                if (arr[i][j].power > aPower) {
                    ax = i;
                    ay = j;
                    aPower = arr[i][j].power;
                    aTime = arr[i][j].time;
                } else if (arr[i][j].power == aPower) {
                    if (arr[i][j].time < aTime) {
                        ax = i;
                        ay = j;
                        aTime = arr[i][j].time;
                    } else if (arr[i][j].time == aTime) {
                        if ((i+j) < (ax+ay)) {
                            ax = i;
                            ay = j;
                        } else if ((i+j) == (ax+ay)) {
                            if (j < ay) {
                                ax = i;
                                ay = j;
                            }
                        }
                    }
                }
            }
        }
        
        // 2-1.레이저 공격
        // bfs 우,하,좌,상 순으로 돌면서 경로에 있는놈들은 절반 피해 입힘
        // 공격할때 오른쪽끝 넘어가면 왼쪽끝 반대편으로 감
        // x,y(공격포탑)에서 출발 -> ax,ay(공격받는놈) 도착 
        
        boolean flag = false;
        q = new ArrayDeque<>();
        subQ = new ArrayDeque<>();
        visited = new boolean[N][M];
        
        visited[x][y] = true;
        
        q.add(new int[] {x,y});
        subQ.add(new ArrayList<>());
        
        while (!q.isEmpty()) {
        	if (flag) break;
        	int[] cur = q.poll();
        	ArrayList<Integer> curList = subQ.poll();
        	
        	for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				
				nx = setX(nx);
				ny = setY(ny);
				
				if (visited[nx][ny] || arr[nx][ny].power <= 0) continue;
				
				if (nx == ax && ny == ay) {
					flag = true;
					// x,y는 공격자포탑임 건들이지마
					// 레이저 공격이 가능하면 포탑공격
					visited = new boolean[N][M];
					visited[x][y] = true;
					
					for (Integer idx : curList) {
						//공격받을놈들 좌표
						int curX = idx/M;
						int curY = idx%M;
						if (curX == x && curY == y) continue;
						if (curX == ax && curY == ay) continue;
						arr[curX][curY].power -= arr[x][y].power/2;
						visited[curX][curY] = true;
					}
					arr[ax][ay].power -= arr[x][y].power;
					visited[ax][ay] = true;
					
					return;
				}
				
				visited[nx][ny] = true;
				ArrayList<Integer> copyList = new ArrayList<>();
				
				for (Integer i : curList) {
					copyList.add(i);
				}
				copyList.add(nx*M + ny);
				q.add(new int[] {nx,ny});
				subQ.add(copyList);
			}
        }
        
        // 2-2. 레이저 공격 못할경우 -> 포탑공격
        // 제일 작은놈 찾아서 8개 방향으로 피해입힘 
        // 공격당할놈 좌표는 ax,ay
        arr[ax][ay].power -= arr[x][y].power;
        visited = new boolean[N][M];
        visited[ax][ay] = true;
        visited[x][y] = true;
        
        for (int i = 0; i < 8; i++) {
            int nx = ax+dx2[i];
            int ny = ay+dy2[i];
            nx = setX(nx);
            ny = setY(ny);
            
            if (arr[nx][ny].power <= 0) continue;
            if (x == nx && y == ny) continue;
            arr[nx][ny].power -= arr[x][y].power/2;
            visited[nx][ny] = true;
        }
    }
    
    private static int setX(int nx) {
        if (nx == -1) return N-1;
        else return (nx%N);
    }

    private static int setY(int ny) {
        if (ny == -1) return M-1;
        else return (ny%M);
    }

    // 4. 포탑 정비
    // 부서지지 않았으면서 공격받지 않은놈들 +1
    private static void case4() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j].power > 0 && !visited[i][j]) {
                    arr[i][j].power += 1;
                }
            }
        }
    }

}