import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int n;
    static String[] arr;
    static boolean[] visited;
    static List<String> ans = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        arr = new String[n];
        visited = new boolean[10];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }
//        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < 10; i++) {
            visited[i] = true;
            dfs(i, 0, i+"");
        }

        System.out.println(ans.get(ans.size()-1));
        System.out.println(ans.get(0));

    }

    private static void dfs(int num, int idx, String str) {
        if(idx == n) {
            ans.add(str);
        } else{
            for (int i = 0; i < 10 ; i++) {
                if (!visited[i]) {
                    if (arr[idx].equals("<")){
                        if (num >= i) {
                            continue;
                        }
                    } else {
                        if (num <= i) {
                            continue;
                        }
                    }
                    visited[i] = true;
                    dfs(i,idx+1,str+i);
                }
            }
        }
        visited[num] = false;
    }
}
