package groom.simulation;

import java.io.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class Task2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] current = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = Integer.parseInt(br.readLine());

        Map<String, Boolean> pools = new HashMap<>();
        for(int i = 0; i < N; i++){
            // int[] 사용시, 주소 기준으로 get하기 때문에 틀리기 쉽다.
            pools.put(br.readLine(),true);
        }

        int Q = Integer.parseInt(br.readLine());
        String[] control = br.readLine().split(" ");

        for(String move: control){
            int[] temp = {current[0], current[1]};
            if(move.equals("L")) {
                temp[0]-=1;
            } else if(move.equals("R")) {
                temp[0]+=1;
            } else if(move.equals("U")) {
                temp[1]+=1;
            } else {
                temp[1]-=1;
            }
            String compare = Integer.toString(temp[0]) + " " + Integer.toString(temp[1]);
            if (pools.containsKey(compare)) continue;

            current = temp;

        }

        System.out.println(current[0] + " " + current[1]);
    }
}
