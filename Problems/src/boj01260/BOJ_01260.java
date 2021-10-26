package boj01260;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_01260 {
	static int N, M, V, i, s, e, cur, next;
	static ArrayList<Integer>[] list;
	static boolean visited[];
	static BufferedWriter bw;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		V = Integer.parseInt(stk.nextToken());// start

		list = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (i = 1; i <= M; i++) {
			stk = new StringTokenizer(br.readLine());
			s = Integer.parseInt(stk.nextToken());
			e = Integer.parseInt(stk.nextToken());
			list[s].add(e);
			list[e].add(s);
		}
		for (i = 1; i <= N; i++) {
			Collections.sort(list[i]);
		}
		Arrays.fill(visited, false);
		dfs(V);
		bw.newLine();
		Arrays.fill(visited, false);
		bfs(V);
		bw.flush();
	}

	static int dfs(int here) throws Exception { // stack
		Deque<Integer> st = new ArrayDeque<Integer>();
		visited[here] = true;
		st.push(here);
		bw.write(here + " ");
		while (!st.isEmpty()) {
			boolean isFinished = true;
			cur = st.peek();
			for (Integer next : list[cur]) {
				if (!visited[next]) {
					visited[next] = true;
					bw.write(next + " ");
					st.push(next);
					isFinished = false;
					break; // *******
				}
			}
			if (isFinished)
				st.pop();
		}
		return -1;
	}

	static int bfs(int here) throws Exception { // queue
		Deque<Integer> q = new ArrayDeque<Integer>();
		visited[here] = true;
		q.offer(here);
		bw.write(here + " ");
		while (!q.isEmpty()) {
			cur = q.poll();
			for (int i = 0; i < list[cur].size(); i++) {
				next = list[cur].get(i);
				if (!visited[next]) {
					visited[next] = true;
					q.offer(next);
					bw.write(next + " ");
				}
			}
		}
		return -1;
	}
}
