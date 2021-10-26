package boj07569;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_07569 {
	static int N, M, H, cells, read, vacant, tAlready, tRipened, ans, days;
	static int nX, nY, nZ, nD; // attributes of the next cell
	static int[][][] box;
	static boolean[][][] checked;
	static int[] dx = { 0, 0, 0, 0, 1, -1 };
	static int[] dy = { 0, 0, 1, -1, 0, 0 };
	static int[] dz = { 1, -1, 0, 0, 0, 0 };
	static Deque<Cell> q;

	static class Cell {
		int x, y, z, d;

		Cell(int x, int y, int z, int d) {
			this.x = x;//N
			this.y = y;//M
			this.z = z;//H
			this.d = d;//day
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());
		box = new int[H][M][N];
		checked = new boolean[H][M][N];
		cells = N * M * H;
		q = new ArrayDeque<Cell>();
		for (int h = 0; h < H; h++) {
			for (int m = 0; m < M; m++) {
				stk = new StringTokenizer(br.readLine());
				for (int n = 0; n < N; n++) {
					read = Integer.parseInt(stk.nextToken());
					box[h][m][n] = read;
					if (read == 1) {
						tAlready++; // ripened already
						q.offer(new Cell(n, m, h, 0));
					} else if (read == -1)
						vacant++; // vacant cell
				}
			}
		}

		if ((cells - vacant) == tAlready) {
			ans = 0; // all tomatoes are ripened when save
		} else {
			bfs();
			if (cells == (tRipened + vacant + tAlready))
				ans = days;
			else
				ans = -1; // some tomatoes are not ripened
		}
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Cell cur = q.poll();
			checked[cur.z][cur.y][cur.x] = true;
			days = cur.d;
			for (int i = 0; i < 6; i++) {
				nX = cur.x + dx[i];
				nY = cur.y + dy[i];
				nZ = cur.z + dz[i];
				nD = cur.d + 1;
				if (!OOB(nX, nY, nZ)) {
					if (!checked[nZ][nY][nX] && box[nZ][nY][nX] == 0) {
						Cell next = new Cell(nX, nY, nZ, nD);
						box[nZ][nY][nX] = box[cur.z][cur.y][cur.x] + 1;
						checked[nZ][nY][nX] = true;
						q.offer(next);
						tRipened++;
					}
				}
			}
		}
	}

	static boolean OOB(int x, int y, int z) {
		if (x < 0 || y < 0 || z < 0 || x >= N || y >= M || z >= H)
			return true;
		return false;
	}
}
