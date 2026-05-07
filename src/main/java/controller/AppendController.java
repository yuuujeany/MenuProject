package controller;

import exception.MenuException;
import service.MenuService;
import vo.MenuVO;

import java.util.Scanner;

public class AppendController implements Controller {
    @Override
    public void execute(Scanner sc) {
        System.out.println("신규 메뉴 등록을 시작합니다.....");
        System.out.print("등록할 메뉴번호 : "); // println보다 print가 입력받기 더 좋습니다.
        String menuId = sc.nextLine();

        try {
            MenuService.getInstance().checkDuplicateMenuId(menuId);

            System.out.print("등록할 메뉴명 : ");
            String name = sc.nextLine();

            System.out.print("등록할 가격 : ");
            int price = sc.nextInt();
            sc.nextLine(); // 숫자 입력 후 엔터 제거

            System.out.print("등록할 카테고리 : ");
            String category = sc.nextLine();

            System.out.print("등록할 판매 상태 (1.판매중 / 2.품절) : ");
            int choice = sc.nextInt();
            sc.nextLine(); // 숫자 입력 후 엔터 제거

            // [수정 포인트 1] String이 아니라 boolean으로 선언하고 값을 할당해야 합니다.
            // 사용자가 1번을 선택하면 true(판매중), 아니면 false(품절)
            boolean isSoldOut = (choice == 1);

            // [수정 포인트 2] boolean 타입 변수인 isSoldOut을 생성자에 전달합니다.
            boolean flag = MenuService.getInstance().appendMenu(
                    new MenuVO(menuId, name, price, category, isSoldOut)
            );

            System.out.println(flag ? "신규메뉴 등록 성공" : "신규메뉴 등록 실패");

        } catch (MenuException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // 숫자 입력 칸에 문자를 입력했을 때 발생하는 에러 방지
            System.out.println("잘못된 입력 형식입니다.");
            sc.nextLine(); // 버퍼 비우기
        }
    }
}