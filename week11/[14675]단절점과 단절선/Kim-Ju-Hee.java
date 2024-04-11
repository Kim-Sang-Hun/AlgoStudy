package april2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 모든 간선은 단절선, 연결 간선 개수가 2개 이상이면 단절점
public class JUN14675_단절점과단절선_김주희 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] nodes = new int[N+1];
		StringTokenizer st;
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			nodes[n1]++;
			nodes[n2]++; // 연결된 간선 개수
		}
		
		int q = Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if(t==1) {
				if(nodes[k] >= 2) System.out.println("yes");
				else System.out.println("no");
			}else if(t==2) {
				System.out.println("yes");
			}
		}

	}

}
