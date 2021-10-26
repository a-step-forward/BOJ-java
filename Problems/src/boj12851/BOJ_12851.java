package boj12851;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_12851 {
	static int N, K, cur, next;
	static int routes = 0;
	static int ans = Integer.MAX_VALUE;
	static Deque<Integer> q;
	static BufferedWriter bw;
	static int[] time;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		time = new int[100_001];
		bfs(N);
		bw.write(ans + "\n");
		bw.write(routes + "\n");
		bw.flush();
		bw.close();
	}

	static void bfs(int n) {
		q = new ArrayDeque<Integer>();
		q.offer(n);
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (ans < time[cur])
				return;
			if (cur == K && time[cur] <= ans) {
				ans = time[cur];
				routes++;
			}
			next = cur - 1;
			if (!OOB(next) && (time[next] == 0 || time[next] >= time[cur] + 1)) {
				time[next] = time[cur] + 1;
				q.offer(next);
			}

			next = cur + 1;
			if (!OOB(next) && (time[next] == 0 || time[next] >= time[cur] + 1)) {
				time[next] = time[cur] + 1;
				q.offer(next);
			}

			next = cur * 2;
			if (!OOB(next) && (time[next] == 0 || time[next] >= time[cur] + 1)) {
				time[next] = time[cur];
				q.offer(next);
			}
		}
	}

	static boolean OOB(int x) {
		if (x < 0 || x > 100000)
			return true;
		return false;
	}
}
