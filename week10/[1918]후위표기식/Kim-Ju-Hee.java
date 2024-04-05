package april1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class JUN1918_후위표기식 {
	
	static class Oper{
		boolean isAlphabet, isOper, priority;
		String value;
		
		public Oper(boolean isAlphabet, boolean isOper, boolean priority, String value) {
			this.isAlphabet = isAlphabet;
			this.isOper = isOper;
			this.priority = priority;
			this.value = value;
		}

	}

	private static Oper cal(Deque<Oper> target) {
		Deque<Oper> result = new ArrayDeque<>();
		
		// 괄호 먼저
		while (!target.isEmpty()) {
			Oper cur = target.pollFirst();
			
			if(!cur.isOper && cur.priority) { // 여는 괄호이면
				Deque<Oper> temp = new ArrayDeque<>(); // 재귀적으로 계산 돌릴 값
				int cnt = 0;
				while(!target.isEmpty()) {
					Oper tempO = target.pollFirst();
					
					if(tempO.isAlphabet || tempO.isOper) temp.addLast(tempO);
					else if(tempO.priority) {
						temp.addLast(tempO);
						cnt++;
					}else { 
						if(cnt == 0) {//닫는 괄호이고 안에 여는 괄호가 다 닫혔을 때
							target.addFirst(cal(temp)); // 계산 돌려서 뒤에 oper넣음
							break;
						}
						else {
							temp.addLast(tempO);
							cnt--;
						}
					}
				}
			}else{ // 나머지는 그냥 넣음
				result.addLast(cur);
			}
		}
		
		// 곱셈, 나눗셈
		while (!result.isEmpty()) {
			Oper cur = result.pollFirst();
			
			if(cur.isAlphabet) target.addLast(cur);
			else if(cur.isOper && cur.priority) { // 곱셈, 나눗셈
				String newOper = "";
				newOper += target.pollLast().value;
				newOper += result.pollFirst().value;
				newOper += cur.value;
				target.addLast(new Oper(true, false, false, newOper));
			}else target.addLast(cur);
		}
		
		// 덧셈, 뺄셈
		while (!target.isEmpty()) {
			Oper cur = target.pollFirst();
			
			if(cur.isAlphabet) result.addLast(cur);
			else if(cur.isOper) { // 곱셈, 나눗셈
				String newOper = "";
				newOper += result.pollLast().value;
				newOper += target.pollFirst().value;
				newOper += cur.value;
				result.addLast(new Oper(true, false, false, newOper));
			}else result.addLast(cur);
		}
		
		return result.pollFirst();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		Deque<Oper> dq = new ArrayDeque<>();
		
		for (int i = 0; i < input.length(); i++) {
			char cur = input.charAt(i);
			
			if(cur >= 'A' && cur <= 'Z') { // isAlphabet, isOper, priority
				dq.addLast(new Oper(true, false, false, cur+""));
			}else if(cur == '(') {
				dq.addLast(new Oper(false, false, true, cur+"")); // 여는 괄호에 priority줌
			}else if(cur == ')') {
				dq.addLast(new Oper(false, false, false, cur+""));
			}else if(cur == '*' || cur == '/') {
				dq.addLast(new Oper(false, true, true, cur+""));
			}else {
				dq.addLast(new Oper(false, true, false, cur+""));
			}
		}

		System.out.println(cal(dq).value);
		
	}

}
