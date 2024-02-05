import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int t, n, answer;
    static String[] mbti, picks;
    static StringBuilder sb = new StringBuilder();

    static void solution() throws IOException {
        answer = 2147483647;
        n = Integer.parseInt(br.readLine());
        mbti = new String[n];
        picks = new String[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            mbti[i] = st.nextToken();
        }
        //아무리 밸런스가 잘 맞더라도 33부터는 3명인 mbti가 무조건 존재하므로 넘긴다.
        if(n > 32){
            answer = 0;
            return;
        }
        rec(0, 0);
    }

  //재귀를 통한 인원 탐색
    static void rec(int cnt, int idx){
        if(cnt == 3){
            answer = Math.min(answer, getBetweenDistance());
            return;
        }
        for(int i = idx; i < n; ++i){
            picks[cnt] = mbti[i];
            rec(cnt + 1, i + 1);
        }
    }

  //두 mbti 간 거리 계산
    static int getBetweenDistance(){
        int sum = 0;
        for(int i = 0;i < 4; ++i){
            sum += ((picks[0].charAt(i) != picks[1].charAt(i)) ? 1 : 0);
            sum += ((picks[0].charAt(i) != picks[2].charAt(i)) ? 1 : 0);
            sum += ((picks[1].charAt(i) != picks[2].charAt(i)) ? 1 : 0);
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        t = Integer.parseInt(br.readLine());
        for (int x = 1; x <= t; ++x) {
            solution();
            sb.append(answer).append('\n');
        }
        System.out.println(sb);
    }
}
