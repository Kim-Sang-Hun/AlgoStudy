import java.io.*;
import java.util.*;

public class isayaksh {

    private static final int dx[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int dy[] = {-1, -1, -1, 0, 0, 1, 1, 1};

    private static int N, M, K;
    private static char[][] board;
    private static char[] target;
    
    private static int nx, ny;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        // init board
        board = new char[N][M];
        for(int n = 0; n < N; n++) board[n] = br.readLine().toCharArray();
        
        // 중복 문자열 처리
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        int count;
        
        for(int k = 0; k < K; k++) {
        	target = br.readLine().toCharArray();
        	
        	if(map.containsKey(String.valueOf(target))) {
        		sb.append(map.get(String.valueOf(target))).append("\n");
        		continue;
        	}
        	
        	count = 0;
        	for(int y = 0; y < N; y++) {
        		for(int x = 0; x < M; x++) {
        			// 신이 좋아하는 문자열의 시작 문자가 맞지 않는 경우
        			if(board[y][x] != target[0]) continue;
        			count += dfs(x, y, 0);
        		}
        	}
        	sb.append(count).append("\n");
        	map.put(String.valueOf(target), count);
        }
        System.out.println(sb);
    }
    
    private static int dfs(int x, int y, int depth ) {
    	
    	// 신이 좋아하는 문자열 매칭!
    	if(depth == target.length-1) return 1;
    	
    	int count = 0;
    	
    	for(int i = 0; i < 8; i++) {
    		nx = (x + dx[i] + M) % M;
    		ny = (y + dy[i] + N) % N;
    		
    		if(board[ny][nx] != target[depth+1]) continue;
    		
    		count += dfs(nx, ny, depth + 1);
    		
    	}
    	
    	return count;
    	
    }

    
}
