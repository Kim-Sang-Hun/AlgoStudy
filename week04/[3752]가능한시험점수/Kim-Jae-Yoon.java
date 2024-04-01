/*
  2가지 방법으로 풀어봤으며
  DP, Set 두 가지 풀이방법을 공유합니다.
  구현 난이도는 DP가 더 높으나, 실행 시간에서 DP가 훨씬 유리함을 가져감을 알 수 있습니다.
*/

//1번 풀이(DP)
import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, totalSum;
    static int []number, dp;

    //끝에서부터 시작하여 가져갈 수 있는 값인지 확인 후 가져간다.
    static int solution() {
        dp[0] = 1;
        for(int i = 0;i < n; ++i){
            for (int j = totalSum; j >= number[i]; --j) {
                if(dp[j - number[i]] == 0) continue;
                dp[j] = dp[j - number[i]];
            }
        }
        return getAnswer();
    }

    static int getAnswer() {
        int answer = 0;
        for (int i = 0; i <= totalSum; ++i) {
            answer += dp[i];
        }
        return answer;
    }

    static void input() throws IOException {
        totalSum = 0;
        n = Integer.parseInt(br.readLine());
        number = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0;i < n; ++i){
            number[i] = Integer.parseInt(st.nextToken());
            totalSum += number[i];
        }
        dp = new int[totalSum + 1];
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for(int i = 1;i <= t; ++i) {
            input();
            sb.append("#").append(i).append(" ").append(solution()).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}

//2번 풀이(Set 자료구조 사용)
import java.io.*;
import java.util.*;
 
public class Solution {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n;
    static int []number;

    //number 배열을 돌며 그동안 중복 없이 쌓여있던 값들과의 합산 결과들을 다시 쌓아올리는 방식으로 구현됨
    static int solution() {
        HashSet<Integer> numbers = new HashSet<>();
        numbers.add(0);
        for(int i = 0;i < n; ++i){
            List<Integer> list = new ArrayList<>(numbers);
            for (int j : list) {
                numbers.add(number[i] + j);
            }
        }
        return numbers.size();
    }
 
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for(int i = 1;i <= t; ++i) {
            n = Integer.parseInt(br.readLine());
            number = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j < n; ++j){
                number[j] = Integer.parseInt(st.nextToken());
            }
            sb.append("#").append(i).append(" ").append(solution()).append("\n");
        }
        System.out.println(sb);
    }
}
