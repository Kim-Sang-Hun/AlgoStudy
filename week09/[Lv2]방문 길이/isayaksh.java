import java.util.*;

class Solution {
    public int solution(String dirs) {
        
        char[] orders = dirs.toCharArray();
        
        int count = 0, x = 5, y = 5;
		int nx, ny;
		boolean[][][] visited = new boolean[11][11][4];
		
		Map<Character, Point> map = new HashMap<Character, Point>();
		map.put('U', new Point(0, -1, 0));
		map.put('D', new Point(0, 1, 2));
		map.put('R', new Point(1, 0, 1));
		map.put('L', new Point(-1, 0, 3));
		
		for(char order : orders) {
			Point p = map.get(order);
			
			nx = x + p.x;
			ny = y + p.y;
			
			if(nx < 0 || nx > 10 || ny < 0 || ny > 10) continue;
			
			if(!visited[y][x][p.dir]) {
				visited[y][x][p.dir] = true;
                visited[ny][nx][(p.dir+2)%4] = true;
				count++;
			}
			
			x = nx;
			y = ny;
			
		}
		
		return count;
    }
    
    static class Point {
		int x, y, dir;
		
		public Point(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
}
