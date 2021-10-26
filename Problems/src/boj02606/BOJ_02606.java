package boj02606;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_02606 {
	static int N, M, i, s, e, ans;
	static int[][] map;
	static boolean[] visited;
	static BufferedReader br;
	static StringTokenizer stk;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		init();
		bfs(1);
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}

	static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		for (i = 0; i <= N; i++) {
			Arrays.fill(map[i], -1);
		}

		for (i = 1; i <= M; i++) {
			stk = new StringTokenizer(br.readLine());
			s = Integer.parseInt(stk.nextToken());
			e = Integer.parseInt(stk.nextToken());
			map[s][e] = 1;
			map[e][s] = 1;
		}
	}

	static int bfs(int s) {
		Deque<Integer> q = new ArrayDeque<Integer>();
		q.offer(s);
		visited[s] = true;
		while (!q.isEmpty()) {
			int start = q.poll();
			for (int end = 1; end <= N; end++) {
				if (map[start][end] == 1 && !visited[end]) {
					visited[end] = true;
					q.offer(end);
					ans++;
				}
			}
		}
		return ans;
	}
}
