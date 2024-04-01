import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN17779_게리맨더링2 {

    static int N, total;
    static int[] region = new int[6];
    static int[][] map;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                gerrymendering(i, j);
            }
        }
        System.out.println(minDiff);
    }

    private static void gerrymendering(int y, int x) {
        Point top, left, right, bottom;

        // 각 좌표 y, x 에 대해
        // 가능한 좌표 left, right, bottom을 구해보고 각 선거구의 사람 수를 계산해준다.
        for (int i = 0; i < x; i++) {
            for (int j = x + 1; j < N; j++) {
                top = new Point(y, x);
                left = new Point(y + (x - i), i);
                right = new Point(y + (j - x), j);
                bottom = new Point(y + (j - i), j - (x - i));
                if (bottom.y >= N) continue;
                region[1] = region[2] = region[3] = region[4] = region[5] = 0;
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                // top, left, right, bottom의 영역 내의 값 더해주기
                for (int k = top.y; k < left.y; k++) {
                    for (int l = 0; l < top.x - (k - top.y); l++) {
                        region[1] += map[k][l];
                    }
                }
                for (int k = top.y; k <= right.y; k++) {
                    for (int l = top.x + 1 + (k - top.y); l < N; l++) {
                        region[2] += map[k][l];
                    }
                }
                for (int k = left.y; k <= bottom.y; k++) {
                    for (int l = 0; l < left.x + (k - left.y); l++) {
                        region[3] += map[k][l];
                    }
                }
                for (int k = right.y + 1; k <= bottom.y; k++) {
                    for (int l = right.x - (k - right.y - 1); l < N; l++) {
                        region[4] += map[k][l];
                    }
                }
                // top, left, right, bottom의 영역 밖의 값 더해주기
                for (int k = 0; k < top.y; k++) {
                    for (int l = 0; l <= top.x; l++) {
                        region[1] += map[k][l];
                    }
                    for (int l = top.x + 1; l < N; l++) {
                        region[2] += map[k][l];
                    }
                }
                for (int k = bottom.y + 1; k < N; k++) {
                    for (int l = 0; l < bottom.x; l++) {
                        region[3] += map[k][l];
                    }
                    for (int l = bottom.x; l < N; l++) {
                        region[4] += map[k][l];
                    }
                }
                if (region[1] == 0 || region[2] == 0 || region[3] == 0 || region[4] == 0) continue;
                region[5] = total - region[1] - region[2] - region[3] - region[4];
                for (int k = 1; k < region.length; k++) {
                    if (max < region[k]) max = region[k];
                    if (min > region[k]) min = region[k];
                }
                minDiff = Math.min(minDiff, max - min);
            }
        }
    }

    private static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
