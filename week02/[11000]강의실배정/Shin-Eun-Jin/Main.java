import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		int N = Integer.parseInt(br.readLine());
		Lesson[] lessons = new Lesson[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());  // 시작 시간
			int T = Integer.parseInt(st.nextToken());  // 종료 시간
			
			lessons[i] = new Lesson(S, T);
		}
		
		Arrays.sort(lessons);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(lessons[0].endTime);
		
		
		for(int i = 1; i < lessons.length; i++) {
			if(pq.peek() <= lessons[i].startTime) {
				pq.poll();
			}
			pq.offer(lessons[i].endTime);
		}
		
		System.out.println(pq.size());
	}

}

class Lesson implements Comparable<Lesson> {
	int startTime;
	int endTime;
	
	Lesson(int startTime, int endTime) {
		this.startTime =startTime;
		this.endTime = endTime;
	}
	
	@Override
	public int compareTo(Lesson o) {
		if(this.startTime == o.startTime) {
			return this.endTime-o.endTime;
		}
		return this.startTime - o.startTime;
	}
}
