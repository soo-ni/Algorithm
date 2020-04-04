package study;

import java.util.HashMap;
import java.util.Iterator;

public class Solution_완주하지못한선수 {

	public static void main(String[] args) {
		String answer="";
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		
		int len = participant.length;
		int cnt;
		for(int i=0; i<len; i++) {
			if(map.get(participant[i])!=null) {
				cnt = map.get(participant[i]);
				map.put(participant[i], cnt+1);
			}else {
				map.put(participant[i], 1);
			}
		}
		
		String key;
		Iterator<String> e;
		len = completion.length;
		for(int i=0; i<len; i++) {
			cnt = map.get(completion[i]);
			map.replace(completion[i], cnt-1);
		}
		
		e = map.keySet().iterator();
		while(e.hasNext()) {
			key = e.next();
			if(map.get(key)>0) {
				answer=key;
				break;
			}
		}
		System.out.println(answer);
		
	}
}


