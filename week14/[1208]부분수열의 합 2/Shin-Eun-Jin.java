import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1208_부분수열의합 {
    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int mid = N / 2;

        ArrayList<Long> leftSums = new ArrayList<>();
        ArrayList<Long> rightSums = new ArrayList<>();

        calSum(0, mid, 0, leftSums);
        calSum(mid, N, 0, rightSums);

        Collections.sort(leftSums);
        Collections.sort(rightSums);

        long ans = calCount(leftSums, rightSums);
        System.out.println(S == 0 ? ans - 1 : ans);
    }

    public static void calSum(int start, int end, long sum, ArrayList<Long> list) {

        if (start == end) {
            list.add(sum);
            return;
        }

        calSum(start + 1, end, sum + arr[start], list);
        calSum(start + 1, end, sum, list);
    }

    public static long calCount(ArrayList<Long> leftList, ArrayList<Long> rightList) {
        long count = 0;
        int leftPointer = 0;
        int rightPointer = rightList.size() - 1;

        while (leftPointer < leftList.size() && rightPointer >= 0) {
            long sum = leftList.get(leftPointer) + rightList.get(rightPointer);

            if (sum == S) {
                long leftValue = leftList.get(leftPointer);
                long rightValue = rightList.get(rightPointer);

                long leftCount = 0;
                while (leftPointer < leftList.size() && leftList.get(leftPointer) == leftValue) {
                    leftCount++;
                    leftPointer++;
                }

                long rightCount = 0;
                while (rightPointer >= 0 && rightList.get(rightPointer) == rightValue) {
                    rightCount++;
                    rightPointer--;
                }

                count += leftCount * rightCount;
            }
            else if (sum < S) {
                leftPointer++;
            }
            else if (sum > S) {
                rightPointer--;
            }
        }

        return count;
    }

}
