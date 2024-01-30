import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Pair> lectureList = new ArrayList<>();

        // input
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int[] st = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            lectureList.add(new Pair(st[0], st[1]));
        }

        lectureList.sort(Comparator
                .comparingInt((Pair pair) -> pair.getStart())
                .thenComparing(pair -> pair.getEnd()));

        // result
        System.out.println(solution(T, lectureList));

    }

    public static int solution(int T, List<Pair> lectureList) {

        int start, end;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        priorityQueue.add(0);

        for (int i = 0; i < T; i++) {
            start = lectureList.get(i).getStart();
            end = lectureList.get(i).getEnd();

            if(priorityQueue.peek() <= start) {
                priorityQueue.poll();
            }
            priorityQueue.add(end);
        }
        return priorityQueue.size();
    }
}

class Pair implements Comparable<Pair> {
    int start;
    int end;

    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public int compareTo(Pair other) {
        if (this.start != other.start) {
            return Integer.compare(this.start, other.start);
        } else {
            return Integer.compare(this.end, other.end);
        }
    }
}