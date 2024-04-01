import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int n, k;
  //왼쪽, 오른쪽 확인하기 위한 간단한 클래스
	static class Info{
		char a;
		int left, right;
		Info(int num){
			left = right = -1;
			a = (char) (num + 65);
		}
	}
  //철자 갯수만큼인 26개만 담으면 됨
	static Info[] node = new Info[27];

  //1글자 문자열 변경을 위한 메서드
	static int transform(String num) {
		return num.charAt(0) - 'A';
	}

  //입력과 동시에 트리 생성
	static void input() throws IOException{
		n = Integer.parseInt(br.readLine());
		for(int i = 0;i < 26; ++i) {
			node[i] = new Info(i);
		}
		for(int i = 0;i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int par = transform(st.nextToken());
			int left = transform(st.nextToken());
			int right = transform(st.nextToken());
			if(left != transform(".")) {
				node[par].left = left;
			}
			if(right != transform(".")) {
				node[par].right = right;
			}
		}
	}

  //전위 순회
	static void preorder(int idx) {
		if(idx < 0 || idx >= 26) return;
		sb.append((char)(idx + 'A'));
		preorder(node[idx].left);
		preorder(node[idx].right);
	}

  //중위 순회
	static void inorder(int idx) {
		if(idx < 0 || idx >= 26) return;
		inorder(node[idx].left);
		sb.append((char)(idx + 'A'));
		inorder(node[idx].right);
	}

  //후위 순회
	static void postorder(int idx) {
		if(idx < 0 || idx >= 26) return;
		postorder(node[idx].left);
		postorder(node[idx].right);
		sb.append((char)(idx + 'A'));
	}
	
	static void solution() {
		preorder(0); sb.append('\n');
		inorder(0); sb.append('\n');
		postorder(0); sb.append('\n');
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
}
