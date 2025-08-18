package groom.graph.search;

import java.io.*;
import java.util.*;

public class Task5 {
    public static int N, M, X, Y, Z;
    public static String[][] mat;

    public static int[][] moves = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public static int[][] visited;

    public static int bfs(int x, int y, int end_x, int end_y){
        Deque<int[]> q = new ArrayDeque<>();
        for (int[] rows : visited) {
            Arrays.fill(rows, -1);
        }
        visited[x][y]=0;

        q.add(new int[]{x, y});
        while (!q.isEmpty()){
            int[] cur = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];

            for(int[] move : moves){
                int next_x = cur_x + move[0];
                int next_y = cur_y + move[1];
                if ((next_x >= N || next_x < 0 || next_y >= N || next_y < 0) || visited[next_x][next_y] > -1 || mat[next_x][next_y].equals("1")) continue;
                visited[next_x][next_y] = visited[cur_x][cur_y] + 1;
                q.add(new int[]{next_x, next_y});
            }
        }

        //    for (int[] rows: visited) {
        //        System.out.println(Arrays.toString(rows));
        //    }
        // System.out.println();
        return visited[end_x][end_y];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        Z = Integer.parseInt(st.nextToken());

        visited = new int[N][N];
        mat = new String[N][N];

        for(String[] rows: mat) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                rows[i] = st.nextToken();
            }
        }

        int ans = 0;
        int temp_x=0, temp_y=0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken())-1;

            int move = bfs(b, a, d, c);
            int cost = X;
            if (move > 5) cost += (move-5) * Y;
            cost -= Z*move;

            if (i > 0) {
                cost -= bfs(temp_x, temp_y, b, a)*Z;
            }
            temp_x=d;
            temp_y=c;
            ans += cost;
        }
        System.out.println(ans);
    }
}
