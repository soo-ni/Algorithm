import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] answer = new int[N];
        
        LinkedList<Integer> stack = new LinkedList<Integer>();
        for(int i=0; i<N; i++) {
            stack.clear();
            for(int j=i+1; j<N; j++) {
                if(prices[i]<=prices[j]) {
                    stack.add(prices[j]);
                }else {
                    stack.add(prices[j]);
                    break;
                }
            }
            answer[i] = stack.size();
        }
        
        return answer;
    }
    
    public void show(int[] stock, int[] answer) {
        System.out.println("stock");
        for(int i: stock)
            System.out.print(i+" ");
        
        System.out.println("answer");
        for(int i: answer)
            System.out.print(i+" ");
    }
}
