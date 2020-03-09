/**
* 154,624 kb
* 653 ms
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Solution_D4_7701_염라대왕의이름정렬 {
	static int T, N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		Set<String> set = new HashSet<String>();
		
		for(int t=0; t<T; t++) {
			set.clear();
			N = Integer.parseInt(br.readLine());
			for(int i=0; i<N; i++) {
				String name = br.readLine();
				set.add(name);
			}
			
			ArrayList<String> arr = new ArrayList<String>(set);
			Collections.sort(arr, new Comparator<String>(){
				@Override
				public int compare(String o1, String o2) {
					int result = o1.length()-o2.length();
					if(result==0) {
						for(int i=0; i<o1.length(); i++) {
							result=o1.charAt(i)-o2.charAt(i);
							if(result!=0) break;
						}
					}
					return result;
				}
			});
			
			sb.append('#').append(t+1).append('\n');
			for(String p: arr) {
				sb.append(p).append('\n');
			}
		}	// test case
		
		System.out.println(sb.toString());
	}
}
