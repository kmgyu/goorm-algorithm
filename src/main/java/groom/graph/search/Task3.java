package groom.graph.search;

import java.io.*;
import java.util.*;

class Task3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

        Map<Integer, Queue<Integer>> edges = new HashMap<>();

        int[] visited = new int[N+1];
        Arrays.fill(visited, 0);

        for (int i=1; i<N+1; i++) {
            edges.put(i, new PriorityQueue<>());
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
            edges.get(s).add(e);
            edges.get(e).add(s);
        }

        visited[K] = 1;
        int current = K;
        while (true) {
            Queue<Integer> q = edges.get(current);
            if (q.isEmpty()) break;
            int next = q.peek();
            while (!q.isEmpty() && visited[q.peek()] > 0) {
                next = q.poll();
            }
            if (visited[next] == 0) {
                visited[next] = visited[current]+1;
                current = next;
            }

        }

        System.out.println(visited[current] +" "+ current);
    }
}