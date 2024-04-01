package Algo_week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17406 {
	static int N, M, K;
    static int[][] cycle;
    static int[][] map;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cycle = new int[K][3];

        for(int k = 0; k<K; k++) {
            st = new StringTokenizer(br.readLine());
            cycle[k][0] = Integer.parseInt(st.nextToken()) - 1;
            cycle[k][1] = Integer.parseInt(st.nextToken()) - 1;
            cycle[k][2] = Integer.parseInt(st.nextToken());
        }

        permutation(0, new int[K], new boolean[K]);

        System.out.printf("%d", min);
    }

    public static void permutation(int cnt, int[] arr, boolean[] visited) {
        if(cnt == K) {
            doCycle(arr);
            return;
        }
        for(int i=0; i<K; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            arr[cnt] = i;
            permutation(cnt+1, arr, visited);
            visited[i] = false;
        }
    }

    public static void doCycle(int[] arr) {
        int[][] tmp = copyMap();

        for(int k=0; k<K; k++) {
            int r = cycle[arr[k]][0];
            int c = cycle[arr[k]][1];
            int S = cycle[arr[k]][2];

            for(int s=1; s<=S; s++) {
                //위
                int upTmp = tmp[r-s][c+s];
                for(int y = c+s; y > c-s; y--) {
                    tmp[r-s][y] = tmp[r-s][y-1];
                }
                //오른쪽
                int rightTmp = tmp[r+s][c+s];
                for(int x = r+s; x > r-s; x--) {
                    tmp[x][c+s] = tmp[x-1][c+s];
                }
                tmp[r-s+1][c+s] = upTmp;
                //아래
                int leftTmp = tmp[r+s][c-s];
                for(int y = c-s; y < c+s; y++) {
                    tmp[r+s][y] = tmp[r+s][y+1];
                }
                tmp[r+s][c+s-1] = rightTmp;
                //왼쪽
                for(int x = r-s; x < r+s; x++) {
                    tmp[x][c-s] = tmp[x+1][c-s];
                }
                tmp[r+s-1][c-s] = leftTmp;
            }
        }

        getAnswer(tmp);
    }

    public static int[][] copyMap() {
        int[][] tmp = new int[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        return tmp;
    }

    public static void getAnswer(int[][] tmp) {
        for(int i=0; i<N; i++) {
            int sum = 0;
            for(int j=0; j<M; j++) {
                sum += tmp[i][j];
            }
            if(min > sum) min = sum;
        }
    }
}