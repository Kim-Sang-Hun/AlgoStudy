import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ16472_고냥이_최민혁 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, answer = 0;
    static int[] alphabet = new int[26];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        
        // 0번 index부터 two pointer로 출발, count는 센 알파벳 종류의 개수
        int start = 0, end = 0, count = 1;
        alphabet[str.charAt(0) - 'a']++;

        while (end < str.length() - 1) {
            end++;
            
            if (alphabet[str.charAt(end) - 'a']++ == 0)
            	count++; 

            // count가 N보다 크면 줄어들 때까지 start를 앞으로 당김
            while (count > N && start < end) { 
                if (--alphabet[str.charAt(start++) - 'a'] == 0)
                	count--;
            }

            answer = Math.max(answer, end - start + 1);

        }
        
        System.out.print(answer);
    }
}
