package BOJ;

import java.util.*;
import java.io.*;

/*
* 제목
<별 찍기 - 11>
* 링크
https://www.acmicpc.net/problem/2448
* 요약
첫째 줄부터 N번째 줄까지 별을 출력한다.
* 풀이
규칙찾기
* 메모리
149672 KB
* 시간
708 ms
*/

public class boj_2448 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();



    public static void main(String[] args) throws IOException {
        // 입력
        int N = Integer.parseInt(br.readLine()); // 3 6 12 24 48 96 192 384 768 1536 3072 (출력 결과의 세로 길이)
        char[][] answer = new char[N][N * 2 - 1]; // 출력할 배열
        for(int i=0; i<answer.length; i++) {
            Arrays.fill(answer[i], ' ');
        }

        // 풀이
        char[][] intialTriangle = {{' ', ' ', '*', ' ', ' '},
                                            {' ', '*', ' ', '*', ' '},
                                            {'*', '*', '*', '*', '*'}};
        int intialTriangleHeight = intialTriangle.length; // 3
        int intialTriangleWidth = intialTriangle[0].length; // 5

        // N이 3일 때 -> 바로 출력
        if(N==3) {
            for (int i = 0; i < intialTriangleHeight; i++) {
                for (int j = 0; j < intialTriangleWidth; j++) {
                    sb.append(intialTriangle[i][j]);
                }
                sb.append('\n');
            }
            bw.write(sb.toString());
            bw.flush();
            return;
        }

        // N이 6이상일 때
        // 1. 초기 상태 설정 - 중심 찾기, 가장 작은 부분삼각형 복붙
        // 2. while 돌면서 양 옆 하단에 현재 부분삼각형을 복붙
        // 3. 부분삼각형 갱신. 갱신된 부분삼각형이 최종 결과물과 동일하면 while 종료
        // 4. 출력

        // 1 초기 상태 설정 - 중심 찾기, 가장 작은 부분삼각형 복붙
        int centerJ = answer[0].length / 2;
        int startI = 0;
        int startJ = centerJ - 2;

        for(int i=0; i<intialTriangleHeight; i++) {
            for(int j=0; j < intialTriangleWidth; j++) {
                answer[startI+i][startJ+j] = intialTriangle[i][j];
            }
        }

        // 2. while 돌면서 양 옆 하단에 현재 부분삼각형을 복붙
        int currentStateHeight = intialTriangleHeight;
        int currentStateWidth = intialTriangleWidth;
        do {
            // 왼쪽 밑에 복붙
            int leftI = currentStateHeight;
            int leftJ = centerJ - currentStateWidth;
            for(int i=0; i<currentStateHeight; i++) {
                for(int j=0; j < currentStateWidth; j++) {
                    answer[leftI+i][leftJ+j] = answer[startI+i][startJ+j];
                }
            }

            // 오른쪽 밑에 복붙
            int rightI = currentStateHeight;
            int rightJ = centerJ + 1;
            for(int i=0; i<currentStateHeight; i++) {
                for(int j=0; j < currentStateWidth; j++) {
                    answer[rightI+i][rightJ+j] = answer[startI+i][startJ+j];
                }
            }

            // 3. 부분삼각형 갱신. 갱신된 부분삼각형이 최종 결과물과 동일하면 while 종료
            currentStateHeight *= 2;
            currentStateWidth = currentStateWidth * 2 + 1;
            startI = 0;
            startJ = centerJ - currentStateWidth / 2;
        } while(currentStateHeight != N);

        // 4. 출력
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[i].length; j++) {
                sb.append(answer[i][j]);
            }
            sb.append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
