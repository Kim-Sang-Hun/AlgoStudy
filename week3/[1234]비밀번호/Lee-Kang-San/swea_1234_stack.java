package SWEA.D3;

import java.io.*;
import java.util.*;

public class swea_1234_stack {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // 입력
        for(int i=1; i<=10; ++i) {                  // TC = 10
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();           // 길이
            String pw = st.nextToken();             // 암호
        // 풀이
            solution(pw, i);            
        }
        // 출력
        bw.write(sb.toString());
        bw.flush();
    }

    private static void solution(String s, int TC) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for(int i=0; i<length; ++i) {
            try {
                if(stack.peek()==s.charAt(i)) stack.pop();
                else stack.push(s.charAt(i));
                
            } catch (EmptyStackException e) {
                stack.push(s.charAt(i));
            }
        }
        sb.append("#"+TC+" ");
        StringBuilder temp = new StringBuilder();
        while(!stack.isEmpty()) {
            temp.append(stack.pop());
        }
        temp.reverse();
        sb.append(temp);
        sb.append("\n");
    }
}