package groom.timecomplexity;
import java.io.*;
class Task1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 구글링 : N! 뒤에 붙는 0의 개수는 N!을 소인수분해했을 때 10의 개수, 즉 2와 5의 쌍의 개수와 같습니다. 2의 개수보다 5의 개수가 항상 적으므로 5의 개수만 세면 됩니다.
        // 2랑 5 둘다 구할 필요가 없다... 에라토스테네스 체도 쓸 필요가 없다...!!!
        int input = Integer.parseInt(br.readLine());

        int cnt = 0;

        int pow = 5;
        while (pow <= input) {
            cnt += input/pow;
            pow *= 5;
        }

        System.out.println(cnt);
    }
}