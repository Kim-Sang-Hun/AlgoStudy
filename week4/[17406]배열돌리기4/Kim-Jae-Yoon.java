import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, k, answer = (int)1e9;
    static int[][] a, tmp;
    static boolean[] vis;
    static int[] seq;
    static List<Info> cmd = new ArrayList<>();

    static class Info {
        int r, c, s;

        Info(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    static void solution() throws IOException {
        int r, c, s;
        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            cmd.add(new Info(r, c, s));
        }
        perm(0);
        System.out.println(answer);
    }

    //순열 기반으로 가능한 모든 케이스를 연산한다.
    static void perm(int cnt) {
        if(cnt == k){
            rotate();
            return;
        }
        for (int i = 0; i < k; ++i) {
            if(vis[i]) continue;
            vis[i] = true;
            seq[cnt] = i;
            perm(cnt + 1);
            vis[i] = false;
        }
    }

    //k번의 회전 연산을 수행하도록 하는 메서드
    //각 회차에서 r, c, s를 기반으로 실제 회전 메서드를 불러 가능한 케이스들에 대한 결과값을 확인하도록 한다.
    static void rotate(){
        tmp = copy();
        for (int i = 0; i < k; ++i) {
            int r = cmd.get(seq[i]).r;
            int c = cmd.get(seq[i]).c;
            int s = cmd.get(seq[i]).s;
            cycle(r, c, s);
        }
        getSum();
    }

    static void getSum(){
        for (int i = 1; i <= n; ++i) {
            int sum = 0;
            for (int j = 1; j <= m; ++j) {
                sum += tmp[i][j];
            }
            answer = Math.min(answer, sum);
        }
    }

    static int[][] copy() {
        int[][] temp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                temp[i][j] = a[i][j];
            }
        }
        return temp;
    }

    //실제 회전 연산을 수행하는 메서드
    //외곽에서부터 회전을 하고 점차 내부로 들어가서 회전시키는 재귀 구조 
    static void cycle(int r, int c, int s) {
        if(s == 0) return;
        int temp = tmp[r - s][c - s];
        for (int i = r - s; i < r + s; ++i) {
            tmp[i][c - s] = tmp[i + 1][c - s];
        }
        for (int i = c - s; i < c + s; ++i) {
            tmp[r + s][i] = tmp[r + s][i + 1];
        }
        for (int i = r + s; i > r - s; --i) {
            tmp[i][c + s] = tmp[i - 1][c + s];
        }
        for (int i = c + s; i > c - s; --i) {
            tmp[r - s][i] = tmp[r - s][i - 1];
        }
        tmp[r - s][c - s + 1] = temp;
        cycle(r, c, s - 1);
    }

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[n + 1][m + 1];
        seq = new int[k];
        vis = new boolean[k];
        for(int i = 1;i <= n; ++i){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; ++j) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
