package BOJ;
import java.io.*;
import java.util.*;

/*
* 제목
* <영우는 사기꾼?> G3
* 링크
* https://www.acmicpc.net/problem/14676
* 요약
* 
* 풀이
* 구현, 진입차수 정보 유지하기
*/
public class boj_14676 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N, M, K; // 건물 종류 개수 N, 건물 간 관계 개수 M, 영우의 게임 정보 개수 K
	
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 건물 간 건설 관계 그래프, i번 건물 짓기 위해 필요한 건물들 저장

	static int[] inCount; // 진입차수 저장
	
	static int[] buildingCount; // 건설한 건물 개수 저장
	
	static boolean cheaterCheck = false;
	
	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=N; i++) { // 0번 건물 제외
			graph.add(new ArrayList<Integer>());
		}
		
		inCount = new int[N+1]; // 0번 건물 제외	
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int parent =  Integer.parseInt(st.nextToken());
			int child =  Integer.parseInt(st.nextToken());
			graph.get(parent).add(child);
			inCount[child]++;
		}
				
		buildingCount = new int[N+1]; // 0번 건물 제외	
		
		// 풀이
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int constructInfo = Integer.parseInt(st.nextToken());
			int buildingNo = Integer.parseInt(st.nextToken());
			
			if(constructInfo == 1) { // 건설
				checkConstruct(buildingNo);
			} else { // constructInfo == 2 // 파괴
				checkDestroy(buildingNo);
			}
			
			if(cheaterCheck) {
				break;
			}
		}
		
		// 출력
		if(cheaterCheck) {
			bw.write("Lier!");
		} else {
			bw.write("King-God-Emperor");
		}
		bw.flush();
	}

	private static void checkDestroy(int buildingNo) {
		if(buildingCount[buildingNo]<1) {
			cheaterCheck = true;
			return;
		}
		buildingCount[buildingNo]--;
		if(buildingCount[buildingNo]==0) { // 건물 개수 0개 되면 얘 필요한 건물들 inCount++
			for(int childNo : graph.get(buildingNo)) {
				inCount[childNo]++;
			}
		}
	}

	private static void checkConstruct(int buildingNo) {
		if(inCount[buildingNo] > 0) {
			cheaterCheck = true;
			return;
		}
		buildingCount[buildingNo]++;
		if(buildingCount[buildingNo]==1) { // 건물 개수 1개 되면 얘 필요한 건물들 inCount--
			for(int childNo : graph.get(buildingNo)) {
				inCount[childNo]--;
			}
		}	
	}
}
