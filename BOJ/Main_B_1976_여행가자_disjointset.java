/**
* 17208	kb
* 136 ms
* Disjoint Set
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_B_1976_여행가자_disjointset {
	
	static int N, M;
	static int[][] linked;
	static Set<Integer> set;
	static boolean[] visited;
	static int[] parents;
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());	// 도시 수
		M = Integer.parseInt(br.readLine());	// 여행 계획에 속한 도시 수
		linked = new int[N][N];	// 인접행렬
		set = new HashSet<Integer>();	// 중복제거된 가고싶은 도시
		
		parents = new int[N];
		Arrays.fill(parents, -1);
		
		int temp, start=0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				temp = Integer.parseInt(st.nextToken());
				linked[i][j] = temp;
				
				if(temp>0) {
					UnionSet(i, j);
				}
				
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<M; i++) {
			temp = Integer.parseInt(st.nextToken())-1;
			set.add(temp);
			
			if(i>0) continue;
			start=i;
		}
		
		int root = findSet(start), tRoot;
		boolean token = true;
		Iterator<Integer> e = set.iterator();
		while(e.hasNext()) {
			tRoot = findSet(e.next());
			if(root!=tRoot) {
				token=false; break;
			}
		}
		
		if(token) System.out.println("YES");
		else System.out.println("NO");
	}
	
	static private int findSet(int a) {
		if(parents[a]<0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static private void UnionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot!=bRoot) {
			parents[bRoot] = aRoot;
		}
	}
	
}
