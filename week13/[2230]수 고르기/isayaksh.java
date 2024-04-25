import java.util.*;
import java.io.*;

public class isayaksh {

    private static int N, M;
    private static int[] A;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N];
        for(int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(br.readLine());
        }

        // M이 0일 경우 예외처리
        if(M == 0) {
            System.out.println(0);
            return;
        }

        // 투포인터
        Arrays.sort(A);

        int answer = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int gap;

        while(end < N) {
            gap = A[end] - A[start];

            if(M <= gap && gap < answer) answer = gap;

            if(M <= gap) start++;
            else if(gap < M) end++;
        }
        
        System.out.println(answer);
    }

}
