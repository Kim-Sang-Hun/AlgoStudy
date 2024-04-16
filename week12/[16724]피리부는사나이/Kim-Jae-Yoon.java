import java.sql.Array;
import java.util.*;
import java.io.*;
/*
  Title: 피리 부는 사나이
  Tier: Gold 3
  Algorithm: Union-Find
  Constraint: 1 Second, 256MB
  Additional Idea: Transform 2D array to 1D array
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static char[][] area;
    static int[] par;
    static Set<Integer> s = new HashSet<>();
    //우하좌상
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static void solution() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int dir = getDir(i, j);
                int nx = i + dx[dir];
                int ny = j + dy[dir];
                union(i * m  + j, nx * m + ny);
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                s.add(find(i * m + j));
            }
        }
        System.out.println(s.size());
    }

    static int getDir(int x, int y) {
        if(area[x][y] == 'R') return 0;
        else if(area[x][y] == 'D') return 1;
        else if(area[x][y] == 'L') return 2;
        return 3;
    }

    static void union(int a,  int b) {
        a = find(a);
        b = find(b);
        par[b] = a;
    }

    static int find(int a){
        if(par[a] == a) return a;
        return par[a] = find(par[a]);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        area = new char[n][m];
        par = new int[n * m];
        for (int i = 0; i < n; ++i) {
            String tmp = br.readLine();
            for (int j = 0; j < m; ++j) {
                area[i][j] = tmp.charAt(j);
                par[i * m + j] = i * m + j;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
