import java.io.*;
import java.util.*;

public class isayaksh {
	
	private static final int MOD = 1000000007;
	private static Map<Long, Long> map = new HashMap<Long, Long>();

    public static void main(String[] args) throws IOException {
    	
    	map.put(1L, 1L);
    	map.put(2L, 1L);
    	map.put(3L, 2L);
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
  
    	System.out.println(fibonacci(N));
    	
    }
    
    private static long fibonacci(long value) {
    	
    	if(map.containsKey(value)) return map.get(value);
    	
    	long m = value/2 + value%2;
    	long n = value/2;
    	
    	long fmn = ((fibonacci(m-1) * fibonacci(n)) % MOD + (fibonacci(m) * fibonacci(n+1)) % MOD) % MOD;
    	
    	map.put(value, fmn);
    	
    	return fmn;
    }
    
}
