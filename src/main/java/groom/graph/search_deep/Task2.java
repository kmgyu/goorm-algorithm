package groom.graph.search_deep;

import java.io.*;
import java.util.*;

public class Task2 {
    // 이것도 진자 몰라서 해설봤다. Task1과 마찬가지로, 트리의 지름을 활용한다.
    // 주어진 그래프는 순환이 존재할 수 없는 구조이다. 이미 존재하는 노드와 존재하지 않던 노드를 잇기 때문에, 트리 구조를 유지할 수 있다.
    // 이제 트리의 지름을 구하고 해당 경로를 찾아 최장 거리중 최단 거리를 계속해서 갱신해주는 문제이다.
    // 뇌가 녹는다앗

    static final int MAXN = 20001;
    static final int inf = Integer.MAX_VALUE;
    static int N;
    static int[] dist = new int[MAXN];
    static int[] pa = new int[MAXN];
    static List<int[]>[] G = new ArrayList[MAXN];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 주어지는 그래프는 반드시 트리가 된다.
        // 모든 정점까지의 거리의 최댓값이 최소화되는 정점은 트리의 지름 상에 있다.
        N = Integer.parseInt(br.readLine());

        // 그래프 인접 리스트 초기화
        for (int i = 1; i <= N; i++) {
            G[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int Ai = Integer.parseInt(st.nextToken());
            int Li = Integer.parseInt(st.nextToken());
            G[i].add(new int[]{Ai, Li});
            G[Ai].add(new int[]{i, Li});
        }

        // 트리의 지름을 이루는 두 정점 u, v를 구한다.
        Arrays.fill(dist, 0);
        dfs1(1);

        int u = 1;
        for (int i = 2; i <= N; i++) {
            if (dist[u] < dist[i]) {
                u = i;
            }
        }

        // 거리를 계산해준다.
        Arrays.fill(dist, 0);
        dfs1(u);

        int v = 1;
        for (int i = 2; i <= N; i++) {
            if (dist[v] < dist[i]) {
                v = i;
            }
        }

        // u를 루트로 한 트리의 정점마다 부모를 구한다.
        Arrays.fill(pa, 0);
        dfs2(u);

        // v에서 시작해 u로 거슬러 올라가면서, u까지의 거리와 v까지의 거리를 구해 정답을 갱신해나간다.
        int res = inf;
        for (int i = v; i != 0; i = pa[i]) {
            res = Math.min(res, Math.max(dist[i], dist[v] - dist[i]));
        }

        // 정답 출력
        System.out.println(res);
    }

    // DFS로 거리 계산 (스택을 사용한 비재귀적 DFS)
    static void dfs1(int start) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{start, 0}); // {현재 노드, 부모 노드}
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int i = current[0], p = current[1];
            for (int[] neighbor : G[i]) {
                int j = neighbor[0], k = neighbor[1];
                if (j == p) continue;
                dist[j] = dist[i] + k;
                stack.push(new int[]{j, i});
            }
        }
    }

    // DFS로 부모 정보 계산 (스택을 사용한 비재귀적 DFS)
    static void dfs2(int start) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{start, 0}); // {현재 노드, 부모 노드}
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int i = current[0], p = current[1];
            for (int[] neighbor : G[i]) {
                int j = neighbor[0], k = neighbor[1];
                if (j == p) continue;
                pa[j] = i;
                stack.push(new int[]{j, i});
            }
        }
    }
}