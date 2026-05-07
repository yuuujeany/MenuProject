package controller;

import exception.MenuException;
import service.MenuService;
import vo.MenuVO;

import java.util.Scanner;

public class AppendController implements Controller {
    @Override
    public void execute(Scanner sc) {
        System.out.println("신규 메뉴 등록을 시작합니다.....");
        System.out.println("등록할 메뉴번호 : ");
        String menuId = sc.nextLine();

        try {
            MenuService.getInstance().checkDuplicateMenuId(menuId);

            System.out.println("등록할 메뉴명 : ");
            String name = sc.nextLine();
            System.out.println("등록할 가격 : ");
            int price =sc.nextInt(); sc.nextLine();
            System.out.println("등록할 카테고리 : ");
            String category = sc.nextLine();
            System.out.println("등록할 판매 상태 (1.판매중 / 2.품절) : ");
            int choice = sc.nextInt();
            sc.nextLine();
            boolean isSoldOut = (choice == 1);
            boolean flag = MenuService.getInstance().appendMenu(
                    new MenuVO(menuId, name, price, category, isSoldOut)
            );



            System.out.println(flag ? "신규메뉴 등록 성공" : "신규메뉴 등록 실패");
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }



    }
}
