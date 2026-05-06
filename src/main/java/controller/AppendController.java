package controller;

import java.util.Scanner;

public class AppendController implements Controller {
    @Override
    public void execute(Scanner sc) {
        System.out.println("신규 메뉴 등록을 시작합니다.....");
        System.out.println("등록할 메뉴번호 : ");
        String menuId = sc.nextLine();



    }
}
