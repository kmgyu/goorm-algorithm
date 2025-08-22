package groom.greedy;

import java.io.*;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ans =  new int[N]; //answer
        String[] input = br.readLine().split(" ");

        // 사실 정렬할 거면 굳이 pq 쓸 필요는 없다는 거임... 그냥 정렬써도 된다.
        // Task2에서 재활용해와서 이런 모양인 거다!
        // 콜렉션의 소트가 머지소트였나? 아무튼 그거 하면 이렇게 할 필요 없다. 안정 정렬이라 인덱스 먼저 온놈이 우선이기 대문에....
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> {
                    if (a[0] != b[0]) {
                        return Long.compare(a[0], b[0]); // 시간 순서대로 정렬
                    } else {
                        return Long.compare(a[1], b[1]); // 시간이 같을 경우 도착 시각이 우선
                    }
                }
        );

        // 0이 안되게 만들어야함.
        // 0되면? 에라 모르겠다 다같이 죽자 1부터 N까지 출력
        for (int i = 0; i<N; i++){
            pq.add(new int[]{Integer.parseInt(input[i]),i});
        }

        boolean breakflag = false;
        for (int i = 0; i<N; i++){
            int[] current = pq.poll();
            if(current[0]-i <= 0) {
                breakflag = true;
                break;
            }
            ans[i] = current[1];
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if(breakflag){
            for(int i = 0; i<N; i++){
                bw.write((i+1) + " ");
            }
        } else {
            for(int i = 0; i<N; i++){
                bw.write((ans[i]+1) + " ");
            }
        }
        bw.flush();
    }
}
