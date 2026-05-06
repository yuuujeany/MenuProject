package controller;

import service.MenuService;
import vo.MenuVO;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintAllController implements Controller {
    @Override
    public void execute(Scanner sc) {
        System.out.println("전체 메뉴를 출력합니다....");
        ArrayList<MenuVO> list = MenuService.getInstance().getList();

        for (MenuVO vo : list){
            System.out.println(vo);
        }

        System.out.println();
    }
}
