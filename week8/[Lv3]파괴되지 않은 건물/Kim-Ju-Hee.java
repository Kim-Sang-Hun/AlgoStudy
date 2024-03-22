import java.util.*;
import java.io.*;

class Solution {
    static int N, M, damage[][];

    public int solution(int[][] board, int[][] skill) {
        
        N = board.length;
        M = board[0].length;
        damage = new int[N+1][M+1];
        
        for(int[] s : skill){
            int attack = 0;
            if(s[0] == 1){ // 공격
                attack = -s[5];
            }else if(s[0] == 2){ // 회복
                attack = s[5];
            }
            
            damage[s[1]][s[2]] += attack;
            damage[s[1]][s[4]+1] -= attack;
            damage[s[3]+1][s[2]] -= attack;
            damage[s[3]+1][s[4]+1] += attack;
        }
        
        for(int i = 1; i <= N; i++){
            for(int j = 0; j<= M; j++){
                damage[i][j] = damage[i-1][j] + damage[i][j];
            }
        }
        
        for(int i = 0; i <= N; i++){
            for(int j = 1; j<= M; j++){
                damage[i][j] = damage[i][j-1] + damage[i][j];
            }
        }
        
        int answer = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(board[i][j] + damage[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}
