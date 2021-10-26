package boj01697;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_01697 {
	static int N, K, cur, time;
	static Deque<Position> q;
	static BufferedWriter bw;
	static int[] cost;

	static class Position {
		int n;// position number
		int t;// time

		Position(int n, int t) {
			this.n = n;
			this.t = t;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		bfs(N);
		cost = new int[N];
		bw.flush();
		bw.close();
	}

	static void bfs(int n) throws Exception {
		q = new ArrayDeque<Position>();
		q.offer(new Position(n, 0));
		while (!q.isEmpty()) {
			Position p = q.poll();
			cur = p.n;
			time = p.t;
			if (cur == K) {
				bw.write(time + "\n");
				break;
			}
			makeNext(cur, time);
		}
	}

	static void makeNext(int x, int t) {
		q.offer(new Position(x + 1, t + 1));
		cost[x + 1]++;
		q.offer(new Position(x - 1, t + 1));
		cost[x + 1]++;
		q.offer(new Position(x * 2, t + 1));
		cost[x + 1]++;
	}
}
