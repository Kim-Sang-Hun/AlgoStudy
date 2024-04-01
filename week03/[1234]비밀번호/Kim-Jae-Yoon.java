import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static String answer;
    static int n;

  //단계별로 나아가며 2글자씩 없엔다. 정말 볼품없지만 간단한 로직
    static void solution() {
        for(int i = 0;i < n - 1; ++i) {
            int st = i, en = i + 1;
            while(i < n - 1 && i >= 0) {
                if(answer.charAt(i) != answer.charAt(i + 1)) break;
                n -= 2;
                answer = answer.substring(0, i) + answer.substring(i + 2);
                --i;
            }
        }
    }
     
    public static void main(String[] args) throws IOException{
        for(int t = 1;t <= 10; ++t) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            answer = st.nextToken();
            solution();
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
