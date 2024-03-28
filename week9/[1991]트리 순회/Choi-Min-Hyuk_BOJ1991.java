import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 딱히 설명 필요 없을 듯
public class BOJ1991_트리의순회_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static String lineSeparator = System.lineSeparator();
	static int N;
	static char arr[][];
	static Node nodeArr[];
	
	static class Node {
		Node leftChild;
		Node rightChild;
		char data;
		
		Node(char data) {
			this.data = data;
		}
	}
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new char[N][2];
		nodeArr = new Node[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nodeArr[i] = new Node(st.nextToken().charAt(0));
			arr[i][0] = st.nextToken().charAt(0);
			arr[i][1] = st.nextToken().charAt(0);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][0] == nodeArr[j].data)
					nodeArr[i].leftChild = nodeArr[j];
				
				if (arr[i][1] == nodeArr[j].data)
					nodeArr[i].rightChild = nodeArr[j];
			}
		}
		
		preorder(nodeArr[0]);
		sb.append(lineSeparator);
		inorder(nodeArr[0]);
		sb.append(lineSeparator);
		postorder(nodeArr[0]);
		
		System.out.print(sb);
	}
	
	private static void preorder(Node node) {
		if (node == null)
			return ;
		
		sb.append(node.data);
		preorder(node.leftChild);
		preorder(node.rightChild);
	}
	
	private static void inorder(Node node) {
		if (node == null)
			return ;
		
		inorder(node.leftChild);
		sb.append(node.data);
		inorder(node.rightChild);
	}
	
	private static void postorder(Node node) {
		if (node == null)
			return ;
		
		postorder(node.leftChild);
		postorder(node.rightChild);
		sb.append(node.data);
	}
}
