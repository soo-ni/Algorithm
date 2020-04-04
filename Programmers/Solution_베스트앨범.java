package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class Solution_베스트앨범 {

	public static void main(String[] args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		int[] answer;
		
		HashMap<String, ArrayList<Point>> map = new HashMap<String, ArrayList<Point>>();

		int len=genres.length;
		for(int i=0; i<len; i++) {	// 초기화
			map.put(genres[i], new ArrayList<Point>());
			map.get(genres[i]).add(new Point(-1, 0));
		}
		
		for(int i=0; i<len; i++) {
			map.get(genres[i]).get(0).y += plays[i];
			map.get(genres[i]).add(new Point(i, plays[i]));
		}
		
		ArrayList<Point>[] playlist = new ArrayList[map.size()];
		Iterator<String> e = map.keySet().iterator();
		String key; int num=0;
		while(e.hasNext()) {
			key =  e.next();
			playlist[num++] = map.get(key);
		}
		
		Arrays.sort(playlist, new Comparator<ArrayList<Point>>() {
			@Override
			public int compare(ArrayList<Point> o1, ArrayList<Point> o2) {
				return o2.get(0).y-o1.get(0).y;
			}
		});
		
		len = playlist.length;
		for(int i=0; i<len; i++) {
			Collections.sort(playlist[i]);
		}
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i=0; i<len; i++) {
			if(playlist[i].size()>2) {
				temp.add(playlist[i].get(1).x);
				temp.add(playlist[i].get(2).x);
			}else {
				temp.add(playlist[i].get(1).x);
			}
		}
		
		len = temp.size();
		answer = new int[len];
		for(int i=0; i<len; i++) {
			answer[i] = temp.get(i);
		}
		
		System.out.println(answer.toString());
	}
	
	static class Point implements Comparable<Point> {
		int x, y;	// 고유번호, 재생횟수
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			int result = o.y-this.y;
			if(result==0) result=this.x-o.x;
			return result;
		}
	}
}
