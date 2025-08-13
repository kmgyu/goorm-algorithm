package groom.graph.search;

import java.io.*;
import java.util.*;

class Task2 {
    public static String[][] mat;
    public static Integer[][] visited;

    public static int[][] moves = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static int N, M;

    public static int bfs(int x, int y) {
        Deque<Integer[]> q = new ArrayDeque<>();
        q.add(new Integer[]{x, y});

        int ans = -1;
        while (!q.isEmpty()) {
            Integer[] node = q.poll(); // pollFirst

            if (mat[node[0]][node[1]].equals("&")) {
                ans = visited[node[0]][node[1]]-1;
                break;
            }

            for (int[] move : moves) {
                int mx = node[0] + move[0];
                int my = node[1] + move[1];

                if (mx >= N || mx < 0 || my >= M || my < 0) continue;
                if (visited[mx][my] > 0) continue;
                if (mat[mx][my].equals("#")) continue;

                q.add(new Integer[]{mx, my});
                visited[mx][my] = visited[node[0]][node[1]]+1;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        mat = new String[N][M];
        visited = new Integer[N][M];

        for(int i=0; i<N; i++){
            mat[i] =  br.readLine().split("");
            Arrays.fill(visited[i], 0);
        }

        int ans = -1;
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                if (mat[i][j].equals("@")) {
                    ans = bfs(i, j);
                }
            }
        }

        System.out.println(ans);
    }
}

// 이건 작동하는데, 왜 내 코드는 작동을 안할까???요???????
// 공간복잡도 줄인다고 visited로 압축한거랑 무슨 상관인건지 하나도 모르겠네ㅔㅔㅔㅔ
//import java.io.*;
//import java.util.*;
//
//public class Main {
//
//    static final int MAX = 1000;
//    static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 방향
//    static int R, C;
//    static int[][] dist = new int[MAX][MAX];
//    static boolean[][] visited = new boolean[MAX][MAX];
//    static String[] S = new String[MAX];
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        // R과 C 입력 받기
//        String[] input = br.readLine().split(" ");
//        R = Integer.parseInt(input[0]);
//        C = Integer.parseInt(input[1]);
//
//        // 지도 정보 입력 받기
//        for (int i = 0; i < R; i++) {
//            S[i] = br.readLine();
//        }
//
//        // BFS를 위한 덱 선언
//        Deque<int[]> dq = new ArrayDeque<>();
//
//        // 불의 위치를 먼저 모두 덱에 담는다.
//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < C; j++) {
//                if (S[i].charAt(j) == '@') { // 불인 칸
//                    dq.add(new int[]{i, j});
//                    dist[i][j] = 0;
//                    visited[i][j] = true;
//                }
//            }
//        }
//
//        // BFS 시작
//        while (!dq.isEmpty()) {
//            int[] current = dq.pollFirst(); // 덱에서 현재 위치 꺼냄
//            int i = current[0];
//            int j = current[1];
//
//            // 사방으로 확장
//            for (int[] d : dir) {
//                int ni = i + d[0];
//                int nj = j + d[1];
//
//                // 범위를 벗어나지 않고, 방문하지 않은 빈 칸만 처리
//                if (ni >= 0 && ni < R && nj >= 0 && nj < C && S[ni].charAt(nj) != '#' && !visited[ni][nj]) {
//                    visited[ni][nj] = true;
//                    dist[ni][nj] = dist[i][j] + 1;
//                    dq.addLast(new int[]{ni, nj}); // 다음 위치를 덱에 추가
//                }
//            }
//        }
//
//        // 구름이 위치에서 결과 출력
//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < C; j++) {
//                if (S[i].charAt(j) == '&') {
//                    // 구름이 있는 칸에 불이 도달하지 못했을 경우
//                    if (!visited[i][j]) {
//                        System.out.println(-1);
//                    } else {
//                        // 불이 도달한 경우, 거리 -1 을 출력
//                        System.out.println(dist[i][j] - 1);
//                    }
//                    return; // 문제에서 구름이는 한 마리이므로 결과 출력 후 종료
//                }
//            }
//        }
//    }
//}