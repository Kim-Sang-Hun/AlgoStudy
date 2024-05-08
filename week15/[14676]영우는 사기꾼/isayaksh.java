import java.io.*;
import java.util.*;

public class isayaksh {
	
	private static int N, M, K;
	
	private static int[] degree;	// 진입차수
	private static int[] count;		// 건설된 건물의 수
	private static Map<Integer, List<Integer>> dependency = new HashMap<Integer, List<Integer>>();

    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        degree = new int[N+1];
        count = new int[N+1];
        
        for(int n = 1; n < N+1; n++) {
        	dependency.put(n, new ArrayList<Integer>());
        }
        
        int X, Y;
        for(int m = 0; m < M; m++) {
        	st = new StringTokenizer(br.readLine());
        	X = Integer.parseInt(st.nextToken());
        	Y = Integer.parseInt(st.nextToken());
        	
        	dependency.get(X).add(Y);
        	degree[Y]++;
        	
        }
        
        int order, building;
        boolean isPossible = true;
        for(int k = 0; k < K; k++) {
        	st = new StringTokenizer(br.readLine());
        	
        	order = Integer.parseInt(st.nextToken());
        	building = Integer.parseInt(st.nextToken());
        	
        	// 건설
        	if(order == 1) {
        		isPossible = build(building);
        	}
        	
        	// 파괴
        	if(order == 2) {
        		isPossible = destroy(building);
        	}
        	
        	if(!isPossible) break;
        	
        }
        
        System.out.println(isPossible ? "King-God-Emperor" : "Lier!");
    	
    }
    
    private static boolean build(int building) {
    	// 먼저 건설해야할 건물이 남아있는 경우
    	if(degree[building] != 0) return false;
    	
    	// building이 최초로 건설될 경우
    	if(count[building] == 0) {
    		// building을 선행 조건으로 갖는 건물의 진입차수--;
    		for(Integer postBuilding : dependency.get(building)) degree[postBuilding]--;
    	}
    	
    	// 건물의 수++;
    	count[building]++;
    	
    	return true;
    }
    
    private static boolean destroy(int building) {
    	// 파괴할 건물이 남아있는 경우
    	if(count[building] == 0) return false;
    	
    	// 마지막 남은 building이 파괴될 경우
    	if(count[building] == 1) {
    		// building을 선행 조건으로 갖는 건물의 진입차수++;
    		for(Integer postBuilding : dependency.get(building)) degree[postBuilding]++;
    	}
    	
    	// 건물의 수++;
    	count[building]--;
    	return true;
    }
}
