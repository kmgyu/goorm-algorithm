package groom.graph.dijikstra;

import java.io.*;
import java.util.*;

public class Task1 {
    // 다음 문제에서도 이거 재활용하자.
    static int N, M, S;
    static List<int[]>[] edges; // 노드**2가 더 크니까 인접 리스트 형식.

    public static long bfs(long start) {
        Queue<long[]> q = new PriorityQueue<>(Comparator.comparingLong((long[] a) -> a[1])); // = (int[] a1, int[] a2) -> a1[1] - a2[1]
        q.add(new long[]{start, 0});

        long[] visited = new long[N];
        Arrays.fill(visited, -1);

        while(!q.isEmpty()) {
            long[] temp = q.poll();
            int current = Math.toIntExact(temp[0]); // index는 int로 형변환?
            long weight = temp[1];
            if(visited[current] < weight && visited[current] != -1) continue;

            visited[current] = weight;
            for(int[] edge : edges[current]) {
                q.add(new long[]{edge[0], edge[1]+weight});
            }

        }

        return Arrays.stream(visited).sum();
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken())-1;

        edges = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());

            edges[s].add(new int[]{e, w});
        }

        System.out.println(bfs(S));
    }
}
