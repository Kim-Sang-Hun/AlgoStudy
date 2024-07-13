package Algo_week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// ㅅㅂ 문제가 가슴에 와닿지가 안흔ㄴ다
public class BOJ7662 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			
			PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> minQ = new PriorityQueue<>();
			HashMap<Integer, Integer> map = new HashMap<>();
			
			for (int k = 1; k <= K; k++) {
				st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				if (command.equals("I")) {
					maxQ.add(num);
					minQ.add(num);
					map.put(num, map.getOrDefault(num, 0)+1);
				} else {
					if (num == 1) { //최대값 삭제
						removeElement(maxQ, map);
					} else if (num == -1) { //최소값
						removeElement(minQ, map);
					}
				}
			}
			
			cleanUp(maxQ, map);
            cleanUp(minQ, map);
			
			if (maxQ.isEmpty() || minQ.isEmpty()) {
				System.out.println("EMPTY");
			}
			else {
				System.out.println(maxQ.poll()+ " " + minQ.poll());
			}
		}
		
	}

	private static void removeElement(PriorityQueue<Integer> pq, HashMap<Integer, Integer> map) {
		while (!pq.isEmpty() && map.get(pq.peek()) == 0) {
			pq.poll();
		}
		
		if (!pq.isEmpty()) {
			int element = pq.poll();
			map.put(element, map.get(element) -1 );
		}
	}
	
	private static void cleanUp(PriorityQueue<Integer> pq, HashMap<Integer, Integer> map) {
        while (!pq.isEmpty() && (!map.containsKey(pq.peek()) || map.get(pq.peek()) == 0)) {
            pq.poll();
        }
    }
}
