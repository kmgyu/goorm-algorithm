package groom.graph.dijikstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Task2 {
    // S번 방에 C명의 사람이 있고, E번 방으로 이동하려 할 시 모든 사람이 이동
    // i번 통로는 나갈 수 있는 최대 사람 수가 k
    // k에 따라 C명의 사람이 모두 이동하는 비용이 달라진다.
    // 따라서 인원이 적을 수록 비용 절감에 유리하다.
    // 그냥 비용 계산 식이 달라진 것 뿐이다. 쉬운 문제!
    static int N, M, S, E, C;
    static List<int[]>[] edges; // node N과 edge M이 100_000 밖에 차이가 안남. 당연하게도 인접리스트 사용.

    public static long bfs(long start) {
        Queue<long[]> q = new PriorityQueue<>(Comparator.comparingLong((long[] a) -> a[1])); // = (int[] a1, int[] a2) -> a1[1] - a2[1]
        q.add(new long[]{start, 0});

        long[] visited = new long[N]; // 비용 저장.
        Arrays.fill(visited, -1);

        while(!q.isEmpty()) {
            long[] temp = q.poll();
            // index는 int로 형변환해야 한다! JVM이 int 범위에서 안전하게 동작하기 때문임. 애초에 long 타입 넣으면 안돌아간다.
            // https://claris.tistory.com/83
            int current = Math.toIntExact(temp[0]);
            long weight = temp[1];
            if(visited[current] < weight && visited[current] != -1) continue;

            visited[current] = weight;
            for(int[] edge : edges[current]) {
                long cost = C / edge[1] + (C%edge[1] > 0? 1:0);
                q.add(new long[]{edge[0], weight+cost});
            }

        }

        return visited[E];
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken())-1;
        E = Integer.parseInt(st.nextToken())-1;
        C = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());

            // 양방향. 둘다 추가해주자.
            edges[s].add(new int[]{e, w});
            edges[e].add(new int[]{s, w});
        }

        System.out.println(bfs(S));
    }
}
