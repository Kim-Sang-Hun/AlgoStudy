import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[][] a;
    static long[][] dp;
    static final int[] dx = {0, 1};
    static final int[] dy = {1, 0};

    static void input() throws IOException {
        a = new int[4][8];
        for (int i = 0; i < 4; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 8; ++j) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    //점인지 확인
    static boolean onPoint(int idx) {
        if(a[idx][0] == a[idx][6]) {
            if(a[idx][1] == a[idx][7] || a[idx][3] == a[idx][5]) return true;
        }
        if(a[idx][2] == a[idx][4]){
            if(a[idx][1] == a[idx][7] || a[idx][3] == a[idx][5]) return true;
        }
        return false;
    }

    //선인지 확인
    static boolean onLine(int idx) {
        if(a[idx][2] == a[idx][4] || a[idx][0] == a[idx][6]) return true;
        if(a[idx][3] == a[idx][5] || a[idx][1] == a[idx][7]) return true;
        return false;
    }

    //아무것도 아닌지 확인
    static boolean onEmpty(int idx) {
        if(a[idx][2] < a[idx][4] || a[idx][6] < a[idx][0]) return true;
        if(a[idx][7] < a[idx][1] || a[idx][3] < a[idx][5]) return true;
        return false;
    }

    static void solution(){
        for (int i = 0; i < 4; ++i) {
            //공통부분 없는지 체크
            if(onEmpty(i)) sb.append("d\n");
            //점만 겹치는지 체크
            else if(onPoint(i)) sb.append("c\n");
            //선분이 겹치는지 체크
            else if(onLine(i)) sb.append("b\n");
            //직사각형 날먹 달고~
            else sb.append("a\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
