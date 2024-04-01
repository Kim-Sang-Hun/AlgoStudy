import java.io.*;
import java.util.*;

class Solution {
    static ArrayList<Edge> edgeList;
    
    static class Edge {
        int start;
        int end;
        
        Edge(int startX, int startY, int endX, int endY) {
            start = startY * 100 + startX;
            end = endY * 100 + endX;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            
            if (o == null || getClass() != o.getClass())
                return false;
            
            Edge edge = (Edge) o;
            
            if (start == edge.start && end == edge.end)
                return true;
            
            if (start == edge.end && end == edge.start)
                return true;
            
            return false;
        }
    }
    
    public int solution(String dirs) {
        edgeList = new ArrayList<Edge>();
        
        int x = 5;
        int y = 5;
        int dirsLength = dirs.length();
        
        for (int i = 0; i < dirsLength; i++) {
            char c = dirs.charAt(i);
            Edge edge = null;
            boolean isExist = false;
            
            if (c == 'U') {
                if (y + 1 > 10)
                    continue;
                
                edge = new Edge(x, y, x, y + 1);
                y++;
            }
            
            else if (c == 'D') {
                if (y - 1 < 0)
                    continue;
                
                edge = new Edge(x, y, x, y - 1);
                y--;
            }
            
            else if (c == 'R') {
                if (x + 1 > 10)
                    continue;
                
                edge = new Edge(x, y, x + 1, y);
                x++;
            }
            
            else if (c == 'L') {
                if (x - 1 < 0)
                    continue;
                
                edge = new Edge(x, y, x - 1, y);
                x--;
            }
            
            if (edge != null) {
                for (Edge e : edgeList) {
                    if (e.equals(edge)) {
                        isExist = true;
                        break;
                    }
                }
                
                if (!isExist) {
                    edgeList.add(edge);
                }
            }
        }
        
        return edgeList.size();
    }
}
