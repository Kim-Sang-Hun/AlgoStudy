import java.io.*;
import java.util.*;

// DP 풀이 시도 중 실패 ㅠ

public class isayaksh {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] positiveDP = new int[40000001]; // 양수
        int[] negativeDP = new int[40000001]; // 음수

        Set<Integer> numbers = new HashSet<Integer>();
        Set<Integer> tmps = new HashSet<Integer>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {  

            int num = Integer.parseInt(st.nextToken());

            for(Integer number : numbers) {
                int newNumber = number + num;

                if(0 <= newNumber) positiveDP[newNumber]++; // 양수 일 때
                if(newNumber < 0) negativeDP[Math.abs(newNumber)]++;  // 음수 일 때

                tmps.add(newNumber);
            }

            for(Integer number: tmps) {
                numbers.add(number);
            }
            
            if(0 <= num) positiveDP[num]++; // 양수 일 때
            if(num < 0) negativeDP[Math.abs(num)]++;  // 음수 일 때
            numbers.add(num);

        }

        if(0 <= S) System.out.println(positiveDP[S]);
        if(S < 0) System.out.println(negativeDP[Math.abs(S)]);        
    }
}
