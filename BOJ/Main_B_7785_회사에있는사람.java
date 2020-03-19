/**
* using hashSet
* 53372 KB	
* 572 ms
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_B_7785_회사에있는사람 {
	
	static int N;
	static Set<String> people;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		people = new HashSet<String>();
				
		String name;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			name = st.nextToken();
			if(st.nextToken().charAt(0) == 'e') { 
				people.add(name);
			}else {
				people.remove(name);
			}
		}
		
		ArrayList<String> arr = new ArrayList<String>(people);
		Collections.sort(arr);
		
		int len = arr.size();
		for(int i=len-1; i>=0; i--) {
			sb.append(arr.get(i)).append('\n');			
		}
		
		System.out.println(sb.toString());
		
	}
}
