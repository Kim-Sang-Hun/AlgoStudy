import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
메모리 15000kb 시간 156ms
중복순열 하듯이 하면 되는데
비내림차순이어야 하므로  
if (idx > 0 && selected[idx - 1] > arr[i]) {
      continue;
    }
끼워넣어서 전꺼보다는 큰거 고르도록

*/
public class Main {

    static StringBuilder sb;
    static int n, m;
    static int[] arr, selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<String> input = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            input.add(st.nextToken());
        }
        arr = input.stream().mapToInt(Integer::parseInt).distinct().sorted().toArray();
        selected = new int[m];
        sb = new StringBuilder();
        select( 0);
        System.out.println(sb.toString().trim());
        br.close();
    }

    static void select(int idx) {
        if (idx == m) {
            for (int i = 0; i < m; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append(System.lineSeparator());
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (idx > 0 && selected[idx - 1] > arr[i]) {
                continue;
            }
            selected[idx] = arr[i];
            select(idx + 1);
        }
    }
}
