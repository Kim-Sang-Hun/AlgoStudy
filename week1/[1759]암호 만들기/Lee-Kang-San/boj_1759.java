package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1759 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int L; // 암호 길이
    static int C; // 문자 개수
    static List<Character> letters = new ArrayList<>(); // 문자들
    static boolean[] isUsed;
    static char[] password;
    public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken()); 
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			letters.add(st.nextToken().charAt(0));
		}
		Collections.sort(letters);

        // 풀이, 출력
        isUsed = new boolean[letters.size()];
        password = new char[L];
        bt(0);

		// 종료
		bw.flush();
		bw.close(); 
    }

    static void bt(int depth) throws IOException{
        if(depth == L) {
            if(isSorted(password) && hasVowel(password, 1, L-2)) {
                bw.write(password);
                bw.write("\n");
            }
            return;
        }
        for(int i=depth; i<C; i++) {
            if(isUsed[i]==false) {
                password[depth] = letters.get(i);
                isUsed[i]=true;
                bt(depth+1);
                isUsed[i]=false;   
            }
        }
    }
    
    static boolean isSorted(char[] password) {
        for(int i=0; i<L; i++) {
            for(int j=i+1; j<L; j++) {
                if(password[j]<password[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean hasVowel(char[] password, int min, int max) {
        char[] v = new char[]{'a', 'e', 'i', 'o', 'u'};
        int cnt=0;
        for(int i=0; i<L; i++) {
            for(int j=0; j<5; j++) {
                if(password[i]==v[j])
                    cnt++;
            }            
        }
        if(1 <= cnt && cnt <= max)
            return true;
        return false;
    }
}
