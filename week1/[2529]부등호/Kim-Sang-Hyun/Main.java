package week0.Baekjoon.G3;

import java.util.*;

public class Main {

    public static int k;
    public static String[] inequalityList;
    public static boolean[] used;
    public static List<String> answerList = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        k = Integer.parseInt(scanner.nextLine());
        inequalityList = scanner.nextLine().split(" ");

        used = new boolean[10];

        dfs(0, "");

        System.out.println(answerList.get(answerList.size()-1));
        System.out.println(answerList.get(0));

    }

    public static void dfs(int index, String numbers) {

        if(index == k+1) {
            answerList.add(numbers);
            return;
        }
        for (int i = 0; i < 10; i++) {
            if(index == 0) {
                used[i] = true;
                dfs(index+1, numbers + i);
                used[i] = false;
            } else {
                if(used[i] == false && isPossible(Character.getNumericValue(numbers.charAt(index-1)), i, inequalityList[index-1] )) {
                    used[i] = true;
                    dfs(index+1, numbers + i);
                    used[i] = false;
                }
            }
        }
    }

    public static boolean isPossible(int n1, int n2, String inequality) {
        if(inequality.equals("<")) {
            return n1 < n2;
        }
        if(inequality.equals(">")) {
            return n1 > n2;
        }
        return true;
    }
}