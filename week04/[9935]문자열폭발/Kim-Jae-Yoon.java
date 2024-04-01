import java.io.*;
import java.util.Stack;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String a, bomb;

    static void input() throws IOException {
        a = br.readLine();
        bomb = br.readLine();
    }

    static void solution() {
        Stack<Character> s = new Stack<>();
        int len = bomb.length();
        for (int i = 0; i < a.length(); ++i) {
            s.add(a.charAt(i));
            //폭발물 길이보다 짧을 경우는 당연히 스킵
            if(s.size() < len) continue;
            //폭발 가능 여부 파악후 폭발 실행
            if(!canExplosion(s, len)) continue;
            doExplosion(s, len);
        }
        if(s.isEmpty()) {
            System.out.println("FRULA");
            return;
        }
        for (char nxt : s) {
            sb.append(nxt);
        }
        System.out.println(sb);
    }

    //폭발 조건이 갖춰질 경우 해당 메서드로 폭발(삭제) 연산을 수행한다.
    static void doExplosion(Stack<Character> s, int len) {
        for (int i = 0; i < len; ++i) {
            s.pop();
        }
    }

    //폭발 가능한 문자열 구간인지 확인한다.
    static boolean canExplosion(Stack<Character> s, int len) {
        int size = s.size();
        for (int i = 0; i < len; ++i) {
            if(s.get(size - len + i).equals(bomb.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
