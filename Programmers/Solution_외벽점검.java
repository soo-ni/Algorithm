package study;

public class Solution_외벽점검 {
	
	static int n = 12, len, people, answer;
	static int[] weak = {1, 3, 4, 9, 10};
	static int[] dist = {3, 5, 7};
	static int[] selected, next;
	static boolean[] visited;
	public static void main(String[] args) {
		
		len = weak.length;
		people = dist.length;
		selected = new int[people];
		visited = new boolean[people];
		
		next = new int[len+len-1];
		for(int i=0; i<next.length; i++) {
			if(i<len) next[i] = weak[i];
			else next[i] = weak[i-len]+n;
		}
		
		answer = Integer.MAX_VALUE;
		dfs(0);	//idx
		
		if(answer == Integer.MAX_VALUE) answer=-1;
		System.out.println(answer);
	}
	
	private static void dfs(int idx) {
		
		if(idx==people) {
			answer = Math.min(answer, check());
			return;
		}
		
		for(int i=0; i<people; i++) {
			if(visited[i]) continue;
			visited[i]=true;
			selected[idx]=i;
			dfs(idx+1);
			visited[i]=false;
		}
	}

	private static int check() {
		int min = Integer.MAX_VALUE;
		for(int i=0; i<len; i++) {
			int idx = 0;
			int start = next[i];
			boolean flag = false;
			
			for(int j=i+1; j<i+len; j++) {
				if(next[j]-start > dist[selected[idx]]) {
					start = next[j];
					idx++;
					
					if(idx==people) {
						flag = true;
						break;
					}
				}
			}
			
			if(!flag) min = Math.min(min, idx+1);
		}
		
		return min;
	}
	
}

//while(start < i+len-1) {
//distance = next[start+1]-next[start];
//
//if(person==people) {
//	flag=true;
//	break;
//}
//
//if(dist[selected[person]]-remain < distance) {	// 거리가 더 멀다면
//	person++;
//	remain=0;
//}else {
//	start++;	// 다음 취약점으로 이동
//	if(pre!=person) {
//		sum++;	// 사람 더하기
//		pre=person;
//	}
//	remain = dist[selected[person]] - distance;	// 남아있는 거리
//	if(remain==0) person++;	// 남아있는 거리가 없으면 다음사람
//	else remain = distance;
//}
//}
//
//if(!flag) min = Math.min(min, sum);
