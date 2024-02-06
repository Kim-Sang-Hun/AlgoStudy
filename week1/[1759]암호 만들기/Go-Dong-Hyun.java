import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int L, C;
    static String[] lst;
    static List<String> sub_lst = Arrays.asList("a", "e", "i", "o", "u");
    static List<String> ans = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        L = scanner.nextInt();
        C = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        lst = scanner.nextLine().split(" ");
        Arrays.sort(lst);

        sol(0, new ArrayList<>());

        for (String a : ans) {
            System.out.println(a);
        }
    }

    static void sol(int cnt, List<String> sub_ans) {
        if (sub_ans.size() == L) {
            int mo_cnt = 0; // 모음 카운트
            for (String ch : sub_ans) {
                if (sub_lst.contains(ch)) {
                    mo_cnt++;
                }
            }

            if (mo_cnt >= 1 && L - mo_cnt >= 2) {
                ans.add(String.join("", sub_ans));
                return;
            }
        }

        if (cnt >= C) {
            return;
        }

        sub_ans.add(lst[cnt]);
        sol(cnt + 1, new ArrayList<>(sub_ans));
        sub_ans.remove(sub_ans.size() - 1);
        sol(cnt + 1, new ArrayList<>(sub_ans));
    }
}
