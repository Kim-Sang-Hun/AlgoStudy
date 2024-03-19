package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <트리의 지름> G2
 * 링크
 * https://www.acmicpc.net/problem/1167
 * 요약
 * 트리의 지름
 * 1) 임의의 한 정점 x에서 가장 멀리 떨어진 정점 y를 구한다.
 * 2) 정점 y에서 가장 멀리 떨어진 정점 z를 구한다.
 * 3) 트리의 지름은 정점 y에서 정점 z 로 가는 경로와 같다.  
 * 풀이
 * dfs
 */
public class boj_1167 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "|" + num + " " + weight + "|";
        }
    }

    static int V; // 정점 개수 1~V
    static ArrayList<Node>[] nodeList;
    static int[] started;
    static boolean[] visited;

    static int max;
    static int startPos;

    public static void main(String[] args) throws IOException {
        // 입력
        V = Integer.parseInt(br.readLine());
        nodeList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            nodeList[i] = new ArrayList<>();
        }
        started = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1)
                    break;
                int weight = Integer.parseInt(st.nextToken());
                nodeList[node].add(new Node(num, weight));
            }
        }

        // 풀이
        visited = new boolean[V + 1];
        max = Integer.MIN_VALUE;
        visited[1] = true;
        dfs(1, 0); // 임의로 선택한 정점 1에서 가장 멀리 떨어진 정점 찾기 (시작지점 찾기)
        // System.out.println("start pos is " + startPos);

        visited = new boolean[V + 1];
        max = Integer.MIN_VALUE;
        visited[startPos] = true;
        dfs(startPos, 0);
        // System.out.println("end pos is " + startPos);

        // 출력
        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(int cur, int dist) {
        boolean hasNext = false;
        for (int i = 0; i < nodeList[cur].size(); i++) {
            int curNodeNum = nodeList[cur].get(i).num;
            if (visited[curNodeNum] == false) { // 하나라도 갈 수 있는 정점이 있으면 hasNext = true
                hasNext = true;
                break;
            }
        }
        if (hasNext == false) { // 더 이상 갈 곳이 없음
            if (dist > max) { // 현재까지 누적 거리가 max보다 크면 갱신
                max = dist;
                startPos = cur;
            }
            return;
        }

        for (int i = 0; i < nodeList[cur].size(); i++) {
            int nextNodeNum = nodeList[cur].get(i).num;
            if (visited[nextNodeNum])
                continue; // 방문한 정점 제외
            visited[nextNodeNum] = true;
            dfs(nextNodeNum, dist + nodeList[cur].get(i).weight);
            visited[nextNodeNum] = false;
        }
    }
}
