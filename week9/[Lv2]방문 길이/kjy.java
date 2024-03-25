import java.util.*;
import java.io.*;

/*
  vis 배열을 3D Array로 설정했습니다.
  지나온 길이 중복된 경로인지 탐색하기 위해 고안한 방법으로,
  특정 정점으로부터 4개의 방향에 대해 출발한 전적이 있는지 확인하는 것으로 경로 중복을 확인할 수 있습니다.
*/

class Solution {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int x = 5, y = 5, answer = 0;
    static boolean[][][] vis = new boolean[11][11][4];
    
    public int solution(String dirs) {
        for(int i = 0;i < dirs.length(); ++i){
            int dir = getDir(dirs.charAt(i));
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(outRange(nx, ny)) continue;
            if(!vis[nx][ny][dir]) {
                ++answer;
                vis[nx][ny][dir] = true;
                if(dir % 2 == 0) dir = 2 - dir;
                else dir = 4 - dir;
                vis[x][y][dir] = true;
            }
            x = nx;
            y = ny;
        }
        return answer;
    }
    
    static int getDir(char dir){
        if(dir == 'L') return 0;
        if(dir == 'D') return 1;
        if(dir == 'R') return 2;
        return 3;
    }
    
    static boolean outRange(int nx, int ny){
        if(nx < 0 || ny < 0 || nx > 10 ||  ny > 10) return true;
        return false;
    }
}
