package boj02644;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_02644 {
	static int N, S, E, M, i, parent, child, start, end;
	static int[][] map;
	static int[] count;
	static boolean[] visited;
	static BufferedReader br;
	static BufferedWriter bw;
	static Deque<Integer> q;

	public static void main(String[] args) throws Exception {
		init();
		bfs(S);
		finishing();
	}

	static void finishing() throws Exception {
		bw.write(count[E] + "\n");
		bw.flush();
		bw.close();
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		S = Integer.parseInt(stk.nextToken());
		E = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		count = new int[N + 1];
		visited = new boolean[N + 1];
		Arrays.fill(count, -1);
		for (i = 1; i <= M; i++) {
			stk = new StringTokenizer(br.readLine());
			parent = Integer.parseInt(stk.nextToken());
			child = Integer.parseInt(stk.nextToken());
			map[parent][child] = 1;
			map[child][parent] = 1;
		}
	}

	static void bfs(int s) {
		q = new ArrayDeque<Integer>();
		q.offer(s);
		count[s] = 0;
		visited[s] = true;
		while (!q.isEmpty()) {
			int start = q.poll();
			if (start == E)
				break;
			for (int end = 1; end <= N; end++) {
				if (!visited[end] && map[start][end] == 1) {
					q.offer(end);
					visited[end] = true;
					count[end] = count[start] + 1;
				}
			}
		}
	}
}
