package groom.search.binarysearch;

import java.io.*;
class Task3 {
    public static long N;
    public static long M;
    public static final long URGAY = 1_000_000_000_000L;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Long.parseLong(input[0]);
        M = Long.parseLong(input[1]);

        // 저어는 형변환이 너무너무 실허요
        // *100 밖으로 빼면 계산 잘못되서 큰일나니 안에다 넣어주자.
        // 계산식을!!! 확인하자!!!!
        int current = (int) (M*100/N);
        int target = (int) current+1;

        // 자바도 초기화 할 때 , 쓸 수 있다.
        long left = 1, right = URGAY-1;
        long ans = URGAY;

        while (left <= right) {
            long mid = (left + right)/2;
            int nextRate = (int) ((M+mid)*100/(N+mid));
            if (nextRate >= target) {
                ans = mid;
                right = mid-1;
            } else if (nextRate < target) {
                left = mid+1;
            }
        }

        if (ans == URGAY) {
            System.out.println("X");
        } else {
            System.out.println(ans);
        }
    }
}