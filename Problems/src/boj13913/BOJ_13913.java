package boj13913;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_13913 {
	static int N, K, cur, next;
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
		bw.write(bfs(N) + "\n");
		bw.flush();
		bw.close();
	}

	static int bfs(int n) throws Exception {
		q = new ArrayDeque<Integer>();
		q.offer(n);
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur == K)
				return time[cur];

			next = cur - 1;
			if (!OOB(next) && time[next] == 0) {
				time[next] = time[cur] + 1;
				q.offer(next);
			}

			next = cur + 1;
			if (!OOB(next) && time[next] == 0) {
				time[next] = time[cur] + 1;
				q.offer(next);
			}

			next = cur * 2;
			if (!OOB(next) && time[next] == 0) {
				time[next] = time[cur] + 1;
				q.offer(next);
			}
		}
		return -1;
	}

	static boolean OOB(int x) {
		if (x < 0 || x > 100000)
			return true;
		return false;
	}
}
