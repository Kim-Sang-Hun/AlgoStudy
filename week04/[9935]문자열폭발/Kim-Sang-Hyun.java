import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Kim-Sang-Hyun {

    private static String string;
    private static String stringBomb;
    private static Stack<Character> stack;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        string = br.readLine();
        stringBomb = br.readLine();

        System.out.println(solution(string, stringBomb));
    }

    public static String solution(String string, String stringBomb) {
        stack = new Stack<Character>();
        int length = stringBomb.length();
        char lastCharacter = stringBomb.charAt(stringBomb.length()-1);

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            stack.add(c);

            if(c == lastCharacter && checking(length)) {
                for (int j = 0; j < length; j++) {
                    stack.pop();
                }
            }
        }

        String result = stack.stream()
                .map(Objects::toString)
                .collect(Collectors.joining());

        if(result.isEmpty()) {
            return "FRULA";
        }

        return result;
    }

    public static boolean checking(int length) {

        if(stack.size() < length) return false;

        for(int j = 0; j < length; j++) {
            if(!stack.get(stack.size() - j - 1).equals(stringBomb.charAt(length - j - 1))) {
                return false;
            }
        }

        return true;
    }
}
