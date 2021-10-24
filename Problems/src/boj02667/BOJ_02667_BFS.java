package boj02667;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BOJ_02667_BFS {
	static int[] dX = { 0, 0, 1, -1 }; // 이동방향 X
	static int[] dY = { 1, -1, 0, 0 }; // 이동방향 Y
	static int N, cnt;
	static int[] apt; //단지별 아파트 수 배열
	static boolean[][] visited;
	static int[][] map;

	static class Cell {
		int x, y;

		Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void bfs(int x, int y) {
		Deque<Cell> q = new ArrayDeque<Cell>();
		q.add(new Cell(x, y));
		visited[x][y] = true;
		apt[cnt]++;

		while (!q.isEmpty()) {
			Cell now = q.poll();
			int curX = now.x;
			int curY = now.y;
			for (int i = 0; i < 4; i++) {
				int nextX = curX + dX[i];
				int nextY = curY + dY[i];
				if (!OOB(nextX, nextY)) {
					if (map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
						q.add(new Cell(nextX, nextY));
						visited[nextX][nextY] = true;
						apt[cnt]++;
					}
				}
			}
		}
	}

	static boolean OOB(int nX, int nY) {// out of bounds
		if (nX < 0 || nY < 0 || nX >= N || nY >= N)
			return true;
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		apt = new int[N * N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			//System.out.println(input);
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		// 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					cnt++;
					bfs(i, j);
				}
			}
		}
		bw.write(cnt + "\n");

		Arrays.sort(apt);
		for (int i = 0; i < apt.length; i++) {
			if (apt[i] != 0)
				bw.write(apt[i] + "\n");
		}
		bw.flush();
		bw.close();
	}

}
