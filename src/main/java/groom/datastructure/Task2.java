package groom.datastructure;

import java.io.*;
import java.util.*;
class Task2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> info = new HashMap();
        Set<String> food = new HashSet<>();

        for (int i = 0; i<N; i++) {
            String input[] = br.readLine().split(" ");
            if (info.containsKey(input[0])) {
                info.replace(input[0], info.get(input[0]) + Integer.parseInt(input[1]) );
            } else {
                info.put(input[0], Integer.parseInt(input[1]));
                food.add(input[0]);
            }
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String foodarr[] = food.toArray(new String[0]);
        Arrays.sort(foodarr);
        for (String foodie : foodarr) {
            bw.write(foodie +" "+info.get(foodie)+"\n");
            // System.out.println(foodie +" " + info.get(foodie));

        }
        bw.flush();
        // System.out.println(info.keySet().toArray());

    }
}