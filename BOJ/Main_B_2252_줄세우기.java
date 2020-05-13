/**
 * 46328 kb	
 * 400 ms
 * LinkedList로 인접행렬짜면 시간이 10배~!
 * 
 */

package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_2252_줄세우기 {
	
	static int N, M;
	static ArrayList<Integer>[] line;
	static int[] inDegree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());	// people
		M = Integer.parseInt(st.nextToken());	// line
		inDegree = new int[N+1];	// dgree
		line = new ArrayList[N+1];	// adj list
		for(int i=0; i<N+1; i++) {
			line[i] = new ArrayList<Integer>();
		}
	
		int A, B;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			A = Integer.parseInt(st.nextToken());	// 앞  
			B = Integer.parseInt(st.nextToken());	// 뒤
			line[A].add(B);
			inDegree[B]++;
		}
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for(int i=1; i<N+1; i++) {
			if(inDegree[i]==0) {
				queue.add(i);
			}
		}
		
		int current, len, temp;
		ArrayList<Integer> result = new ArrayList<Integer>();
		while(!queue.isEmpty()) {
			current = queue.poll();
//			result.add(current);
			sb.append(current).append(' ');
			len = line[current].size();
			for(int i=0; i<len; i++) {
				temp = line[current].get(i);
				inDegree[temp]--;
				if(inDegree[temp]==0) {
					queue.add(temp);
				}
			}
		}
		
//		for(Integer rs: result) {
//			sb.append(rs).append(' ');
//		}
		
		System.out.println(sb.toString());
	}
}
