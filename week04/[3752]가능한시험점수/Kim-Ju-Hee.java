package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/*
 * nums는 idx에 해당하는 배점의 문제 개수를 저장하는 배열 (ex. 두 번째 tc의 경우 nums[1] = 10)
 * sums Set에 해당 배점이 n개일때 가능한 점수를 넣음 (ex. 2점짜리가 2개일 때 0,2,4를 넣음)
 * 모든 배점에 대해 이미 sums Set에 들어있는 값과 더한 값을 sums Set에 넣음
 * (ex. (0,2,4)가 들어있는 sums에 다음 배점이 3점짜리 1개일 때, 가능한 점수인(0,3)과 원래 값들을 더한 (0,2,4,3,5,9)를 넣게 됨 )
 * */
public class JUN3752__ {
	static int N, nums[];
	static Set<Integer> sums, scores;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			nums = new int[101]; // 점수는 100점까지 있음
			scores = new HashSet<>();
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int input = Integer.parseInt(st.nextToken());
				nums[input] += 1;
				scores.add(input);
			}
			
			// 계산
			sums = new HashSet<>();
			Integer[] scoreArr = scores.toArray(new Integer[0]);
			for (int j = 0; j <= nums[scoreArr[0]]; j++) { // 처음 값 초기화
				sums.add(scoreArr[0]*j) ;
			}
			
			for (int n = 1; n < scoreArr.length; n++) {
				
				int[] now = new int[nums[scoreArr[n]]+1]; // 현재 배점의 가능한 점수 배열
				for (int j = 0; j <= nums[scoreArr[n]]; j++) {
					now[j] = scoreArr[n]*j;
				}
	
				for (int s : sums.toArray(new Integer[0])) { // 기존 sums의 값들에 현재 가능한 점수 더한 값 추가
					for (int i : now) {
						sums.add(s + i);
					}
				}
			}
			
			//출력
			System.out.printf("#%d %d\n", tc, sums.size());
		}
	}
}
