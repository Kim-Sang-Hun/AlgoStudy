import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
    static boolean[] isVisit;
    static String[] command;
    static int originNum, resultNum;
    static StringBuilder ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ans = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            originNum = Integer.parseInt(st.nextToken());
            resultNum = Integer.parseInt(st.nextToken());

            isVisit = new boolean[10000];
            command = new String[10000];
            Arrays.fill(command, "");

            bfs();
        }
        System.out.println(ans);
    }
    
    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(originNum);
        isVisit[originNum] = true;

        while(!queue.isEmpty()) {
            int curNum = queue.poll();

            if(curNum == resultNum) {
                ans.append(command[curNum]).append("\n");
                return;
            }

            int D = (2 * curNum) % 10000;
            int S = (curNum == 0) ? 9999 : curNum-1;
            int L = (curNum % 1000) * 10 + curNum/1000;
            int R = (curNum % 10) * 1000 + curNum/10;

            if(!isVisit[D]){
                queue.offer(D);
                isVisit[D] = true;
                command[D] = command[curNum] + "D";
            }

            if(!isVisit[S]){
                queue.offer(S);
                isVisit[S] = true;
                command[S] = command[curNum] + "S";
            }

            if(!isVisit[L]){
                queue.offer(L);
                isVisit[L] = true;
                command[L] = command[curNum] + "L";
            }

            if(!isVisit[R]){
                queue.offer(R);
                isVisit[R] = true;
                command[R] = command[curNum] + "R";
            }
        }
    }
}
