class Solution {
    public int solution(String dirs) {
        
        boolean[][][] arr = new boolean[11][11][4];
		
		int x = 5, y = 5;
		int cnt = 0;

		for (int i = 0; i < dirs.length(); i++) {
			char d = dirs.charAt(i);
			
			if (d == 'U' && (x-1) >= 0) {
				if (!arr[x-1][y][0]) {
					cnt++;
					arr[x-1][y][0] = true;
					arr[x][y][1] = true;
                    
				}
                x--;
			} else if (d == 'D' && (x+1) <= 10) {
				
				if (!arr[x+1][y][1]) {
					cnt++;
					arr[x+1][y][1] = true;
					arr[x][y][0] = true;
                    
				}
                x++;
			} else if (d == 'R' && (y+1) <= 10) {
				
				if (!arr[x][y+1][2]) {
					cnt++;
					arr[x][y+1][2] = true;
					arr[x][y][3] = true;
                    
				}
                y++;
			} else if (d == 'L' && (y-1) >= 0) {
				
				if (!arr[x][y-1][3]) {
					cnt++;
					arr[x][y-1][3] = true;                    
					arr[x][y][2] = true;
                    
				}
                y--;
			}
		}
        
        return cnt;
    }
}