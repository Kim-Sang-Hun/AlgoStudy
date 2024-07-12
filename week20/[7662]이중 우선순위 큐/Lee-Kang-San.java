package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
<이중 우선순위 큐> G4
* 링크
https://www.acmicpc.net/problem/7662
* 요약
이중 우선순위 큐에서 주어진 연산에 따라 매 연산 시 최대/최소 값 추출하기
* 풀이
min heap, max heap 사용
매 데이터 저장 시 양쪽 모두 저장 (새 객체 생성 후 레퍼런스 삽입)
데이터 추출 시 해당하는 heap 에서 poll. 반대쪽 heap에서도 무효한 값임을 알아야 하므로 valid 값 갱신.
heap의 비교 연산 구현 시 값끼리 직접 빼기 연산 시 오버플로우의 문제 발생
-> 비교 연산으로 대체, 리턴 값은 1, -1로 하기
* 메모리
337656 KB
* 시간
2020 ms
*/

public class boj_7662 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Data {
        boolean valid;
        int value;

        public Data(boolean valid, int value) {
            this.valid = valid;
            this.value = value;
        }
    }

    static int T; // T 개의 테스트데이터
    static int k; // 각 테스트데이터 당 연산 개수 (최대 100만)
    static PriorityQueue<Data> pqMin, pqMax;
    static int pqCount;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            //입력
            initPriorityQueue();
            k = Integer.parseInt(br.readLine()); // k개 연산
            // 풀이
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                char op = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());
                if (op == 'I') {
                    Data data = new Data(true, num);
                    // 이중 우선순위 큐에 삽입
                    pqMax.offer(data);
                    pqMin.offer(data);
                    pqCount++;
                } else { // op == 'D'
                    // 이중 우선순위 큐에서 삭제
                    if (pqCount == 0) {
                        continue;
                    } else if (num == 1) { // 최대값 추출
                        removeImvalidData();
                        Data data = pqMax.poll();
                        data.valid = false;
                    } else { // num == -1 // 최소값 추출
                        removeImvalidData();
                        Data data = pqMin.poll();
                        data.valid = false;
                    }
                    pqCount--;
                }
            }
            // 출력
            if (pqCount == 0) {
                sb.append("EMPTY\n");
            } else {
                removeImvalidData();
                sb.append(pqMax.peek().value).append(' ').append(pqMin.peek().value).append('\n');
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static void removeImvalidData() {
        while (!pqMax.peek().valid) {
            pqMax.poll();
        }
        while (!pqMin.peek().valid) {
            pqMin.poll();
        }
    }


    private static void initPriorityQueue() {
        pqMax = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if (o2.value > o1.value) {
                    return 1;
                } else
                    return -1;
            }
        });
        pqMin = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if (o2.value < o1.value) {
                    return 1;
                } else
                    return -1;
            }
        });
        pqCount = 0;
    }
}
