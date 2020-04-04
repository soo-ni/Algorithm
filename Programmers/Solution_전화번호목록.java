package study;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Solution_전화번호목록 {
	
	public static void main(String[] args) {
		boolean answer;
		String[] phone_book = {"119", "97674223", "1195524421"};
		Arrays.sort(phone_book, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length()-o2.length();
			}
		});
		
		Set<String> set = new HashSet<String>();
		
		int count=phone_book[0].length();
		set.add(phone_book[0]);
		int len = phone_book.length;
		String phone, subStr; int subLen;
		L: for(int i=1; i<len; i++) {
			phone = phone_book[i];
			subLen = phone_book[i].length();
			
			for(int j=count; j<subLen; j++) {
				subStr = phone.substring(0, j);
				if(!set.add(subStr)) {
					answer=false;
					break L;
				}
				set.remove(subStr);
			}
			set.add(phone);
		}
		
		System.out.println(phone_book);
	}

}
