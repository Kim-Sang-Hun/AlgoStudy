package BOJ;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <소문난 칠공주> G3
 * 링크
 * https://www.acmicpc.net/problem/1941
 * 요약
 * 25명의 학생 중 7명 선택 후 S파 4명 이상이고 모두 인접했으면 cnt++
 * 풀이
 * 프림
 */
public class boj_1941 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int cnt = 0;
	static char[] student; // S or Y
	static ArrayList<Integer>[] list; // 인접리스트

    public static void main(String[] args) throws IOException {
    	// 노드 
    	student = new char[26]; // [0] 제외
    	// 노드 정보
    	for(int i=0; i<5; i++) {
    		String str = br.readLine();
    		for(int j=0; j<5; j++) {
    			student[i*5+j+1] = str.charAt(j);
    		}
    	}
    	// 인접리스트
    	list = new ArrayList[26]; // [0] 제외
    	for(int i=1; i<=25; i++) list[i] = new ArrayList<Integer>();
    	// 인접 정보
    	for(int i=0; i<=4; i++) { // 가로방향 인접 정보 1-2, 2-3, 3-4, ..., 24-25
    		for(int j=1; j<=4; j++) {
    			list[i*5+j].add(i*5+j+1);
    			list[i*5+j+1].add(i*5+j);
    		}
    	}
    	for(int i=0; i<4; i++) { // 세로방향 인접 정보 1-6, 2-7, 3-8, ..., 20-25
    		for(int j=1; j<=5; j++) {
    			list[i*5+j].add((i+1)*5+j);
    			list[(i+1)*5+j].add(i*5+j);
    		}
    	}
//    	// 인접 정보 확인
//    	for(int i=1; i<=25; i++) {
//    		System.out.println(i +" : " + list[i].toString());
//    	}
		// 풀이
		solution();
		// 출력
		sb.append(cnt);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		// 25_C_7 = 480700
		for(int a=1; a<=25; a++) {
			for(int b=a+1; b<=25; b++) {
				for(int c=b+1; c<=25; c++) {
					for(int d=c+1; d<=25; d++) {
						for(int e=d+1; e<=25; e++) {
							for(int f=e+1; f<=25; f++) {
								for(int g=f+1; g<=25; g++) {
									// 선택된 학생들 번호 : a, b, c, d, e, f, g
									int[] selectedSt = {a, b, c, d, e, f, g};
									// 7명 중 S파가 4명 미만이라면 제외
									int cntS = 0;
									for(int n : selectedSt) {
										if(student[n]=='S') cntS++;
									}
									if(cntS<4) continue;
									// 7명의 인접 여부 확인
									boolean[] visited = new boolean[7]; 
									boolean[] havePath = new boolean[7];
									
									PriorityQueue<Integer> pq = new PriorityQueue<>(); // 7명에 대한 인덱스 삽입
									havePath[0] = true; // 시작정점 a (인덱스 0)
									pq.offer(0);
									int cntSt = 0; // cntSt가 7이면 모두 인접
									while(!pq.isEmpty()) {
										int currentStIdx = pq.poll(); // 현재 학생의 인덱스
										if(visited[currentStIdx]) continue; // 이미 방문한 정점이면 continue
										visited[currentStIdx] = true; // 트리 정점에 포함
										cntSt++;
										if(cntSt==7) break;
										// 새로 추가된 노드에 대해 인접한 정점 있는 지 체크
										ArrayList<Integer> al = list[selectedSt[currentStIdx]];
										for(int i=0; i<al.size(); i++) { // 현재 노드와 인접한 정점들 중 7명 존재하는 지 확인
											for(int j=0; j<7; j++) { 
												if(al.get(i)==selectedSt[j]) {
													havePath[j]=true;
												}
											}
										}
										for(int i=0; i<7; i++) {
											if(!visited[i] && havePath[i]) {
												pq.offer(i);
											}
										}
									}
									if(cntSt==7) cnt++;
								}	
							}
						}
					}
				}
			}
		}
	}
}
