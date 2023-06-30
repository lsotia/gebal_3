package gebal.ver3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("3. 회원탈퇴");
        System.out.print("메뉴를 선택하세요: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                Registration.registerUser();
                break;
            case 2:
                Registration.loginUser();
                break;
            case 3:
                Registration.withdrawUser();
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                break;
        }

        scanner.close();
    }
}