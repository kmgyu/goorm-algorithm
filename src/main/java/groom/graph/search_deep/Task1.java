package groom.graph.search_deep;

import java.io.*;
import java.util.*;

public class Task1 {
    // 진자 몰라서 해설 봤다. 트리 + 그래프 진자 개어렵네...
    // 트리 지름구하기 문제가 되는 이유 : 그래프는 정렬되지 않은 트리라고 볼 수 있음. n-1 개의 무방향 엣지를 가지나, 단순 경로라는 조건이 있다. 순환이 왜 없는 건지는 모르겠다. 진짜 모르겠음...
    // 문제 조건 자체가 트리라고 함.
    // 엣지가 n-1, 임의 두 점에 대해서 단순 경로만 존재. 즉, 반드시 경로가 존재한다. = 모든 노드는 연결되어 있다. 아오.
    // 특정 점 i에서 가장 먼 정점을 찾는다면 일단 해당 정점이 i에서 가장 먼 정점임. 그렇다면 이게 트리 지름일 수 있다.
    // 루트에서 가장 먼 점이 지름 안에 없다는 게 모순이 될 수 있기 때문!
    // 주석으로 표현하기 너무 긴 것 같다.
    // https://blogshine.tistory.com/111
    static int N;
    static int[] dist;
    static List<Integer>[] G;

    // 스택을 이용한 DFS 탐색
    static void dfs(int start) {
        Arrays.fill(dist, 0); // 거리 배열 초기화
        Stack<int[]> stack = new Stack<>(); // DFS에 사용할 스택 (노드, 부모)
        stack.push(new int[]{start, -1}); // 시작점과 부모 (-1은 부모가 없음을 의미)

        while (!stack.isEmpty()) {
            int[] node = stack.pop();
            int i = node[0];
            int p = node[1];

            // 현재 노드와 연결된 다른 노드들 을 탐색
            for (int j : G[i]) {
                if (j != p) { // 부모로 다시 돌아가는 경우를 제외
                    dist[j] = dist[i] + 1;
                    stack.push(new int[]{j, i}); // j번 노드를 방문하고 i는 부모로 저장
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N 입력 처리
        N = Integer.parseInt(br.readLine());

        dist = new int[N];
        G = new ArrayList[N]; // 크기 지정을 미리 해주면 스케일업하지 않아서 더 싸게 먹힌다..! maxn이 주어질 경우 maxn으로도 하던데, n 입력받아서 최적화하면 속도 아주 조금 빨라지긴 할듯?

        // 그래프 리스트 초기화
        for (int i = 0; i < N; i++) {
            G[i] = new ArrayList<>();
        }

        // 그래프 간선 입력 처리
        for (int i = 1; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int u = Integer.parseInt(line[0]) - 1;
            int v = Integer.parseInt(line[1]) - 1;
            G[u].add(v);
            G[v].add(u);
        }

        // 첫 번째 DFS로 임의의 정점에서 가장 먼 정점을 찾는다.
        dfs(0);
        int u = 0;
        for (int i = 1; i < N; i++) {
            if (dist[u] < dist[i]) {
                u = i;
            }
        }

        // u에서 가장 먼 정점을 찾는 두 번째 DFS
        dfs(u);
        int v = 0;
        for (int i = 1; i < N; i++) {
            if (dist[v] < dist[i]) {
                v = i;
            }
        }

        // 트리의 지름 + 1 출력
        System.out.print(dist[v] + 1);
    }
}