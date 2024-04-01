package Algo_week08;

import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        int[][] arr = new int[N+1][M+1];
        
        for (int[] s : skill) {
            if (s[0] == 1)
                s[5] = -s[5];
            arr[s[1]][s[2]] += s[5];
            arr[s[3]+1][s[4]+1] += s[5];
            arr[s[1]][s[4]+1] -= s[5];
            arr[s[3]+1][s[2]] -= s[5];
        }
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] += arr[i-1][j];
            }
        }
        
        for (int j = 1; j < M; j++) {
            for (int i = 0; i < N; i++) {
                arr[i][j] += arr[i][j-1];
            }
        }
        
        
        for (int i= 0; i < N; i++) {
            for (int j = 0; j < M; j ++) {
                board[i][j] += arr[i][j];
            }
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}