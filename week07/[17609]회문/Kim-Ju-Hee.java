import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JUN17609_회문_김주희 {
	static String target;
	static int cnt;
	
	private static void checkPalindrome() {
		int left = 0;
		int right = target.length()-1;
		cnt = 0;
		
		while(left < right) {
			if(target.charAt(left) == target.charAt(right)) { 
				left++;
				right--;
			}else { // 일치 안 하면 유사 회문인지 한번 더 탐색
				if(isPseudoPalindrome(left+1,right) || isPseudoPalindrome(left,right-1)){
					cnt = 1;
					break;
				}else {
					cnt = 2;
					break;
				}
			}
		}
	}
	
	private static boolean isPseudoPalindrome(int left, int right) {
        while (left < right) {
            if (target.charAt(left) != target.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			target = br.readLine();
			checkPalindrome();
			System.out.println(cnt);
		}
	}
}
