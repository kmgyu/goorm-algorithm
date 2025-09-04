package groom.graph.dijikstra;

import java.io.*;
import java.util.*;

public class Task3 {
    // bfs를 통해 각 노드별 신호 가중치를 찾고, 인접리스트 형태로 만들어 다익스트라로 만드는 방안을 생각했다.
    // 그러나 bfs할 때 방문 기록을 어떻게 찾는지가 관건이었는데, 해설에서는 8방향을 저장하도록 3차원으로 만들었다.

    static final int MAX = 100;
    static final int inf = Integer.MAX_VALUE;
    // 여덟 방향, 굳이 이렇게 꾸밀 필요가..?
    static final int[][] dir = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] S = new String[N];
        for (int i = 0; i < N; i++) {
            S[i] = br.readLine();
        }

        // 본사와 지사를 우선 찾는다.
        int start_i = 0, start_j = 0, end_i = 0, end_j = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (S[i].charAt(j) == 'S') {
                    start_i = i; start_j = j;
                } else if (S[i].charAt(j) == 'E') {
                    end_i = i; end_j = j;
                }
            }
        }

        /* 본사에서 지사로 가는 최단 시간을 찾아야 하며
        각 칸에서 나아갈 때의 시간이 전부 다르기 때문에 다익스트라가 적합하다. */
        int[][][] dist = new int[N][M][8];
        // dist(i, j, k): (i, j)에 k번째 방향으로 도착했을 때의 최단 거리
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                for (int k = 0; k < 8; k++){
                    dist[i][j][k] = inf;
                }
            }
        }
        // 시작점에서는 모든 방향에 대해 최단 거리가 전부 0이다.
        for (int k = 0; k < 8; k++){
            dist[start_i][start_j][k] = 0;
        }

        // 상남자 특) 인접리스트 그런거 안만들고 바로 탐색함
        // 거리, 행 번호, 열 번호, 방향
        PriorityQueue<Item> pq = new PriorityQueue<>();
        pq.offer(new Item(0, start_i, start_j, 0));

        while (!pq.isEmpty()) {
            Item it = pq.poll();
            int d = it.d, i = it.i, j = it.j, k = it.k;
            if (dist[i][j][k] < d) continue;

            char c = S[i].charAt(j);
            // (i, j)가 빈 곳일 경우
            // character 타입도 어쨌든 문자코드로 보면 순서 맞아서 이런 문법도 가능하다.
            if (c >= '1' && c <= '9') {
                int cost = c - '0';
                int ni = i + dir[k][0], nj = j + dir[k][1];
                if (ni >= 0 && ni < N && nj >= 0 && nj < M
                        && S[ni].charAt(nj) != '#'
                        && dist[ni][nj][k] > d + cost) {
                    dist[ni][nj][k] = d + cost;
                    pq.offer(new Item(d + cost, ni, nj, k));
                }
            }
            // (i, j)가 빈 곳이 아닐 경우
            else {
                for (int nk = 0; nk < 8; nk++){ // 여덟 방향 중 아무 방향으로 보낼 수 있다.
                    int ni = i + dir[nk][0], nj = j + dir[nk][1];
                    if (ni >= 0 && ni < N && nj >= 0 && nj < M
                            && S[ni].charAt(nj) != '#'
                            && dist[ni][nj][nk] > d + 1) {
                        dist[ni][nj][nk] = d + 1;
                        pq.offer(new Item(d + 1, ni, nj, nk));
                    }
                }
            }
        }

        int res = inf;
        for (int k = 0; k < 8; k++){
            res = Math.min(res, dist[end_i][end_j][k]);
        }

        if (res < inf) {
            System.out.print(res);
        } else {
            System.out.print(-1);
        }
    }

    // 여기서는 priority queue를 조금 더 편하게 사용하기 위해 클래스를 만들어줬다.
    // Comparable 클래스 기억해두자.
    static class Item implements Comparable<Item> {
        int d, i, j, k;
        Item(int d, int i, int j, int k){
            this.d = d; this.i = i; this.j = j; this.k = k;
        }
        @Override
        public int compareTo(Item o){
            return Integer.compare(this.d, o.d);
        }
    }
}
