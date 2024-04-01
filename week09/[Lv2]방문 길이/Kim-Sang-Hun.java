/*
* 3차원 boolean배열 visited를 사용한다. 위방향 = 0 왼 = 1 아래 = 2 오른 = 3의 길은 visited 되었다는 의미이다.
* 방향에 따라 visited처리해주고 도착지점에서 반대방향으로의 길도 visited처리 해준다.
* 새로운 길(visited되지 않은) 길이라면 answer를 하나 올려준다.
*/
class Solution {
    public int solution(String dirs) {
        boolean[][][] visited = new boolean[4][11][11];
        int answer = 0;
        int y = 5; 
        int x = 5;
        
        for (int i = 0; i < dirs.length(); i++) {
            char c = dirs.charAt(i);
            if (c == 'U') {
                if (y - 1 < 0) continue;
                if (!visited[0][y][x]) {
                    ++answer;
                    visited[0][y][x] = true;
                    visited[2][y-1][x] = true;
                }
                y -= 1;
            } else if (c == 'L') {
                if (x - 1 < 0) continue;
                if (!visited[1][y][x]) {
                    ++answer;
                    visited[1][y][x] = true;
                    visited[3][y][x-1] = true;
                }
                x -= 1;
            } else if (c == 'D') {
                if (y + 1 > 10) continue;
                if (!visited[2][y][x]) {
                    ++answer;
                    visited[2][y][x] = true;
                    visited[0][y+1][x] = true;
                }
                y += 1;
            } else if (c == 'R') {
                if (x + 1 > 10) continue;
                if (!visited[3][y][x]) {
                    ++answer;
                    visited[3][y][x] = true;
                    visited[1][y][x+1] = true;
                }
                x += 1;
            }
        }
        return answer;
    }
}
