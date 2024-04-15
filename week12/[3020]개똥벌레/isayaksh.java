import java.util.*;
import java.io.*;

public class Main {

    private static int N, H;

    private static int fence, count, answer;

    private static Map<Integer, Integer> stalagmites = new HashMap<Integer, Integer>(); // 석순
    private static Map<Integer, Integer> stalactites = new HashMap<Integer, Integer>(); // 종유석 
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        for(int n = 0; n < N; n++) {
            int height = Integer.parseInt(br.readLine());

            if(n%2 == 0) {
                if(!stalagmites.containsKey(height-1)) stalagmites.put(height-1, 0);
                stalagmites.put(height-1, stalagmites.get(height-1)+1);
            }

            if(n%2 == 1) {
                if(!stalactites.containsKey(H - height)) stalactites.put(H - height, 0);
                stalactites.put(H - height, stalactites.get(H - height)+1);
            }

        }


        fence = N/2 + (stalactites.containsKey(0) ? stalactites.get(0) : 0);

        answer = fence;
        count = 1;
        

        for(int h = 1; h < H; h++) {

            if(stalagmites.containsKey(h-1)) fence -= stalagmites.get(h-1);

            if(stalactites.containsKey(h)) fence += stalactites.get(h);


            if(fence == answer) {
                count++;
                continue;
            }

            if(fence < answer) {
                answer = fence;
                count = 1;
            }

        }

        System.out.println(answer + " " + count);
        
    }
}
