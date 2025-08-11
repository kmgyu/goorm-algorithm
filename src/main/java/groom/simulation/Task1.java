package groom.simulation;

import java.io.*;

import java.util.Arrays;
import java.util.stream.IntStream;

class Task1 {
    public static void main(String[] args) throws IOException  {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int location = Integer.parseInt(input[2])-1;

        int[] H = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int Q = Integer.parseInt(br.readLine());

        String[] move = br.readLine().split(" ");

        long result = 0;
        int lazy[] = new int[N];

        for(int i = 0; i<Q; i++) {
            // lazy location에 i 그대로 담을 경우 M을 만족시키는 S에서 무한 파밍 문제 발생.
            int current = lazy[location]>0 ? i-lazy[location]+1 : H[location]+i;
            if (current >= M){
                result += current;
                lazy[location] = i+1;
            }
            switch(move[i]) {
                case "L":
                    location = (location+N-1)%N;
                    break;
                case "R":
                    location = (location+1)%N;
                    break;
            }
        }

        System.out.println(result);
    }

}