import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	static String[] result;
	static int answer;
	static Map<String, Integer> map;

// 문자열 돌면서 다른 점을 센다
	public static int checkXOR(String str1, String str2) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if (str1.charAt(i) != str2.charAt(i)) ++count;
		}
		return count;
	}
	// 조합 뽑는 방식과 비슷하지만, 만약 map에 해당 MBTI인 사람이 2명 이상일 경우 중복되게 뽑아볼 필요가 있으므로 해당 MBTI인 사람의 수를 바꿔가며 뽑아준다
	public static void select(int count) {
// 다 뽑았으면 틀린 점이 얼마나 되는지 확인해준다
		if (count == 3) {
			int sum = checkXOR(result[0], result[1]) 
					+ checkXOR(result[1], result[2])
					+ checkXOR(result[2], result[0]);
			answer = Math.min(answer, sum);
			return;
		}
		if (answer == 0) return;
		for (Entry<String, Integer> item : map.entrySet()) {
			if (item.getValue() == 0) continue;
			result[count] = item.getKey();
			item.setValue(item.getValue() - 1);
			select(count + 1);
			item.setValue(item.getValue() + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int i = 0; i < T; i++) {
			answer = Integer.MAX_VALUE;
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			arr = new int[16];
			result = new String[3];
			map = new HashMap<>();
			for (int j = 0; j < N; j++) {
				String key = st.nextToken();
				map.put(key, map.getOrDefault(key, 0) + 1);
			}
			select(0);
			System.out.println(answer);
		}
	}
}
