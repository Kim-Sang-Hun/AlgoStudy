import java.util.*;
import java.io.*;

public class Main {

    private static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    private static int N, M, K;
    private static int[][] land, nourishment;
    private static List<Tree> trees = new ArrayList<Tree>();
    private static List<Tree> deads = new ArrayList<Tree>();

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        land = new int[N][N];
        for(int n = 0; n < N; n++) {
            Arrays.fill(land[n], 5);
        }

        nourishment = new int[N][N];
        for(int n = 0; n < N; n++) {
            nourishment[n] = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        }

        int x, y, z;
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;
            z = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, z));
        }

        for(int k = 0; k < K; k++) {
            spring();
            summer();
            autum();
            winter();
        }

        System.out.println(trees.size());

    }

    private static void spring() {

        Collections.sort(trees);

        List<Tree> tmp = new ArrayList<Tree>();

        for(Tree t : trees) {
            if(land[t.y][t.x] >= t.z) {
                land[t.y][t.x] -= t.z;
                t.z++;
                tmp.add(t);
            } else {
                deads.add(t);
            }
        }

        trees = tmp;
    }

    private static void summer() {
        for(Tree t : deads) {
            land[t.y][t.x] += t.z/2;
        }
        deads.clear();
    }

    private static void autum() {
        int nx, ny;

        List<Tree> adds = new ArrayList<Tree>();

        for(Tree t : trees) {

            if(t.z%5 != 0) continue;

            for(int i = 0; i < 8; i++) {
                nx = t.x + dx[i];
                ny = t.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                
                adds.add(new Tree(nx, ny, 1));
            }
        }

        for(Tree a : adds) {
            trees.add(a);
        }

    }

    private static void winter() {
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                land[y][x] += nourishment[y][x];
            }
        }
    }

    static class Tree implements Comparable<Tree> {
        int x, y, z;

        Tree(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(Tree other) {
            return this.z - other.z;
        }

    }
    
}

