package controller;

import exception.MenuException;
import service.MenuService;

import java.util.Scanner;

public class DeleteController implements Controller {
    @Override
    public void execute(Scanner sc) {
        System.out.println("기존 메뉴 삭제를 시작합니다.....");
        System.out.println("삭제할 메뉴번호 : ");
        String menuId = sc.nextLine();

        try{
            MenuService.getInstance().deleteMenu(menuId);
            System.out.println("메뉴 삭제 완료");
        }catch (MenuException m){
            System.out.println(m.getMessage());
        }
    }
}
