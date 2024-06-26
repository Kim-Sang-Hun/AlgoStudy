import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int N = book_time.length;
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            String[] start = book_time[i][0].split(":");
            String[] end = book_time[i][1].split(":");

            arr[i][0] = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            arr[i][1] = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]) + 10;
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(arr[0][1]);

        for (int i = 1; i < N; i++) {
            if (arr[i][0] >= pq.peek()) {
                pq.poll();
            }
            pq.add(arr[i][1]);
        }

        return pq.size();
    }
}
