package controller;

import exception.MenuException;
import service.MenuService;
import vo.MenuVO;
import java.util.Scanner;

public class UpdateController implements Controller {
    @Override
    public void execute(Scanner sc) {
        System.out.println("기존 메뉴 수정을 시작합니다.....");
        System.out.print("수정할 메뉴번호 : ");
        String menuId = sc.nextLine();

        try {
            // 1. 수정할 메뉴 찾기
            MenuVO vo = MenuService.getInstance().searchMenu(menuId);

            // 2. 정보 수정 입력 받기
            System.out.print("수정할 메뉴명 : ");
            vo.setName(sc.nextLine());

            System.out.print("수정할 가격 : ");
            vo.setPrice(sc.nextInt());
            sc.nextLine(); // 숫자 입력 후 개행문자(엔터) 제거

            System.out.print("수정할 카테고리 : ");
            vo.setCategory(sc.nextLine());

            // 3. 판매 상태 수정 (AppendController와 동일하게 처리)
            System.out.print("수정할 판매 상태 (1.판매중 / 2.품절) : ");
            int choice = sc.nextInt();
            sc.nextLine(); // 엔터 제거

            // boolean 변수 isSoldOut에 true(판매중) 또는 false(품절) 저장
            boolean isSoldOut = (choice == 1);
            vo.setSoldOut(isSoldOut);

            System.out.println("기존 메뉴 수정 성공");

        } catch (MenuException m) {
            System.out.println(m.getMessage());
            System.out.println("메뉴 수정에 실패했습니다.");
        }
    }
}