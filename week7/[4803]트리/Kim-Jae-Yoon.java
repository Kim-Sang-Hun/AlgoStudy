/*
* 1번은 Disjoint-Set으로 풀었고,
* 2번은 BFS 방식으로 문제를 풀었습니다.
* 두 방식 중 1번은 간결하게 구현할 수 있고, 2번은 구현이 상대적으로 쉽다는 특징을 가집니다.
*/

//solution 1
//Disjoint-Set 문제 풀이
import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] par;
    static int n, m, cnt = 1;

    //사이클 관계에 있다면 par[i] = 0일테니 트리 관계 계산에 영향이 없음
    //이외에 par[i] == i인 경우는 루트 노드이고, 해당 노드를 기반으로 트리가 형성되어있다는 뜻이니 총 결과값에 더해줍니다.
    static void solution() throws IOException{
        sb.append("Case ").append(cnt++).append(": ");
        int answer = 0;
        for (int i = 1; i <= n; ++i) {
            if(par[i] == i) ++answer;
        }
        if(answer == 0){
            sb.append("No trees.\n");
        } else if (answer == 1) {
            sb.append("There is one tree.\n");
        } else {
            sb.append("A forest of ").append(answer).append(" trees.\n");
        }
    }

    static boolean input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if(n == 0 && m == 0) return false;
        par = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            par[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        return true;
    }

    //입력값 기반 부모-자식 관계를 형성해주는 union 메서드
    //해당 메서드를 통해 사이클 형성 여부 확인까지 해줍니다.
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        //같은 부모를 가졌는데도 다시 연결이 되었다는 뜻, 사이클
        if(par[a] == par[b] || par[a] == 0 || par[b] == 0){
            par[a] = par[b] = 0;
            return;
        }
        par[b] = a;
    }

    static int find(int cur) {
        if(par[cur] == cur) return cur;
        return par[cur] = find(par[cur]);
    }

    public static void main(String[] args) throws IOException {
        while(input()){
            solution();
        }
        System.out.println(sb);
    }
}

//solution 2
//BFS 문제 풀이
import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<Integer>[] edge;
    static boolean[] vis;
    static int n, m, cnt = 1;

    //트리의 사이클 조건 또한 결국 간선이 n - 1이며, 정점이 n개인 경우를 따지는 것으로 검증 가능하다.
    //즉 마지막에 edge의 수와 node의 수에 따른 차이를 비교한다.
    //이 때 edge는 양방향으로 연결되어 있으므로 shift연산을 통해 2를 나눠준다.
    static boolean dfs(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        int edgeCnt = 0, nodeCnt = 0;
        vis[start] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            ++nodeCnt;
            edgeCnt += edge[cur].size();
            for (int nxt : edge[cur]) {
                if(vis[nxt]) continue;
                vis[nxt] = true;
                q.add(nxt);
            }
        }
        if((edgeCnt >> 1) == nodeCnt - 1) return true;
        return false;
    }

    //n개의 노드를 탐색하며 해당 노드로부터 아직 탐색되지 않은 경우 새로운 트리인지 여부를 확인할 수 있게 됩니다.
    static void solution() throws IOException{
        sb.append("Case ").append(cnt++).append(": ");
        int answer = 0;
        for (int i = 1; i <= n; ++i) {
            if(vis[i]) continue;
            if(dfs(i)) ++answer;
        }
        if(answer == 0){
            sb.append("No trees.\n");
        } else if (answer == 1) {
            sb.append("There is one tree.\n");
        } else {
            sb.append("A forest of ").append(answer).append(" trees.\n");
        }
    }

    static boolean input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if(n == 0 && m == 0) return false;
        edge = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) {
            edge[i] = new ArrayList<>();
        }
        vis = new boolean[n + 1];
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge[a].add(b);
            edge[b].add(a);
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        while(input()){
            solution();
        }
        System.out.println(sb);
    }
}
