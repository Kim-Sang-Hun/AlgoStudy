/*
스택으로 풀면 조건이 까다로울 것 같다.
그냥 한번에 스트링으로 받고 앞에서, 그리고 뒤에 포인터를 하나씩 두고 비교하면서 가다가
다른 곳이 나오면 앞 포인터를 한칸 뒤로 하거나
뒤 포인터를 한칸 앞으로 한 다음 회문이 되는지 확인해본다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JUN17609_회문 {

    static boolean palindrome, pseudo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int left = 0;
            int right = str.length() - 1;
            palindrome = true;
            pseudo = false;
            checkPalindrome(str, left, right);
            if (palindrome) {
                sb.append(0);
            } else if (pseudo) {
                sb.append(1);
            } else sb.append(2);
            sb.append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    private static void checkPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                palindrome = false;
                // 왼쪽 포인터를 오른쪽으로 한 칸 옮기고 체크
                int l1 = left + 1;
                int r1 = right;
                boolean b1 = true;
                while (l1 < r1) {
                    if (str.charAt(l1) != str.charAt(r1)) {
                        b1 = false;
                        break;
                    }
                    ++l1;
                    --r1;
                }
                // 오른쪽 포인터를 왼쪽으로 한 칸 옮기고 체크
                int l2 = left;
                int r2 = right - 1;
                boolean b2 = true;
                while (l2 < r2) {
                    if (str.charAt(l2) != str.charAt(r2)) {
                        b2 = false;
                        break;
                    }
                    ++l2;
                    --r2;
                }
                if (b1 || b2) {
                    pseudo = true;
                }
                break;
            }
            left++;
            right--;
        }
    }
}
