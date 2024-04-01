import java.io.*;
import java.util.*;

public class isayaksh {

    private static Map<Character, Node> edge = new HashMap<Character, Node>();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Character head, left, right;
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            head = st.nextToken().charAt(0);
            left = st.nextToken().charAt(0);
            right = st.nextToken().charAt(0);

            edge.put(head, new Node(left, right));

        }

        visited = new boolean['Z'-'A'+1];

        preOrder('A');
        System.out.println();

        inOrder('A');
        System.out.println();

        postOrder('A');
        System.out.println();

    }
    
    private static void preOrder(Character head) {

        Node node = edge.get(head);
        visited[head-'A'] = true;

        System.out.print(head);
        if(node.left != '.') preOrder(node.left);
        if(node.right != '.') preOrder(node.right);

    }

    private static void inOrder(Character head) {

        Node node = edge.get(head);
        visited[head-'A'] = true;
        
        if(node.left != '.') inOrder(node.left);
        System.out.print(head);
        if(node.right != '.') inOrder(node.right);
    }

    private static void postOrder(Character head) {

        Node node = edge.get(head);
        visited[head-'A'] = true;
        
        if(node.left != '.') postOrder(node.left);
        if(node.right != '.') postOrder(node.right);
        System.out.print(head);

    }

    static class Node {

        char left, right;

        public Node(char left, char right) {
            this.left = left;
            this.right = right;
        }

    }


}
