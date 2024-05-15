import java.util.*;

class Solution {
    
    private static final String EMPTY = "EMPTY";

    private static String[] board = new String[2551];
    private static int[] parents = new int[2551];
    
    private static List<String> ans = new ArrayList<String>();
    private static StringTokenizer st;
    
    public String[] solution(String[] commands) {
        String[] answer;
        
        for(int i = 0; i < 2551; i++) {
            parents[i] = i;
            board[i] = EMPTY;
        }

        for(String command : commands) {
            st = new StringTokenizer(command);

            String order = st.nextToken();

            if(order.equals("UPDATE")) {
                if(st.countTokens() == 3) {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String value = st.nextToken();
                    update(r, c, value);
                }
                else if(st.countTokens() == 2) {
                    String value1 = st.nextToken();
                    String value2 = st.nextToken();
                    update(value1, value2);
                }
            }

            if(order.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                merge(r1, c1, r2, c2);
            }

            if(order.equals("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                unmerge(r, c);
            }

            if(order.equals("PRINT")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                print(r, c);
            }

        }

        answer = new String[ans.size()];

        for(int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
    
    private static void update(int r, int c, String value) {
        int target = find(r * 50 + c);
        board[target] = value;
    }
    
    private static void update(String value1, String value2) {
        for(int i = 51; i < 2551; i++) {
            if(board[i].equals(value1)) board[i] = value2;
        }
    }
    
    private static void merge(int r1, int c1, int r2, int c2) {
        int t1 = find(r1 * 50 + c1);
        int t2 = find(r2 * 50 + c2);
        
        if(t1 == t2) return;
        
        String value = !board[t1].equals(EMPTY) ? board[t1] : board[t2];
        
        board[t1] = EMPTY;
        board[t2] = EMPTY;
        
        union(t1, t2);

        board[Math.min(t1, t2)] = value;
        
    }
    
    private static void unmerge(int r, int c) {
        
        int target = find(r * 50 + c);
        String targetValue = board[target];

        List<Integer> list = new ArrayList<Integer>();

        for(int i = 51; i < 2551; i++) {
            if(target == find(i)) {
                list.add(i);
            }
        }

        for(Integer i : list) {
            parents[i] = i;
            board[i] = EMPTY;
        }

        board[r * 50 + c] = targetValue;

    }
    
    private static void print(int r, int c) {
        String target = board[find(r * 50 + c)];
        ans.add(target);
    }
    
    private static int find(int x) {
        if(x != parents[x]) parents[x] = find(parents[x]);
        return parents[x];
    }
    
    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        parents[Math.max(x, y)] = Math.min(x, y);
    }
}
