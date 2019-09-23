package suager.lemon;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        String num1 = scanner.nextLine();
        String num2 = scanner.nextLine();
        System.out.println(multiply(num1,num2));
    }

    private static String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        int[] num = new int[num1.length()+ num2.length()];
        for(int i = num1.length() - 1; i >= 0; i --){
            for(int j = num2.length() - 1; j >= 0; j --){
                num[i + j + 1]  += (num1.charAt(i) - '0') *(num2.charAt(j) - '0');
            }
        }
        int add = 0;
        for(int i = num.length - 1; i >= 0; i--){
            int t = (num[i] + add) % 10;
            add = (num[i] + add)/ 10;
            num[i] = t;
        }
        boolean flag = true;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < num.length; i ++){
            if(num[0] == 0 && flag){
                i = i + 1;
                flag = false;
            }
            builder.append(num[i]);
        }

        return builder.toString();
    }

}
