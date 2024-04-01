package boj;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JUN2573 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, answer = 0;
    static int[][] a;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        a = new int[n][2];
        for(int i = 0;i < n; ++i){
            st = new StringTokenizer(br.readLine());
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = Integer.parseInt(st.nextToken());
        }
    }
    
    static void breakEgg(int x, int y) {
        a[x][0] -= a[y][1];
        a[y][0] -= a[x][1];
    }

    static void reviveEgg(int x, int y) {
        a[x][0] += a[y][1];
        a[y][0] += a[x][1];
    }

    static void checkAnswer(){
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if(a[i][0] <= 0) ++cnt;
        }
        answer = Math.max(answer, cnt);
    }

    static void rec(int index){
        if(index == n){
            checkAnswer();
            return;
        }
        if(a[index][0] <= 0) {
            rec(index + 1);
            return;
        }
        boolean broken = false;
        for (int i = 0; i < n; ++i) {
            if(index == i || a[i][0] <= 0) continue;
            broken = true;
            breakEgg(index, i);
            rec(index + 1);
            reviveEgg(index, i);
        }
        if(!broken) rec(n);
    }

    static void solution() {
        rec(0);
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
