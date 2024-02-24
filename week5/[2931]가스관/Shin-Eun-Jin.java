import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Shin-Eun-Jin {
    static int R, C;
    static char[][] map;
    static boolean[][] isVisited;
    static int blockRow;
    static int blockCol;
    static char deletePipe;

    // 상,하,좌,우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static HashMap<Character, ArrayList<Integer>> pipe = new HashMap<Character, ArrayList<Integer>>() {
        {
            put('M', new ArrayList<>(List.of(0, 1, 2, 3)));
            put('|', new ArrayList<>(List.of(0, 1)));
            put('-', new ArrayList<>(List.of(2, 3)));
            put('+', new ArrayList<>(List.of(0, 1, 2, 3)));
            put('1', new ArrayList<>(List.of(1, 3)));
            put('2', new ArrayList<>(List.of(0, 3)));
            put('3', new ArrayList<>(List.of(0, 2)));
            put('4', new ArrayList<>(List.of(1, 2)));
        }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        isVisited = new boolean[R][C];

        // 배열 입력
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // M 위치 찾기
        int MRow = -1;
        int MCol = -1;
        loop:
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'M') {
                    MRow = i;
                    MCol = j;
                    break loop;
                }
            }
        }

        // 해커가 지운 블록의 (행,열) 찾기
        blockRow = -1;
        blockCol = -1;
        dfs(MRow, MCol);
        sb.append(blockRow + 1).append(" ").append(blockCol + 1).append(" ");

        // 해커가 지운 블록 찾기
        pipe.remove('M');
        deletePipe = ' ';
        findBlock();
        sb.append(deletePipe);

        System.out.println(sb);
    }

    static void dfs(int row, int col) {
        char cur = map[row][col];
        isVisited[row][col] = true;

        for (int dir : pipe.get(cur)) {

            int nextRow = row + dr[dir];
            int nextCol = col + dc[dir];

            if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || isVisited[nextRow][nextCol]) {
                continue;
            }

            // 다음 노드에 pipe가 없을 경우
            if (!pipe.containsKey(map[nextRow][nextCol])) {
                blockRow = nextRow;
                blockCol = nextCol;
                continue;
            }

            dfs(nextRow, nextCol);

            if(cur == 'M'){
                break;
            }
        }
    }

    static void findBlock() {
        ArrayList<Integer> dirs = new ArrayList<>();

        // 0: 상, 1: 하, 2: 좌, 3: 우
        for (int i = 0; i < 4; i++) {
            int nextRow = blockRow + dr[i];
            int nextCol = blockCol + dc[i];

             // 범위 벗어났거나, 구조물이 없을 경우
            if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || !pipe.containsKey(map[nextRow][nextCol])) {
                continue;
            }

            // 현재 방향의 반대 방향 (0 <-> 1, 2 <-> 3)
            int oppositeDir = ((i + 1) % 2 == 0 ? i - 1 : i + 1);

            // map[nextRow][nextCol] 위치의 구조물이 map[blockRow][blockCol] 방향으로 흐르는 경우 체크
            ArrayList<Integer> nextDir = pipe.get(map[nextRow][nextCol]);
            if(nextDir.contains(oppositeDir)){
                dirs.add(i);
            }
        }

        // 해당 방향으로 흐르는 파이프 찾기
        for(char p : pipe.keySet()) {

            if(dirs.size() != pipe.get(p).size()) {
                continue;
            }

            boolean isPipe = true;
            for(int d : pipe.get(p)) {
                if(!dirs.contains(d)) {
                    isPipe = false;
                    break;
                }
            }

            if(isPipe) {
                deletePipe = p;
                return;
            }
        }
    }

}
