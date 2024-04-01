import java.util.*;
import java.io.*;

/*
    03.23 06:30 ~ 07:44
    
    누적합 알고리즘은 바로 떠올렸으나, 기존처럼 밀어버리는 누적합으로는 절대 해결할 수 없습니다.
    추가적인 아이디어가 필요한데, 기존 누적합도 구간 설정을 한 후 밀어버리는 구조이므로 구간 설정을 skill 회 진행한 후 한 번에 밀어버리는 아이디어로 접근했습니다.

    0 0 0 0 0          0 0 0 0 0       0 0 0 0 0 
    0 0 0 0 0          0 2 0 0-2       0 2 2 2 0 
    0 0 0 0 0          0 0 0 0 0       0 2 2 2 0 
    0 0 0 0 0          0-2 0 0 2       0 0 0 0 0 
    0 0 0 0 0          0 0 0 0 0       0 0 0 0 0 

    여기서 -2가 2번 있어 하단의 2부분이 -2가 되어야 한다고 생각할 수 있으나, 오른쪽 또는 아래로 밀면서 둘 중 하나의 -2가 선제적으로 지워지므로 결국 문제없이 연산이 수행됩니다.(0으로 만들 수 있음)
    
    O(n * m * skill): 이렇게 하려면 대신 누적합 계산을 간소화할 필요가 있었다.
                      n * m만 해도 1e6이 넘는데, 100 skill만 넘어도 컷이므로
                      2차원 배열 합을 매 번 전부 구하기보다, 시도할 때 마다 각 끝점에 집중해야 효율성을 챙길 수 있습니다.
*/

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int x = board.length, y = board[0].length;

        //prefix가 int배열이어도 되는 이유는, 모든 skill이 -5e2씩 25e4 이루어진다 하더라도 1.25e8이므로 long 배열에 담을 필요가 없습니다.
        int[][] prefix = new int[x + 1][y + 1];
        for(int[] s : skill){
            if(s[0] == 1) s[5] = -s[5];
            //s1:r1, s2:c1, s3:r2, s4:c2
            prefix[s[1]][s[2]] += s[5];
            prefix[s[3] + 1][s[4] + 1] += s[5];
            prefix[s[1]][s[4] + 1] -= s[5];
            prefix[s[3] + 1][s[2]] -= s[5];
        }
        
        //1. 오른쪽으로 밀자
        for(int i = 0;i <= x; ++i){
            for(int j = 0;j < y; ++j){
                prefix[i][j + 1] += prefix[i][j];
            }
        }
        
        //2. 아래로 밀자
        for(int i = 0;i < x; ++i){
            for(int j = 0;j <= y; ++j){
                prefix[i + 1][j] += prefix[i][j];
            }
        }
        
        //3. 기존 board에 이식한다.
        for(int i = 0;i < x; ++i){
            for(int j = 0;j < y; ++j){
                board[i][j] += prefix[i][j];
            }
        }
        
        //4. 간절하게 정답이 나오기를 기도한다.
        int answer = 0; //최대 1e6만 담을 것이므로 overflow 문제없음
        for(int i = 0;i < x; ++i){
            for(int j = 0;j < y; ++j){
                if(board[i][j] > 0) ++answer;
            }
        }
        return answer;
    }
}
