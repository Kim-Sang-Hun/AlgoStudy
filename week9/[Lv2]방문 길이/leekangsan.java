import java.util.*;

class Solution {
    class Pos {
        int si, sj, ni, nj;
        Pos(int si, int sj, int ni, int nj) {
            this.si=si;
            this.sj=sj;
            this.ni=ni;
            this.nj=nj;
        }
        
        @Override
        public boolean equals(Object obj) { // A->B 또는 B->A 면 동일한 edge
            Pos p = (Pos)obj;
            if(this.si==p.si && this.sj==p.sj && this.ni==p.ni && this.nj==p.nj) return true;
            if(this.si==p.ni && this.sj==p.nj && this.ni==p.si && this.nj==p.sj) return true;
            return false;
        }
        
        @Override
        public int hashCode() { // 시작점(si, sj) 종료점(ni, nj) 좌표로 hash 생성
            if(si<ni) {
                int temp = ni;
                ni = si;
                si = temp;
            }
            if(sj<nj) {
                int temp = nj;
                nj = sj;
                sj = temp;
            }
            String str = ""+si+sj+ni+nj;
            return str.hashCode();
        }
    }
    
    
    int[][] arr = new int[11][11];
    int[] di = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    int[] dj = {0, 0, -1, 1};
    HashMap<Character, Integer> move = new HashMap<>();
    HashSet<Pos> edge = new HashSet<>();
    {
        move.put('U', 0);
        move.put('D', 1);
        move.put('L', 2);
        move.put('R', 3);
    }
    public int solution(String dirs) {
        int len = dirs.length();
        int  si = 5;
        int  sj = 5;
        int k;
        
        for(int i=0; i<len; i++) {
            k = move.get(dirs.charAt(i));
            int ni = si + di[k];
            int nj = sj + dj[k];
            if(!isIn(ni, nj)) continue;
            edge.add(new Pos(si, sj, ni, nj));
            si = ni;
            sj = nj;
        }
        return edge.size();
    }
    
    private boolean isIn(int i, int j) {
        if(0 <= i && i <= 10 && 0 <= j && j <= 10) 
            return true;
        return false;
    }
}
