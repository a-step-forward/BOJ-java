package boj05214;

//Contest > Croatian Open Competition in Informatics > COCI 2012/2013 > Contest #5 No.4
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_05214 {
	static int N, K, M, i, j, k;
	static int[] visit_S;
	static boolean[] visit_T;
	static ArrayList<Integer>[] list_S, list_T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken()); // station count
		K = Integer.parseInt(stk.nextToken()); // stations per tube
		M = Integer.parseInt(stk.nextToken()); // n. of tubes

		visit_S = new int[N + 1];
		visit_T = new boolean[M + 1];
		list_S = new ArrayList[N + 1]; // tubes in each station
		list_T = new ArrayList[M + 1]; // stations in each tube
		for (i = 0; i <= N; i++)
			list_S[i] = new ArrayList<>();
		for (i = 0; i <= M; i++)
			list_T[i] = new ArrayList<>();
		for (i = 1; i <= M; i++) {
			stk = new StringTokenizer(br.readLine());
			for (j = 0; j < K; j++) {
				k = Integer.parseInt(stk.nextToken());
				list_T[i].add(k);
				list_S[k].add(i);
			}
		}
		br.close();

		bw.write(bfs(1) + "\n");
		bw.flush();
		bw.close();
	}

	static int bfs(int here_S) {
		Deque<Integer> q = new ArrayDeque<Integer>();
		visit_S[here_S] = 1;
		q.offer(here_S);
		while (!q.isEmpty()) {
			here_S = q.poll();
			if (here_S == N)
				return visit_S[here_S];
			for (i = 0; i < list_S[here_S].size(); i++) {
				int here_T = list_S[here_S].get(i);
				if (!visit_T[here_T]) {
					visit_T[here_T] = true;
					for (j = 0; j < list_T[here_T].size(); j++) {
						int next_S = list_T[here_T].get(j);
						if (visit_S[next_S] == 0) {
							visit_S[next_S] = visit_S[here_S] + 1;
							q.offer(next_S);
						}
					}
				}
			}
		}
		return -1;
	}
}
