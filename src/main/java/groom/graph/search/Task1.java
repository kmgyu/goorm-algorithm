package groom.graph.search;


import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Task1 {
    public static String[][] mat;
    public static Integer[][] visited;

    public static int counter = 0;

    public static int[][] moves = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };

    public static int N, M;

    public static int bfs(int x, int y) {
        counter += 1;
        Deque<Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[]{x, y});
        int size = 0;


        while (!q.isEmpty()) {
            Integer[] node = q.poll(); // pollFirst

            if (node[0] >= M || node[0] < 0 || node[1] >= N || node[1] < 0) continue;
            if (visited[node[0]][node[1]] > 0) continue;
            if (mat[node[0]][node[1]].equals(".")) continue;

            visited[node[0]][node[1]] = counter;
            size += 1;
            for (int[] move : moves) {
                q.add(new Integer[]{node[0] + move[0], node[1] + move[1]});
            }
        }
        return size;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        mat = new String[M][N];
        visited = new Integer[M][N];

        for(int i=0; i<M; i++){
            mat[i] =  br.readLine().split("");
            Arrays.fill(visited[i], 0);
        }

        int result_size = 0;
        for (int j=0; j<N; j++){
            for (int i=0; i<M; i++){
                if (mat[i][j].equals(".")) continue;
                if (visited[i][j] == 0) {
                    result_size = Math.max(result_size, bfs(i, j));
                }
            }
        }

        System.out.println(counter);
        System.out.println(result_size);
    }
}