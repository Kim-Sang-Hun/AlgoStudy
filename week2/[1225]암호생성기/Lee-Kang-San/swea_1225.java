package SWEA.D3;

import java.io.*;
import java.util.*;
 
public class swea_1225 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {                                 
        for(int i=1; i<=10; ++i) {							// TC 10개 고정
            // 입력
        	Queue<Integer> q = new ArrayDeque<Integer>();	// 8개 데이터 입력 받을 큐
            int c = Integer.parseInt(br.readLine());        // TC 번호
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<8; j++)
                q.offer(Integer.parseInt(st.nextToken())); 	// 초기 암호
 
            // 풀이
            int cnt=0;
            while(true) {
                if(q.peek()-(cnt%5+1)<=0) {         		// 0이하가 되는 경우 -> 암호
                    q.poll();
                    sb.append("#"+c+" ");
                    while(!q.isEmpty()) sb.append(q.poll()+" ");
                    sb.append("0\n");						// 암호 끝에 0 offer() 하는 대신 대신 출력에만 0 추가
                    break;
                }
                q.offer(q.poll()-(cnt%5+1));				// 첫번째 원소에서 필요한 만큼 뺀 후 offer()     
                cnt++;
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}