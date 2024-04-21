import java.util.*;
import java.io.*;

public class isayaksh {
    
    private static int N, M;
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        for(int n = 0; n < N; n++) {
            graph[n] = Arrays.stream(br.readLine().split(""))
            .mapToInt(Integer::parseInt)
            .toArray();
        }


        // [EXCEPTION] 1 X 1 크기의 표 예외 처리
        if(N == 1 && M == 1) {
            if(isSquareNumber(graph[0][0])) {
                System.out.println(graph[0][0]);
                return;
            }
        }

        int answer = -1;
        int nx, ny, arithmeticSequenceNumber;

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                for(int dy = -N+1; dy < N; dy++) {
                    for(int dx = -M+1; dx < M; dx++) {
                        if(dx == 0 && dy == 0) continue;
                        
                        arithmeticSequenceNumber = 0;
                        nx = x;
                        ny = y;
                        
                        while(0 <= nx && nx < M && 0 <= ny && ny < N) {
                            arithmeticSequenceNumber = arithmeticSequenceNumber * 10 + graph[ny][nx];

                            if(answer < arithmeticSequenceNumber && isSquareNumber(arithmeticSequenceNumber)) answer = arithmeticSequenceNumber;

                            nx += dx;
                            ny += dy;
                        }
                    }
                }
            }
        }

        System.out.println(answer);
        
    }

    private static boolean isSquareNumber(int number) {
        return 0 == Math.sqrt(number) % 1;
    }

}
