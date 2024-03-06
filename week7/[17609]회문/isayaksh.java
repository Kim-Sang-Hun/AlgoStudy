import java.io.*;

public class isayaksh {

    private static char[] word;
    private static int result;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {

            word = br.readLine().toCharArray();

            result = 2;

            checkPalindrome(0, word.length-1, false);

            sb.append(result + "\n");

        }

        System.out.println(sb);

    }

    private static void checkPalindrome(int start, int end, boolean delete) {

        if(result != 2) return;

        if(start >= end) {
            result = delete ? 1 : 0;
            return;
        }

        if(word[start] != word[end]) {    
            if(!delete) {
                if(word[start+1] == word[end]) checkPalindrome(start+1, end, true);
                if(word[start] == word[end-1]) checkPalindrome(start, end-1, true);
            }
        }

        if(word[start] == word[end]) checkPalindrome(start+1, end-1, delete);

    }

}
