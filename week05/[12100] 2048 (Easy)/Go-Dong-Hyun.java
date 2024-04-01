package Algo_week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12100 {
	static int N, ans;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		move(0);
		System.out.println(ans);
	}

	private static void move(int cnt) {
		if (cnt == 5) {
			check();
			return;
		}

		int[][] copyArr = new int[N][N];
		for (int c = 0; c < N; c++) {
			copyArr[c] = arr[c].clone();
		}
		for (int t = 0; t < 4; t++) {
			
			if (t == 0) {	//위로갈때
				for (int j = 0; j < N; j++) {	//여기는 더하기
					for (int i = 0; i < N; i++) {
						if (arr[i][j] == 0 ) continue;
						
						int idx = i+1;
						while (idx < N) {
							if (arr[idx][j] == 0) {
								idx++;
								continue;
							}
							
							if (arr[idx][j] != arr[i][j]) break;
							else {
								arr[i][j] *= 2;
								arr[idx][j] = 0;
								break;
							}
						}
//						i = idx;
					}
				}
				for (int j = 0; j < N; j++) {	//여기는 옮기기
					for (int i = 0; i < N-1; i++) {
						if (arr[i][j] == 0) {
							int idx = i+1;
							while (idx <= N-1) {
								if (arr[idx][j] != 0) {
									arr[i][j] = arr[idx][j];
									arr[idx][j] = 0;
									break;
								}
								idx++;
							}
//							i = idx;
						}
					}
				}
				move(cnt+1);
				for (int c = 0; c < N; c++) {
					arr[c] = copyArr[c].clone();
				}
				
			} else if (t == 1) {	//왼쪽으로 갈때
				for (int i = 0; i < N; i++) {	//더하기
					for (int j = 0; j < N ; j++) {
						if (arr[i][j] == 0) continue;
						
						int idx = j + 1;
						while (idx < N) {
							if (arr[i][idx] == 0) {
								idx++;
								continue;
							}
							
							if (arr[i][j] != arr[i][idx]) break;
							else {
								arr[i][j] *= 2;
								arr[i][idx] = 0;
								break;
							}
						}
//						j = idx;
					}
				}
				for (int i = 0; i < N; i++) {	//옮기기
					for (int j = 0; j < N-1; j++) {
						if (arr[i][j] == 0) {
							int idx = j+1;
							while (idx <= N-1) {
								if (arr[i][idx] != 0) {
									arr[i][j] = arr[i][idx];
									arr[i][idx] = 0;
									break;
								}
								idx++;
							}
//							j = idx;
						}
					}
				}
				move(cnt+1);
				for (int c = 0; c < N; c++) {
					arr[c] = copyArr[c].clone();
				}
				
			} else if (t == 2) {	//아래
				for (int j = 0; j < N; j++) {	//더하기
					for (int i = N-1; i >= 0; i--) {
						if (arr[i][j] == 0) continue;
						
						int idx = i-1;
						while (idx >= 0) {
							if (arr[idx][j] == 0) {
								idx--;
								continue;
							}
							
							if (arr[i][j] != arr[idx][j]) break;
							else {
								arr[i][j] *= 2;
								arr[idx][j] = 0;
								break;
							}
						}
//						i = idx;
					}
				}
				for (int j = 0; j < N; j++) {	//아래 옮기기
					for (int i = N-1; i >=0 ; i--) {
						if (arr[i][j] == 0) {
							int idx = i-1;
							while (idx >= 0) {
								if (arr[idx][j] != 0) {
									arr[i][j] = arr[idx][j];
									arr[idx][j] = 0;
									break;
								}
								idx--;
							}
//							i = idx;
						}
					}
				}
				move(cnt+1);
				for (int c = 0; c < N; c++) {
					arr[c] = copyArr[c].clone();
				}
				
			} else {	//오른쪽으로 갈때
				for (int i = 0; i < N; i++) {	//더하기
					for (int j = N-1; j >= 0; j--) {
						if (arr[i][j] == 0) continue;
						
						int idx = j-1;
						while (idx >= 0) {
							if (arr[i][idx] == 0) {
								idx--;
								continue;
							}
							
							if (arr[i][j] != arr[i][idx]) break;
							else {
								arr[i][j] *= 2;
								arr[i][idx] = 0;
								break;
							}
						}
//						j = idx;
					}
				}
				for (int i = 0; i < N; i++) {	//오른쪽 옮기기
					for (int j = N-1; j >= 0; j--) {
						if (arr[i][j] == 0) {
							int idx = j-1;
							while (idx >= 0) {
								if (arr[i][idx] != 0) {
									arr[i][j] = arr[i][idx];
									arr[i][idx] = 0;
									break;
								}
								idx--;
							}
//							j = idx;
						}
					}
				}
				move(cnt+1);
				for (int c = 0; c < N; c++) {
					arr[c] = copyArr[c].clone();
				}
			}
		}
	}

	private static void check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] > ans) {
					ans = arr[i][j];
				}
			}
		}
	}
}
