import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n;
	static class P{
		int x, y;
		P(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static PriorityQueue<P> pq = new PriorityQueue<>(new Comparator<P>() {
		@Override
		public int compare(P o1, P o2) {
			if(o1.y == o2.y) return Integer.compare(o1.x, o2.x);
			return Integer.compare(o1.y, o2.y);
		}
	});
	
	static List<P> lectures = new ArrayList<>();

  /**
  * 정렬된 강의들을 우선순위 큐에 집어넣고, 종점 이후 시점이 진행될 경우 크기 유지
  * 아닐 경우에는 하나의 공간?을 더 소모한다는 개념으로 크기를 더해준다.
  *
  */
	static void solution() throws Exception{
		pq.add(new P(lectures.get(0).x, lectures.get(0).y));
		for(int i = 1;i < n; ++i) {
			P cur = lectures.get(i);
			if(pq.peek().y <= cur.x) {
				pq.remove();
			}
			pq.add(cur);
		}
		System.out.println(pq.size());
	}
	
	public static void main(String[] args) throws Exception{
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		int s, e;
		for(int i = 0;i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			lectures.add(new P(s, e));
		}
		
		Collections.sort(lectures, new Comparator<P>() {
			@Override
			public int compare(P o1, P o2) {
				if(o1.x == o2.x) {
					return Integer.compare(o1.y, o2.y);
				}
				return Integer.compare(o1.x,  o2.x);
			}
		});
		solution();
	}
}
