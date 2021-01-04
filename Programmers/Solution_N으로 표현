class Solution {
    static int answer;
    public int solution(int N, int number) {
        answer = -1;        
        dfs(0, 0, number, N);  // idx, sum, number, N
        
        return answer;
    }
    
    public void dfs(int idx, int sum, int number, int N) {
        int next = N;
        
        if(idx>8) {
            answer=-1;
            return;
        }
        
        if(sum==number) {
            if(answer<0 || answer>idx)
                answer = idx;
            return;
        }
        
        for(int i=1; i<9-idx; i++) {
            dfs(idx+i, sum+next, number, N);
            dfs(idx+i, sum-next, number, N);
            dfs(idx+i, sum/next, number, N);
            dfs(idx+i, sum*next, number, N);
            next = next+(int)(N*(Math.pow(10, i)));
        }
    }
}
