package study;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution_오픈채팅방 {
	
	public static void main(String[] args) {
		String[] record = {
				"Enter uid1234 Muzi", 
				"Enter uid4567 Prodo",
				"Leave uid1234",
				"Enter uid1234 Prodo",
				"Change uid4567 Ryan"
		};
		
		int cnt = 0;
		HashMap<String, Integer> uid = new HashMap<>();
		HashMap<Integer, String> unick = new HashMap<>();
		ArrayList<Point> tempAnswer = new ArrayList<>();
		
		String[] temp; String id, nick;
		for(String msg: record) {
			temp = msg.split(" ");
			id = temp[1];
			
			if(msg.contains("Enter")) {
				if(uid.get(id)!=null) {
					tempAnswer.add(new Point(uid.get(id), 0));
					unick.replace(uid.get(id), temp[2]);
				}else {
					uid.put(id, cnt);
					tempAnswer.add(new Point(uid.get(id), 0));
					unick.put(uid.get(id), temp[2]);
					cnt++;
				}
				
			}else if(msg.contains("Leave")) {
				tempAnswer.add(new Point(uid.get(id), 1));
				
			}else if(msg.contains("Change")) {
				if(uid.get(id)!=null) {
					unick.replace(uid.get(id), temp[2]);
				}
				
			}
		}
		
		int nid, idx=0;
		String[] answer = new String[tempAnswer.size()];
		for(Point p: tempAnswer) {
			if(p.y==0) {
				// enter
				answer[idx++] = unick.get(p.x)+"님이 들어왔습니다.";
			}else {
				answer[idx++] = unick.get(p.x)+"님이 나갔습니다.";
			}
		}
		
		System.out.println(answer.toString());
		
	}
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
