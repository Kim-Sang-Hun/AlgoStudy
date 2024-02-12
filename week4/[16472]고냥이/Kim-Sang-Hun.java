import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// cat소리를 하나하나 보면서 charCount배열에 넣어주고 종류와 길이를 세어주면서 가는 풀이 방식이다.(Sliding Window)
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String catSound = br.readLine();
    // 알파벳 종류를 세기 위한 alphaCnt, 길이를 세기 위한 len, 현재 보고 있는 해석 가능한 문자열 안에 들어있는 알파벳의 개수를 세기 위한 charCount배열을 둔다.
    int[] charCount = new int[26];
    int alphaCnt = 0;
    int len = 0;
    int maxLen = 0;

    for (int i = 0; i < catSound.length(); i++) {
      int charIdx = catSound.charAt(i) - 'a';
      // 만약 charCount의 값이 0이라면 새로운 알파벳이라는 의미이므로 alphaCnt를 1 올려준다.
      if (charCount[charIdx]++ == 0) {
        ++alphaCnt;
      }
      ++len;
      // 만약 alphaCnt가 N보다 크다면 해석할 수 있는 종류를 넘어선 것이므로 기존에 추가했던 알파벳부터 제거해준다.
      while (alphaCnt > N) {
        int removeIdx = catSound.charAt(i - len + 1) - 'a';
        --charCount[removeIdx];
        --len;
        // 만약 charCount의 값이 0이라면 알파벳 종류가 하나 줄어들었다는 의미이므로 alphaCnt를 1 줄여준다.
        if (charCount[removeIdx] == 0) {
          --alphaCnt;
        }
      }
      maxLen = Math.max(maxLen, len);
    }
    System.out.println(maxLen);
  }
}
