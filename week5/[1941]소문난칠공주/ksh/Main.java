import java.util.*;
import java.io.*;

public class Main {

    private static final int N = 5;
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {-1, 0, 1, 0};

    private static char[][] arr = new char[N][N];
    private static int[] sevenPrincess = new int[7];
    private static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int y = 0; y < N; y++) {
            arr[y] = br.readLine().toCharArray();
        }

        combination(0, 0);

        System.out.println(answer);
        
        
    }

    private static void combination(int depth, int start) {

        if(depth == 7) {
            if(!isConnected() || !isPossible()) return;
            answer++;
            return;
        }

        for(int i = start; i < N*N; i++) {
            sevenPrincess[depth] = i;
            combination(depth+1, i+1);
        }
    }

    private static boolean isConnected() {


        boolean[] target = new boolean[N*N];
        boolean[] visited = new boolean[N*N];
        Deque<Integer> deque = new ArrayDeque<Integer>();

        for(int t : sevenPrincess) {
            target[t] = true;
        }

        visited[sevenPrincess[0]] = true;
        deque.add(sevenPrincess[0]);

        while(!deque.isEmpty()) {

            int idx = deque.poll();

            int x = idx % N;
            int y = idx / N;

            int nx, ny;

            for(int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny * N + nx]) continue;

                if(!target[ny * N + nx]) continue;

                visited[ny * N + nx] = true;
                deque.add(ny * N + nx);

            }

        }

        for(int t : sevenPrincess) {
            if(!visited[t]) return false;
        }


        return true;
    }

    private static boolean isPossible() {
        int count = 0;
        for(int t : sevenPrincess) {
            if(arr[t/N][t%N] == 'S') count++;
        }

        return count > 3;
    }
}