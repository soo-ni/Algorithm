package study;

import java.util.HashMap;
import java.util.Iterator; 

public class Solution_위장 {
	static int[] clothesNum;
	static boolean[] select;
	public static void main(String[] args) {
//		String[][] clothes = {{"yellow_hat", "headgear"}, 
//				{"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
		String[][] clothes = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
		boolean token=false;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int cnt;
		for(String[] arr: clothes) {
			cnt=0;
			if(map.get(arr[1])!=null) {
				cnt=map.get(arr[1]);
				token=true;
			}
			map.put(arr[1], cnt+1);
		}
		
		if(!token){
            answer=(int) Math.pow(2, map.size());
//            return answer;
        }
		
		clothesNum = new int[map.size()];
		select = new boolean[map.size()];
		Iterator<String> e = map.keySet().iterator();
		int no=0; String key;
		while(e.hasNext()) {
			key = e.next();
			clothesNum[no++] = map.get(key);
		}
		
		answer=0;
		dfs(0, 0);
		System.out.println(answer);
	}

	static int answer;
	private static void dfs(int pre, int idx) {
		if(idx>0) {
			int cnt=1;
			for(int i=0; i<clothesNum.length; i++) {
				if(select[i]) {
					cnt*=clothesNum[i];
				}
			}
			
			answer+=cnt;
		}
		
		for(int i=pre; i<clothesNum.length; i++) {
			if(!select[i]) {
				select[i]=true;
				dfs(i, idx+1);
				select[i]=false;
			}
		}
		
	}

}
