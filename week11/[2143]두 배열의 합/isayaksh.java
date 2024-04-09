import java.util.*;
import java.io.*;

public class isayaksh {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long count = 0;
        Map<Long, Long> counter = new HashMap<Long, Long>();

        long T = Integer.parseInt(br.readLine());

        int N = Integer.parseInt(br.readLine());
        long[] A = new long[N+2];
        st = new StringTokenizer(br.readLine());
        for(int n = 1; n < N+1; n++) A[n] = Long.parseLong(st.nextToken());
        for(int n = 1; n < N+1; n++) A[n] += A[n-1];

        int M = Integer.parseInt(br.readLine());
        long[] B = new long[M+2];
        st = new StringTokenizer(br.readLine());
        for(int m = 1; m < M+1; m++) B[m] = Long.parseLong(st.nextToken());
        for(int m = 1; m < M+1; m++) B[m] += B[m-1];

        for(int s = 0; s < N; s++) {
            for(int e = s+1; e < N+1; e++) {
                long sum = A[e] - A[s];
                if(counter.containsKey(sum)) counter.put(sum, counter.get(sum)+1);
                if(!counter.containsKey(sum)) counter.put(sum, 1L);
            }
        }

        for(int s = 0; s < M; s++) {
            for(int e = s+1; e < M+1; e++) {
                long target = T - (B[e] - B[s]);
                if(counter.containsKey(target)) count += counter.get(target);
            }
        }

        System.out.println(count);

    }

    

}
