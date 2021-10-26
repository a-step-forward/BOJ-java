package boj13549;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_13549 {
	static int N, K, cur, zero, next;
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
		Arrays.fill(time, -1);
		bw.write(bfs(N) + "\n");
		bw.flush();
		bw.close();
	}

	static int bfs(int n) {
		q = new ArrayDeque<Integer>();
		q.offer(n);
		time[n] = 0;
		while (!q.isEmpty()) {
			cur = q.poll();
			if (cur == K)
				return time[cur];

			zero = cur * 2;
			while (zero <= 100_000 && time[zero] == -1) {
				time[zero] = time[cur];
				q.offer(zero);
				zero *= 2;
			}

			next = cur - 1;
			if (!OOB(next) && time[next] == -1) {
				time[next] = time[cur] + 1;
				q.offer(next);
			}

			next = cur + 1;
			if (!OOB(next) && time[next] == -1) {
				time[next] = time[cur] + 1;
				q.offer(next);
			}

		}
		return -1;
	}

	static boolean OOB(int x) {
		if (x < 0 || x > 100_000)
			return true;
		return false;
	}
}
