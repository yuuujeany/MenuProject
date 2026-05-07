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
        MenuService menuService = MenuService.getInstance();

        try {

            boolean isRemoved = menuService.getList().removeIf(menu -> menu.getMenuId().equals(menuId));

            if (isRemoved) {

                menuService.exportToCSV();
                System.out.println("메뉴번호 [" + menuId + "] 삭제가 완료되었습니다.");
            } else {

                System.out.println("해당 메뉴 번호가 존재하지 않습니다.");
            }

        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
    }
}