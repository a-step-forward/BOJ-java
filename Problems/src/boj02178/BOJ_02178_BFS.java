package boj02178;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_02178_BFS {

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] map;
	static int[][] cost;
	static boolean[][] visited;
	static int N, M, i, j, steps;

	static class Cell {
		int x;
		int y;
		int c;

		Cell(int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}

	static void bfs(int x, int y, int c) {
		int curX, curY, curC, nextX, nextY, nextC;
		Deque<Cell> q = new ArrayDeque<Cell>();
		q.add(new Cell(x, y, c));
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Cell cur = q.poll();
			curX = cur.x;
			curY = cur.y;
			curC = cur.c;
			for (i = 0; i < 4; i++) {
				nextX = curX + dx[i];
				nextY = curY + dy[i];
				nextC = curC + 1;
				if (!OOB(nextX, nextY)) {
					if (map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
						q.add(new Cell(nextX, nextY, nextC));
						visited[nextX][nextY] = true;
						cost[nextX][nextY] = nextC;
					}
				}
			}
		}
	}

	static boolean OOB(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M)
			return true;
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		cost = new int[N][M];

		for (i = 0; i < N; i++) {
			Arrays.fill(cost[i], -1);
		}

		for (i = 0; i < N; i++) {
			String line = br.readLine();
			for (j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		/*
				for (i = 0; i < N; i++) {
					for (j = 0; j < M; j++) {
						System.out.printf("%d ", map[i][j]);
					}
					System.out.printf("\n");
				}
		*/
		bfs(0, 0, 1);
		bw.write(cost[N - 1][M - 1] + "\n");
		//System.out.println(Arrays.deepToString(cost));
		bw.flush();
		bw.close();
		br.close();
	}
}
