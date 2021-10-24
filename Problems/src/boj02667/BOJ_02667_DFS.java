package boj02667;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BOJ_02667_DFS {
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

	static void dfs(int x, int y) {
		visited[x][y] = true;
		apt[cnt]++;
		for (int i = 0; i < 4; i++) {
			int nX = x + dX[i];
			int nY = y + dY[i];
			if (!OOB(nX, nY)) {
				if (map[nX][nY] == 1 && !visited[nX][nY]) {
					dfs(nX, nY);
				}
			}
		}
	}

	static void dfsStack(int x, int y) {
		Deque<Cell> st = new ArrayDeque<Cell>();
		st.push(new Cell(x, y));
		visited[x][y] = true;
		while (!st.isEmpty()) {
			int curX = st.peek().x;
			int curY = st.peek().y;
			visited[curX][curY] = true;
			boolean isFinished = true;
			for (int i = 0; i < 4; i++) {
				int nextX = curX + dX[i];
				int nextY = curY + dY[i];
				if (!OOB(nextX, nextY)) {
					if (map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
						st.push(new Cell(nextX, nextY));
						isFinished = false;
						break;
					}
				}
			}
			if (isFinished) {
				apt[cnt]++;
				st.pop();
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
					//dfs(i, j);
					dfsStack(i, j);
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
