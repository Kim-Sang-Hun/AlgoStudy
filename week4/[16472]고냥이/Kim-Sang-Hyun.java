import java.io.*;

public class Kim-Sang-Hyun {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String string = br.readLine();

        System.out.println(solution(N, string));

    }

    public static int solution(int N, String string) {
        int[] alphabet = new int[26];
        int answer = 0;
        int count = 0;
        int start = 0;


        for(int end = 0; end < string.length(); end++) {
            if(alphabet[string.charAt(end) - 'a']++ == 0) {
                count++;
            }
            while (N < count && start < end) {
                if (--alphabet[string.charAt(start++) - 'a'] == 0){
                    count--;
                }
            }
            answer = Math.max(answer, end - start + 1);
        }

        return answer;
    }
}
