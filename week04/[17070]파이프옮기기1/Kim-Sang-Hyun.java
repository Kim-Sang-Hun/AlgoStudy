import java.util.*;
import java.io.*;

public class Main {

    public static final String VERTICAL = "Vertical";
    public static final String HORIZONTAL = "Horizontal";
    public static final String DIAGONAL = "Diagonal";

    public static int answer = 0;
    public static int N;

    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for(int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        }
        
        dfs(1, 0, VERTICAL);

        System.out.println(answer);

    }

    public static void dfs(int x, int y, String state) {

        if(x == N-1 && y == N-1) {
            answer++;
            return;
        }

        if(state == VERTICAL) {
            if(x+1 < N && arr[y][x+1] == 0) {
                dfs(x+1, y, VERTICAL);
            }
            if(x+1 < N && y+1 < N && arr[y][x+1] == 0 && arr[y+1][x] == 0 && arr[y+1][x+1] == 0) {
                dfs(x+1, y+1, DIAGONAL);
            }
        }

        if(state == HORIZONTAL) {
            if(y+1 < N && arr[y+1][x] == 0) {
                dfs(x, y+1, HORIZONTAL);
            }
            if(x+1 < N && y+1 < N && arr[y][x+1] == 0 && arr[y+1][x] == 0 && arr[y+1][x+1] == 0) {
                dfs(x+1, y+1, DIAGONAL);
            }
        }

        if(state == DIAGONAL) {
            if(x+1 < N && arr[y][x+1] == 0) {
                dfs(x+1, y, VERTICAL);
            }
            if(y+1 < N && arr[y+1][x] == 0) {
                dfs(x, y+1, HORIZONTAL);
            }
            if(x+1 < N && y+1 < N && arr[y][x+1] == 0 && arr[y+1][x] == 0 && arr[y+1][x+1] == 0) {
                dfs(x+1, y+1, DIAGONAL);
            }
        }

    }
}
