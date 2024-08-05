package old02.common;

import java.util.Scanner;

public class ScannerUtil {
    Scanner scan = new Scanner(System.in,"ms949");

    public int getInt(String str) {
        int i = 0;
        while (true) {
            System.out.print(str + " : ");
            try {
                i = scan.nextInt();
                scan.nextLine();
                return i;
            } catch (Exception e) {
                System.out.println("수를 입력해주세요.");
                scan.nextLine();
            }

        }
    }

    public String getString(String str) {
        System.out.print(str + " : ");
        String s = scan.nextLine();
        return s;
    }

}
