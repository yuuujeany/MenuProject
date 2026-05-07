package controller;

import exception.MenuException;
import service.MenuService;
import vo.MenuVO;
import java.util.Scanner;

public class UpdateController implements Controller {
    @Override
    public void execute(Scanner sc) {
        System.out.println("기존 메뉴 수정을 시작합니다.....");
        System.out.println("수정할 메뉴번호 : ");
        String menuId = sc.nextLine();

        try {
            MenuVO vo = MenuService.getInstance().searchMenu(menuId);

            System.out.println("수정할 메뉴명 : ");
            vo.setName(sc.nextLine());
            System.out.println("수정할 가격 : ");
            vo.setPrice(sc.nextInt()); sc.nextLine();
            System.out.println("수정할 카테고리 : ");
            vo.setCategory(sc.nextLine());
            System.out.println("수정할 품절 유무 (판매중/품절) : ");
            vo.setSoldOut(sc.nextLine());

            System.out.println("기존 메뉴 수정 성공");
        } catch (MenuException m) {
            System.out.println(m.getMessage());
            System.out.println("메뉴 수정에 실패했습니다.");
        }
    }
}