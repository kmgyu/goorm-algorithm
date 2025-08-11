package groom.math;

import java.util.Scanner;
// 파이썬으로 해서 코드가 없다..
public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int W = sc.nextInt();
        int R = sc.nextInt();
        System.out.println((int)(W * (1 + (double)R / 30)));
    }
}
