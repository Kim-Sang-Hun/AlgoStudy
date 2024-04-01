import java.io.*;
import java.util.*;

/*
 * 제목
 * <트리 순회>
 * 링크
 * https://www.acmicpc.net/problem/1991
 * 요약
 * 트리를 전위, 중위, 후위 순회하여 출력
 * 풀이
 * 연결리스트로 트리 구현
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Node {
		Node parent;
		Node left = null;
		Node right = null;
		char value;

		public Node(Node parent, char value) {
			this.parent = parent;
			this.value = value;
		}
	}

	static int N; // 노드 개수
	static Node root;

	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		char parent = st.nextToken().charAt(0);
		char left = st.nextToken().charAt(0);
		char right = st.nextToken().charAt(0);
		root = new Node(null, parent);
		root.left = new Node(root, left);
		root.right = new Node(root, right);

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			parent = st.nextToken().charAt(0);
			Node p = findNode(parent, root);
			left = st.nextToken().charAt(0);
			right = st.nextToken().charAt(0);
			p.left = new Node(p, left);
			p.right = new Node(p, right);
		}
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static Node findNode(char val, Node cur) {
		if (cur == null || cur.value == '.') {
			return null;
		} else if (cur.value == val) {
			return cur;
		} else {
			Node left = findNode(val, cur.left);
			if (left != null)
				return left;
		}
		return findNode(val, cur.right);
	}

	private static void solution() throws IOException {
		preOrder(root);
		sb.append("\n");
		inOrder(root);
		sb.append("\n");
		postOrder(root);
	}

	// P - L - R
	private static void preOrder(Node cur) throws IOException {
		if (cur.value == '.')
			return;
		sb.append(cur.value);
		preOrder(cur.left);
		preOrder(cur.right);
	}

	// L - P - R
	private static void inOrder(Node cur) throws IOException {
		if (cur.value == '.')
			return;
		inOrder(cur.left);
		sb.append(cur.value);
		inOrder(cur.right);
	}

	// L - R - P
	private static void postOrder(Node cur) throws IOException {
		if (cur.value == '.')
			return;
		postOrder(cur.left);
		postOrder(cur.right);
		sb.append(cur.value);
	}
}
