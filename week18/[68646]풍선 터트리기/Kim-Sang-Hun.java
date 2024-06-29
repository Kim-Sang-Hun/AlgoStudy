/*
맨 왼쪽과 맨 오른쪽 풍선은 항상 살아남을 수 있다.
가운데 있는 풍선의 경우 왼쪽과 오른쪽에 자기보다 작은 풍선이 있을 경우 살아남지 못한다.
그러므로 왼쪽에서의 최소값, 오른쪽에서의 최소값을 구해서 살아남을 수 있는 풍선을 구해준다.
*/
class Solution {
    public int solution(int[] a) {
        
        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];
        int left = a[0];
        int right = a[a.length-1];
        
        for(int i = 1; i < a.length - 1; i++) {
            if (left > a[i]) {
                left = a[i];
            }
            leftMin[i] = left;
        }
        
        for(int i = a.length - 2; i > 0; i--) {
            if(right > a[i]) {
                right = a[i];
            }
            rightMin[i] = right;
        }
        
        if(a.length == 1) return 1;
        int answer = 2;
        for(int i = 1; i <= a.length - 2; i++) {
            if(a[i] > leftMin[i] && a[i] > rightMin[i]) continue;
            answer++;
        }
        return answer;
    }
}
