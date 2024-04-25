import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ16922_로마숫자만들기 {
    static int N;
    static int[] nums = {1, 5, 10, 50};
    static HashSet<Integer> words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        words = new HashSet<>();

        createWord(0,0, 0);

        System.out.println(words.contains(0) ? words.size() - 1 : words.size());
    }

    static void createWord(int start, int cnt, int word) {
        if (cnt == N) {
            words.add(word);
            return;
        }

        for (int i = start; i < 4; i++) {
            createWord(i,cnt + 1, word + nums[i]);
        }

    }
}
