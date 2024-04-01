import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shin-Eun-Jin {
    static int N;
    static int[][] tree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        tree = new int[N + 1][2];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            int parent = input.charAt(0) - 'A' + 1;
            int leftChild = input.charAt(2) == '.' ? 0 : input.charAt(2) - 'A' + 1;
            int rightChild = input.charAt(4) == '.' ? 0 : input.charAt(4) - 'A' + 1;

            tree[parent][0] = leftChild;
            tree[parent][1] = rightChild;
        }

        preorder(1);
        sb.append("\n");
        inorder(1);
        sb.append("\n");
        postorder(1);
        sb.append("\n");
        System.out.println(sb);
    }

    static void preorder(int parent) {
        sb.append((char) (parent - 1 + 'A'));

        for (int child : tree[parent]) {
            if (child == 0) continue;

            preorder(child);
        }
    }

    static void inorder(int parent) {
        if (parent == 0) return;

        inorder(tree[parent][0]);
        sb.append((char) (parent - 1 + 'A'));
        inorder(tree[parent][1]);
    }

    static void postorder(int parent) {
        if (parent == 0) return;

        postorder(tree[parent][0]);
        postorder(tree[parent][1]);
        sb.append((char) (parent - 1 + 'A'));
    }
}
