package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class boj_2529 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int k; // 부등호 개수
    static char[] karr; // 부등호 
    static boolean[] isUsed; 
    static int[] temp;
    static List<String> possibleList; // 가능성 있는 모든 경우 저장하는 리스트
    public static void main(String[] args) throws IOException{
        // 입력
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        karr = new char[k];
        for(int i=0; i<k; i++)
            karr[i]=st.nextToken().charAt(0);

        // 풀이    
        possibleList = new ArrayList<>();
        isUsed = new boolean[10];
        temp = new int[k+1];
        bt(0);

        // 출력
        String max = possibleList.get(possibleList.size()-1).replaceAll("\\[", "")
                                                            .replaceAll("\\]","")
                                                            .replaceAll(" ", "")
                                                            .replaceAll(",", "");
        String min = possibleList.get(0).replaceAll("\\[", "")
                                            .replaceAll("\\]","")
                                            .replaceAll(" ", "")
                                            .replaceAll(",", "");
        bw.write(max+"\n");
        bw.write(min+'\n');
        bw.flush();
        bw.close(); 
    }

    static void bt(int depth) {
        if(depth==k+1) {
            if(isValid(temp)) {
                possibleList.add(Arrays.toString(temp));                                                        
            }
            return;
        }
        for(int i=0; i<10; i++) {
            if(isUsed[i]==false) {
                temp[depth] = i;
                isUsed[i] = true;
                bt(depth+1);
                isUsed[i]=false;
            }
        }
    }

    static boolean isValid(int[] temp) {
        for(int i=0; i<k; i++) {
            if(karr[i]=='>') {
                if(temp[i] < temp[i+1]) return false;
            } else {
                if(temp[i] > temp[i+1]) return false;
            }
        }
        return true;
    }
}