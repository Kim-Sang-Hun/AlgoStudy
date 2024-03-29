import java.util.ArrayList;

public class Shin-Eun-Jin {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args){

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0; i < 11*11; i++) {
            list.add(new ArrayList<>());
        }
        int cnt = 0;
        int curIdx = 60;
        for (char dir : dirs.toCharArray()) {
            int row = curIdx / 11;
            int col = curIdx % 11;

            if (dir == 'U') {
                row += dr[0];
                col += dc[0];
                if(!checkRange(row, col)) continue;

            } else if (dir == 'D') {
                row += dr[1];
                col += dc[1];
                if(!checkRange(row, col)) continue;

            } else if (dir == 'L') {
                row += dr[2];
                col += dc[2];
                if(!checkRange(row, col)) continue;

            } else if (dir == 'R') {
                row += dr[3];
                col += dc[3];
                if(!checkRange(row, col)) continue;

            }

            int nextIdx = row * 11 + col;
            if(!list.get(curIdx).contains(nextIdx)) {
                list.get(curIdx).add(nextIdx);
                list.get(nextIdx).add(curIdx);
                cnt++;
            }
            curIdx = nextIdx;
        }

        System.out.println(cnt);
    }

    static boolean checkRange(int row, int col) {
        if(row < 0 || row >= 11 || col < 0 || col >= 11) {
            return false;
        }

        return true;
    }

}
