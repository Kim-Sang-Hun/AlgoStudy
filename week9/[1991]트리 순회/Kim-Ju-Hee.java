package march4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN1991_트리순회_김주희 {
	static Node[] tree;
	
	static class Node{
		int left, right;
		char value;
		
		public Node(int left, int right, char value) {
			this.left = left;
			this.right = right;
			this.value = value;
		}
	}
	
	static void preorderTraversal(Node root) {
        if (root == null) return;
        System.out.print(root.value); // 노드 출력
        preorderTraversal(tree[root.left]);     // 왼쪽 서브트리 순회
        preorderTraversal(tree[root.right]);    // 오른쪽 서브트리 순회
    }
	
	static void inorderTraversal(Node root) {
        if (root == null) return;
        inorderTraversal(tree[root.left]);      // 왼쪽 서브트리 순회
        System.out.print(root.value); // 노드 출력
        inorderTraversal(tree[root.right]);     // 오른쪽 서브트리 순회
    }
	
	static void postorderTraversal(Node root) {
        if (root == null) return;
        postorderTraversal(tree[root.left]);    // 왼쪽 서브트리 순회
        postorderTraversal(tree[root.right]);   // 오른쪽 서브트리 순회
        System.out.print(root.value); // 노드 출력
    }

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		tree = new Node[N+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char value = st.nextToken().charAt(0);
			int parent = value - 'A';
			
			int left = st.nextToken().charAt(0) - 'A';
			if(left == -19) left = N; // null됨
			
			int right = st.nextToken().charAt(0) - 'A';
			if(right == -19) right = N;
			
			tree[parent] = new Node(left,right,value);
		}
		
		//전위순회
        preorderTraversal(tree[0]);
        System.out.println();
        //중위순회
        inorderTraversal(tree[0]);
        System.out.println();
        //후위순회
        postorderTraversal(tree[0]);
        System.out.println();

	}

}
