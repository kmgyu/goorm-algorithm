package groom.graph.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Task2 {
    // 프림 알고리즘. 크루스칼로 풀려고 하다 프림 생각 났다.
    // 해설 확인하니 우선순위 큐로 간단하게 구현했다.
    static int N, M, K;
    static boolean[] nodes;
    static List<int[]>[] edges;

    public static void main(String[] args) throws Exception {
        // IO logic
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        long result = 0;

        nodes = new boolean[N+1];
        Arrays.fill(nodes, false);
        edges = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[a].add(new int[] {b, w});
            edges[b].add(new int[] {a, w});
            result += w;
        }

        int cnt = 0;
        // solve logic

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[0]));
        pq.add(new long[]{0, 1}); // 1번 구역부터 시작해야 한다.
        int ct = 0; // 현재 최소 스패닝 트리의 크기
        long res = 0; // 최소 스패닝 트리를 이루는 간선들의 가중치의 합

        while (ct < K) { // 크기가 K가 될 때까지 반복해야 한다.
            long[] current = pq.poll();
            long d = current[0]; // 간선의 가중치
            int a = (int) current[1]; // 정점

            // 방문하지 않은 정점으로 나아가는 간선이 뽑혔다면 추가해준다.
            if (nodes[a]) {
                continue;
            }
            nodes[a] = true;
            ++ct;
            res += d;

            // 그 정점에서 다시 나아가는 간선을 우선순위 큐에 넣는다.
            for (int[] edge : edges[a]) {
                int b = edge[0];
                int c = edge[1];
                if (!nodes[b]) {
                    pq.add(new long[]{c, b});
                }
            }
        }

        // 최소한으로 드는 소비 전력을 총 소비 전력에서 빼주면 된다.
        System.out.println(result - res);
    }
}
