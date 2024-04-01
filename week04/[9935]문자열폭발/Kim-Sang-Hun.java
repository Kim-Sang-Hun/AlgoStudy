import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// String.replaceAll() 메서드를 사용하려 했으나 메모리 초과가 났다.
// 입력받는 String의 길이가 백만까지 올라가기 때문인 것 같다(새로운 String을 만드는 방식이기 때문)
// 따라서 스택을 사용하여 문자를 하나씩 넣고 폭발하는 문자열을 확인하면 바로 없애준다.
public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    String bomb = br.readLine();
    int len = bomb.length();
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < str.length(); i++) {
      stack.add(str.charAt(i));

//    IndexOutOfRange 오류를 피하기 위해 stack의 사이즈가 bomb의 크기보다 클 때만 확인해준다.
      if (stack.size() >= len) {
        for (int j = 0; j < len; j++) {
//        스택의 맨 끝에서 bomb의 크기만큼을 확인해준다.
          if (stack.get(stack.size() - len + j) != bomb.charAt(j)) {
            break;
          }
//        만약 j가 len - 1이고 위의 조건문을 통과했다면 stack의 마지막에 bomb가 있다는 말이므로 bomb의 크기만큼 pop()해준다
          if (j == len - 1) {
            for (int k = 0; k < len; k++) {
              stack.pop();
            }
          }
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    for (char c : stack) {
      sb.append(c);
    }

    System.out.println(stack.isEmpty() ? "FRULA" : sb);
  }
}
