package groom.graph.search;

import java.io.*;
import java.util.*;

class Task2 {
    static int N, M;
    static char[][] g;
    static int[][] dist; // 불 도달 시간, -1은 미도달
    static final int[][] MOVES = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().trim().split("\\s+");
        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);

        g = new char[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);

        Deque<int[]> q = new ArrayDeque<>();
        int sx = -1, sy = -1;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                g[i][j] = c;
                if (c == '@') {
                    q.add(new int[]{i, j});
                    dist[i][j] = 0; // 시작점 0초
                } else if (c == '&') {
                    sx = i; sy = j;
                }
            }
        }

        // 불 전파 멀티소스 BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            for (int[] mv : MOVES) {
                int nx = x + mv[0], ny = y + mv[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (g[nx][ny] == '#') continue;        // 벽은 번지지 않음
                if (dist[nx][ny] != -1) continue;      // 이미 도달 시간 확정
                dist[nx][ny] = dist[x][y] + 1;
                q.add(new int[]{nx, ny});
            }
        }

        int ans = Integer.MAX_VALUE;

        // 자기 칸
        if (dist[sx][sy] != -1) ans = Math.min(ans, dist[sx][sy]);

        // 인접 4칸
        for (int[] mv : MOVES) {
            int nx = sx + mv[0], ny = sy + mv[1];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (g[nx][ny] == '#') continue;
            if (dist[nx][ny] != -1) ans = Math.min(ans, dist[nx][ny]);
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
