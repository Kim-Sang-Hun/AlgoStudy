import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String str;

    static int solution(int st, int en, int cnt) throws IOException{
        if(cnt > 1) return 2;
        while(st < en){
            //왼쪽으로 가야할지, 오른쪽으로 가야할지 정해지지 않음
            //둘 중 근사 팰린드롬이 있으면 1을 반환할테니 min으로 받아들인다.
            if(str.charAt(st) != str.charAt(en)){
                return Math.min(solution(st + 1, en, cnt + 1),
                        solution(st, en - 1, cnt + 1));
            }
            //같으면 계속 할 일 하면 된다.
            //양측에서 좁혀가며 팰린드롬 여부를 확인
            else{
                ++st;
                --en;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; ++i) {
            str = br.readLine();
            sb.append(solution(0, str.length() - 1, 0)).append('\n');
        }
        System.out.println(sb);
    }
}
