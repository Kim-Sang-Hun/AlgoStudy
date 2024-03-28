package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* inorder를 보면 root node를 기준으로 left tree, rigth tree로 나눠 지는 것을 볼 수 있고
 * postorder를 보면 root node는 항상 가장 끝에 있다는 것을 알 수 있다 
 * 해당 root node가 inorder의 몇 번째 index에 속해있는지 알아낸 후 left tree, rigth tree로 분할해준다. */
public class BOJ2263_트리의순회_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] inorderArr, postorderArr, inorderIndexArr;

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());

		inorderArr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			inorderArr[i] = Integer.parseInt(st.nextToken());
		}

		postorderArr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			postorderArr[i] = Integer.parseInt(st.nextToken());
		}

		// inorder의 값이 몇 번쨰 index인지 알기 위한 array
		inorderIndexArr = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			inorderIndexArr[inorderArr[i]] = i;
		}

		appendNodeByPreorder(1, n, 1, n);
		System.out.print(sb);
	}

	static void appendNodeByPreorder(int inorderStartIndex, int inorderEndIndex, int postorderStartIndex, int postorderEndIndex) {
		if (inorderEndIndex < inorderStartIndex || postorderEndIndex < postorderStartIndex)
			return;

		int rootNodeValue = postorderArr[postorderEndIndex];
		int rootNodeIndex = inorderIndexArr[rootNodeValue];
		sb.append(rootNodeValue).append(" ");

		int leftTreeLength = rootNodeIndex - inorderStartIndex;

		// left tree
		appendNodeByPreorder(inorderStartIndex, rootNodeIndex - 1, postorderStartIndex, postorderStartIndex + leftTreeLength - 1);
		// right tree
		appendNodeByPreorder(rootNodeIndex + 1, inorderEndIndex, postorderStartIndex + leftTreeLength, postorderEndIndex - 1);
	}
}
