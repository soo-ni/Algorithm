class Solution {
    static int[] dpF, dpL;
    public int solution(int[] money) {
        dpF = new int[10000000];    // 첫번째집 털기
        dpL = new int[10000000];    // 마지막집 털기
        
        dpF[0] = money[0];
        dpF[1] = money[0];
        dpL[0] = 0;
        dpL[1] = money[1];
        
        int len = money.length-1;   // 마지막 집 전까지
        for(int i=2; i<len; i++) {
            dpF[i] = Math.max(dpF[i-2]+money[i], dpF[i-1]); // 현재집 터는게 나은지 아닌지
            dpL[i] = Math.max(dpL[i-2]+money[i], dpL[i-1]);
        }
        dpL[len] = Math.max(dpL[len-2]+money[len], dpL[len-1]); // 마지막집 털지말지
        
        int answer = Math.max(dpF[len-1], dpL[len]);
        return answer;
    }
}
