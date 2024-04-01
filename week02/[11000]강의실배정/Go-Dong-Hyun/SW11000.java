

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SW11000 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		//입력받은 강의의 우선순위를 정해준다
		PriorityQueue<int[]> pq = new PriorityQueue<>(((o1,o2) -> {
			if (o1[0] == o2[0]) {
				return o1[1] - o2[1];
			} else {
				return o1[0] - o2[0];
			}
		}));
		
		//강의를 입력받아서 pq에 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			pq.offer(new int[] {s,e});
		}
		
		//강의실을 나타내고, 강의의 종료시간을 담을 우선순위 큐 room을 만든다.
		PriorityQueue<Integer> room = new PriorityQueue<>();
		room.offer(0);
		
		//room에서 하나씩 값을 peek해서 값을 비교하고 poll을 할지 말지 결정한다.
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			if(room.peek() <= cur[0]) {
				room.poll();
			}
			room.offer(cur[1]);
		}
		
		System.out.println(room.size());
	}
}
