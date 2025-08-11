package groom.timecomplexity;

import java.io.*;
import java.util.*;

class Task3 {
    public static int solve(int[] location, String[][] movement, int N) {
        boolean visited[][] = new boolean[N][N];
        int x = location[0];int y=location[1];
        int score = 0;
        while (visited[x][y] != true) {
            String moving = movement[x][y];
            int count = Integer.parseInt(moving.substring(0, moving.length() - 1));
            String move = moving.substring(moving.length()-1);

            int l = move.equals("L") ? -1:0;
            int r = move.equals("R") ? 1:0;
            int u = move.equals("U") ? -1:0;
            int d = move.equals("D") ? 1:0;

            for (int i = 0; i < count; i++) {
                if (visited[x][y]) break;
                visited[x][y] = true;
                score += 1;
                x+=u+d;y+=l+r;
                x=(x+N)%N;y=(y+N)%N;
            }
        }
        if (!visited[x][y]) score += 1;
        return score;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // String input = br.readLine();
        int N = Integer.parseInt(br.readLine());
        int g[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); //goorm's current location
        int p[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); //player's current location

        g[0]-=1;g[1]-=1;p[0]-=1;p[1]-=1;

        String[][] mat = new String[N][N];

        for (int i = 0; i<N; i++) {
            String[] row = br.readLine().split(" ");
            mat[i] = row;
        }

        int g_cnt = 0;
        int p_cnt = 0;
        // goorm turn
        g_cnt = solve(g, mat, N);
        p_cnt = solve(p, mat, N);

        System.out.println((g_cnt > p_cnt ? "goorm " : "player ") + Math.max(g_cnt, p_cnt));
    }
}