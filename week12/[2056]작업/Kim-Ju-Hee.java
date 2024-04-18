package april3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN2056_작업_김주희 {
	static int N;
	static Task[] task;
	static boolean[] doneTask;
	
	static class Task{
		int time, priorWorks[];
		
		public Task(int time, int n) {
			this.time = time;
			priorWorks = new int[n];
		}
		
	}
	
	static class RunningTask implements Comparable<RunningTask>{
		int doneTime, idx;
		
		public RunningTask(int doneTime, int idx) {
			this.doneTime = doneTime;
			this.idx = idx;
		}
		
		@Override
		public int compareTo(RunningTask o) {
			return this.doneTime - o.doneTime;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		task = new Task[N+1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int pNum = Integer.parseInt(st.nextToken());
			
			task[i] = new Task(t, pNum);
			for (int j = 0; j < pNum; j++) {
				task[i].priorWorks[j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<RunningTask> runningQueue = new PriorityQueue<>();
		// 선행 task 0개인거 다 넣어서 초기화
		for (int i = 1; i <= N; i++) {
			if(task[i].priorWorks.length == 0)
				runningQueue.add(new RunningTask(task[i].time, i));
		}
		
		int time = 0;
		doneTask = new boolean[N+1];
		
		while(!runningQueue.isEmpty()) {
			if(doneTask[runningQueue.peek().idx]) { // 이미 수행한 task면 넘어감
				runningQueue.poll();
				continue;
			}
			
			RunningTask cur = runningQueue.poll();
			time = cur.doneTime;
			doneTask[cur.idx] = true;
			
			// 선행 task 다 수행되었으면 runningQueue에 넣음
			for (int i = 1; i <= N; i++) {
				if(doneTask[i]) continue;
				
				boolean flag = true;
				for (int j : task[i].priorWorks) {
					if(!doneTask[j]) {
						flag = false;
						break;
					}
				}
				
				if(flag)
					runningQueue.add(new RunningTask(time + task[i].time, i));
			}

		}
		
		System.out.println(time);

	}

}
