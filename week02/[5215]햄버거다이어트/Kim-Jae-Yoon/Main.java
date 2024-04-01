import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int t, n, l, answer;
    static int[][] menu;
    static StringBuilder sb = new StringBuilder();

     /**
     *
     * @param taste - 재귀를 거치며 얻은 맛의 합
     * @param cal - 재귀를 거치며 얻은 칼로리 합
     * @param idx - 탐색할 목차
     */
    static void rec(int taste, int cal, int idx){
        if(idx == n){
            if(answer < taste) answer = taste;
            return;
        }
        rec(taste, cal, idx + 1);
        if(cal + menu[idx][1] > l) return;
        rec(taste + menu[idx][0], cal + menu[idx][1], idx + 1);
    }

    static void solution(){
        rec(0, 0, 0);
    }

    static void input() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        menu = new int[n][2];
        for(int i = 0;i < n; ++i){
            st = new StringTokenizer(br.readLine());
            menu[i][0] = Integer.parseInt(st.nextToken());
            menu[i][1] = Integer.parseInt(st.nextToken());
        }
        answer = 0;
    }

    public static void main(String[] args) throws Exception{
        t = Integer.parseInt(br.readLine());
        for(int x = 1;x <= t; ++x) {
            input();
            solution();
            sb.append("#").append(x).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }
}
