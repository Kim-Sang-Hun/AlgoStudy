import java.util.*;
import java.io.*;
/*
풀기 실패.. 마지막 테스트 케이스에서 틀림
*/
public class Main {
    static int n, size, fullness, time;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int[] shark = {0, 0};
    static List<List<Fish>> fish;
    static List<Fish> edible;
    
    static class Fish implements Comparable<Fish> {
        int r, c, size;
        Fish(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }
        
        @Override
        public int compareTo(Fish o) {
            int len1 = Math.abs(this.r - shark[0]) + Math.abs(this.c - shark[1]);
            int len2 = Math.abs(o.r - shark[0]) + Math.abs(o.c - shark[1]);
            
            if (len1 == len2) {
                if (this.r == o.r) {
                    return Integer.compare(this.c, o.c);
                }
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(len1, len2);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        size = 2;
        fish = new ArrayList<>();
        edible = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            fish.add(new ArrayList<>());
        }
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark[0] = i;
                    shark[1] = j;
                } else if (map[i][j] != 0) {
                    fish.get(map[i][j]).add(new Fish(i, j, map[i][j]));
                }
            }
        }
        map[shark[0]][shark[1]] = 0;
        edible.addAll(fish.get(1));
        while (true) {
            if (edible.isEmpty()) break;
            Collections.sort(edible);
            Fish target = edible.get(0);
            edible.remove(0);
            if (!goEatFish(target)) break;
        }
        System.out.print(time);
    }
    
    public static boolean goEatFish(Fish target) {
        boolean[][] visited = new boolean[n][n];
        Fish s = new Fish(shark[0], shark[1], 0);
        visited[s.r][s.c] = true;
        Queue<Fish> qu = new ArrayDeque<>();
        qu.add(s);
        boolean canEat = false;
        
        while (!qu.isEmpty()) {
            s = qu.poll();
            if (s.r == target.r && s.c == target.c) {
                canEat = true;
                shark[0] = target.r;
                shark[1] = target.c;
                time += s.size;
                fullness++;
                if (fullness == size) {
                    if (size <= 6) {
                        edible.addAll(fish.get(size++));
                    }
                    fullness = 0;
                }
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = s.r + dr[i];
                int nc = s.c + dc[i];
                
                if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc] || map[nr][nc] > size) continue;
                visited[nr][nc] = true;
                qu.add(new Fish(nr, nc, s.size + 1));
            }
        }
        return canEat;
    }
}
