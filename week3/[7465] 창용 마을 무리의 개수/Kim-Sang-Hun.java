import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

class Solution {
  // 무리의 개수 세기. 전체 인원 수 만큼의 배열을 만들고 입력을 받으면서 from, to값을 확인한다.
  static int N, M, count, answer, arr[];
  static Set<Integer> set;
	public static void main(String[] args) {
    	Scanner s = new Scanner(System.in);
      int T = s.nextInt();
      for (int tc = 1; tc <= T; tc++) {
        	N = s.nextInt();
          M = s.nextInt();
          answer = 0;
          count = 0;
          arr = new int[N + 1];
          set = new HashSet<>();
          for (int i = 0; i < M; i++) {
            	  int a = s.nextInt();
                int b = s.nextInt();
              // from, to값을 확인하여 더 작은 값을 기준으로 arr배열의 해당 인덱스를 작은 값으로 통일시켜준다.
                if (arr[a] != 0 && arr[b] != 0) {
                    int from = arr[a] > arr[b] ? arr[a] : arr[b];
                    int to = arr[a] > arr[b] ? arr[b] : arr[a];
                    for (int j = 1; j <= N; j++) {
                    // 만약 arr안의 값이 더 큰 값이라면 더 작은 값으로 모두 바꿔준다.
                    	if (arr[j] == from) {
                            arr[j] = to;
                        }
                    }
                  // 만약 둘 중에 이미 들어있는 값이 있는 노드가 있다면 그 노드를 기준으로 값을 바꿔준다.
                } else if (arr[a] != 0) {
                	arr[b] = arr[a];
                } else if (arr[b] != 0) {
                	arr[a] = arr[b];
                } else {
                	arr[a] = arr[b] = a > b ? b : a;
                }
            }
          for (int i = 1; i <= N; i++) {
            // 마지막으로 중복제거를 위해 set이용하여 카운팅해준다.
              if (arr[i] != 0) {
					      set.add(arr[i]);
            // 만약 arr[i]가 0이라면 다른 노드와 연결되지 않은 것이므로 count를 늘려준다.
              } else {
                count++;
              }
          }
        // set의 크기와 count를 더해서 출력한다.
          System.out.printf("#%d %d\n", tc, set.size() + count);
        }
    }
}
