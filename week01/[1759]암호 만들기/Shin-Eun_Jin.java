import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int L, C;
    static char[] characters;
    static StringBuilder result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // L과 C 입력
        String[] input = br.readLine().split(" ");
        L = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        // 문자 입력
        characters = new char[C];
        visited = new boolean[C];
        input = br.readLine().split(" ");
        for (int i = 0; i < C; i++) {
            characters[i] = input[i].charAt(0);
        }

        // 사전식으로 출력하기 위해 정렬
        Arrays.sort(characters);

        // 결과 문자열 초기화
        result = new StringBuilder();

        // 가능성 있는 암호 찾기
        backtracking(0, 0, 0, 0);

        // 결과 출력
        System.out.println(result.toString());
    }

    // 가능성 있는 암호 찾기
    static void backtracking(int index, int selected, int vowels, int consonants) {
        // 기저 조건: L 길이의 암호가 완성되었을 때
        if (selected == L) {
            // 최소 한 개의 모음과 최소 두 개의 자음이 조건에 맞는지 확인
            if (vowels >= 1 && consonants >= 2) {
                // 결과 문자열에 선택된 문자 추가
                for (int i = 0; i < C; i++) {
                    if (visited[i]) {
                        result.append(characters[i]);
                    }
                }
                result.append('\n');
            }
            return;
        }

        // 유효한 범위 내에서 수행
        for (int i = index; i < C; i++) {
            if (!visited[i]) {
                visited[i] = true;
                // 현재 문자가 모음인지 자음인지 확인
                if (isVowel(characters[i])) {
                    backtracking(i + 1, selected + 1, vowels + 1, consonants);
                } else {
                    backtracking(i + 1, selected + 1, vowels, consonants + 1);
                }
                visited[i] = false;
            }
        }
    }

    // 주어진 문자가 모음인지 확인하는 함수
    static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
