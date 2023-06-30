package gebal.ver3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Registration {
    private static final String DataDir = "D:/thePlayer/";

    public static void registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("이메일을 입력하세요: ");
        String email = scanner.nextLine();

        // 동일한 이메일이 있는 경우 재입력 요청
        while (sameEmail(email)) {
            System.out.println("이미 존재하는 이메일입니다. 다른 이메일을 입력해주세요.");
            System.out.print("이메일을 입력하세요: ");
            email = scanner.nextLine();
        }

        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();

        // 이메일 유효성 검사 및 회원가입 처리
        if (valiEmail(email) && valiEmailCheck(email)) {
            if (valiPassword(password)) {
            	createUserDir(email);
                saveUserData(email, password);
                System.out.println("축 회원가입!");
            } else {
                System.out.println("비밀번호는 8~12자 이내로 입력해주세요.");
            }
        } else {
            System.out.println("올바른 이메일 형식이 아닙니다. 대문자, 숫자를 포함하고 8~12자 이내로 입력해주세요.");
        }

        scanner.close();
    }

    // 동일한 이메일이 이미 존재하는지 확인하는 메서드
    private static boolean sameEmail(String email) {
        String userFolder = DataDir + email.split("@")[0] + "/";
        File directory = new File(userFolder);
        return directory.exists();
    }

    // 이메일 유효성 검사 메서드
    private static boolean valiEmail(String email) {
        return email.contains("@");
    }

    // 이메일 요구사항 검사 메서드
    private static boolean valiEmailCheck(String email) {
        // 이메일에 대문자, 숫자 포함 여부 및 길이 조건을 검사.
        boolean hasUppercase = false;
        boolean hasNumber = false;

        for (char ch : email.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            }

            if (hasUppercase && hasNumber) {
                return true;
            }
        }

        return false;
    }

    // 비밀번호 길이 검사 메서드
    private static boolean valiPassword(String password) {
        return password.length() >= 8 && password.length() <= 12;
    }

    // 사용자 폴더 생성 메서드
    private static void createUserDir(String email) {
        String userFolder = DataDir + email.split("@")[0] + "/";
        File directory = new File(userFolder);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("사용자 폴더가 생성되었습니다.");
            } else {
                System.out.println("사용자 폴더 생성에 실패하였습니다.");
            }
        }
    }

    // 사용자 데이터 저장 메서드
    private static void saveUserData(String email, String password) {
        String userFolder = DataDir + email.split("@")[0] + "/";
        String userDataFile = userFolder + email.split("@")[0] + ".txt";

        try (FileWriter writer = new FileWriter(userDataFile)) {
            writer.write("이메일: " + email + "\n");
            writer.write("비밀번호: " + password + "\n");
            System.out.println("사용자 데이터 저장완료.");
        } catch (IOException e) {
            System.out.println("사용자 데이터 저장실패.");
            e.printStackTrace();
        }
    }


}