package Algo_week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ15666 {
    
    static int N, M;
    static int[] arr, num;
    static Set<String> set;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        num = new int[M];
        set = new LinkedHashSet<>();
        
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        dfs(0, 0);
        
        for (String s : set) {
            System.out.println(s);
        }
        
    }

    private static void dfs(int idx, int depth) {
        
        if (depth == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                sb.append(num[i]).append(' ');
            }
            set.add(sb.toString().trim());
            return;
        }
        
        for (int i = idx; i < N; i++) {
            num[depth] = arr[i];
            dfs(i, depth + 1);
        }
        
    }
}
