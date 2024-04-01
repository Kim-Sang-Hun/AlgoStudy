import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String[] mbtiList = br.readLine().split(" ");
            sb.append(solution(N, mbtiList) + "\n");
        }
        System.out.println(sb);
    }

    public static int solution(int N, String[] mbtiList) {
        // MBTI의 종류는 총 16개이다. 즉, 33개 이상부터는 무조건 같은 MBTI가 3개 이상 나올 수 밖에 없다.
        if(N > 32) {
            return 0;
        }
        int answer = Integer.MAX_VALUE;

        for(int i = 0; i < N-2; i++) {
            for(int j = i+1; j < N-1; j++) {
                for(int k = j+1; k < N; k++) {
                    answer = Math.min(answer, getPsychologicalDistance(mbtiList[i], mbtiList[j], mbtiList[k]));
                }
            }
        }
        return answer;
    }

    public static int getPsychologicalDistance(String mbti1, String mbti2, String mbti3) {
        int psychologicalDistance = 0;
        for(int i = 0; i < 4; i++) {
            if(mbti1.charAt(i) != mbti2.charAt(i)) {
                psychologicalDistance++;
            }
            if(mbti1.charAt(i) != mbti3.charAt(i)) {
                psychologicalDistance++;
            }
            if(mbti2.charAt(i) != mbti3.charAt(i)) {
                psychologicalDistance++;
            }
        }
        return psychologicalDistance;
    }
}