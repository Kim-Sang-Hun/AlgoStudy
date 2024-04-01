import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, L;
    static int[] score;
    static int[] calorie;
    static boolean[] isSelect;
    static int maxScore;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            score = new int[N];
            calorie = new int[N];
            isSelect = new boolean[N];
            maxScore = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                calorie[i] = Integer.parseInt(st.nextToken());
            }
            
            combi(0,0,0);
            
            sb.append("#").append(test_case).append(" ").append(maxScore).append("\n");
        }
        
        System.out.println(sb);
    }
    

    static void combi(int totalCalorie, int totalScore, int count) {
    	if(totalCalorie <= L) {
    		maxScore = Math.max(maxScore, totalScore);
    	}
    	else {
    		return;
    	}
    	
    	if(count == N) return;
    	
    	combi(totalCalorie + calorie[count], totalScore + score[count], count+1);
		combi(totalCalorie, totalScore, count+1);

    }
}
