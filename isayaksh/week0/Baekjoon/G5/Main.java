package week0.Baekjoon.G5;

import java.util.*;

public class Main {

    public static String[] alphabet;
    public static boolean[] used;
    public static char[] result;
    public static int L;
    public static int C;

    public static void main(String[] args) {
        // input
        Scanner scanner = new Scanner(System.in);
        int[] LC = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        L = LC[0];
        C = LC[1];
        alphabet = scanner.nextLine().split(" ");

        // preprocessing
        used = new boolean[C];
        Arrays.sort(alphabet);

        // solution
        combination(0, C, L);

    }

    public static void combination(int start, int n, int r) {
        if(r == 0) {
            int vowelsCount = 0;
            int consonantsCount = 0;

            for(int i = 0; i < C; i++) {
                if(used[i] && isVowels(alphabet[i])) {
                    vowelsCount++;
                }
                if(used[i] && !isVowels(alphabet[i])) {
                    consonantsCount++;
                }
            }
            if(consonantsCount >= 2 && vowelsCount >= 1) {
                for(int i = 0; i < C; i++) {
                    if(used[i]) {
                        System.out.print(alphabet[i]);
                    }
                }
                System.out.println();
            }
            return;
        }

        for(int i = start; i < n; i++) {
            used[i] = true;
            combination(i+1, n, r-1);
            used[i] = false;
        }
    }

    public static boolean isVowels(String alphabet) {
        return alphabet.equals("a") || alphabet.equals("e") || alphabet.equals("i") || alphabet.equals("o") || alphabet.equals("u");
    }
}
