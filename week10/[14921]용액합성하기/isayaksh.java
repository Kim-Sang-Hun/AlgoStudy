import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int[] solution = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();

        Arrays.sort(solution);

        System.out.println(mix(N, solution));

    }

    private static long mix(int N, int[] solution) {
        long answer = Long.MAX_VALUE;

        int start = 0;
        int end = N-1;
        int res;

        while (start < end) {
            res = solution[start] + solution[end];
            if(res <= 0) start++;
            if(res > 0) end--;
            if(Math.abs(answer) > Math.abs(res)) answer = res;
        }

        return answer;

    }

}
