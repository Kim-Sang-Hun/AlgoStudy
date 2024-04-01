import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

import static java.nio.file.Files.move;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, answer = 0;
    static final int[] dx ={0,1,0,-1};
    static final int[] dy ={1,0,-1,0};
    static int[][] area;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        area = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void dfs(int cnt) {
        if(cnt == 5) {
            getAnswer();
            return;
        }
        int[][] copy = copy();
        for (int dir = 0; dir < 4; ++dir) {
            move(dir);
            dfs(cnt + 1);
            recover(copy);
        }
    }

    static void left() {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                if(area[i][j] == 0) continue;
                dq.add(area[i][j]);
                area[i][j] = 0;
            }
            int idx = n - 1;
            while (!dq.isEmpty()) {
                int cur = dq.poll();
                if (area[i][idx] == cur) {
                    area[i][idx--] = cur << 1;
                }
                else if (area[i][idx] == 0) {
                    area[i][idx] = cur;
                }
                else {
                    area[i][--idx] = cur;
                }
            }
        }
    }

    static void right() {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if(area[i][j] == 0) continue;
                dq.add(area[i][j]);
                area[i][j] = 0;
            }
            int idx = 0;
            while (!dq.isEmpty()) {
                int cur = dq.poll();
                if (area[i][idx] == cur) {
                    area[i][idx++] = cur << 1;
                }
                else if (area[i][idx] == 0) {
                    area[i][idx] = cur;
                }
                else {
                    area[i][++idx] = cur;
                }
            }
        }
    }

    static void down() {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                if(area[j][i] == 0) continue;
                dq.add(area[j][i]);
                area[j][i] = 0;
            }
            int idx = n - 1;
            while (!dq.isEmpty()) {
                int cur = dq.poll();
                if (area[idx][i] == cur) {
                    area[idx--][i] = cur << 1;
                }
                else if (area[idx][i] == 0) {
                    area[idx][i] = cur;
                }
                else {
                    area[--idx][i] = cur;
                }
            }
        }
    }

    static void up() {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if(area[j][i] == 0) continue;
                dq.add(area[j][i]);
                area[j][i] = 0;
            }
            int idx = 0;
            while (!dq.isEmpty()) {
                int cur = dq.poll();
                if (area[idx][i] == cur) {
                    area[idx++][i] = cur << 1;
                }
                else if (area[idx][i] == 0) {
                    area[idx][i] = cur;
                }
                else {
                    area[++idx][i] = cur;
                }
            }
        }
    }

    static void move(int dir) {
        if(dir == 0) left();
        else if(dir == 1) right();
        else if(dir == 2) up();
        else down();
    }

    static void recover(int[][] copy) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                area[i][j] = copy[i][j];
            }
        }
    }


    static int[][] copy() {
        int[][] tmp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                tmp[i][j] = area[i][j];
            }
        }
        return tmp;
    }

    static void getAnswer() {
        for (int[] subArea : area) {
            for (int block : subArea) {
                if(block > answer) answer = block;
            }
        }
    }

    static void solution() {
        dfs(0);
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
