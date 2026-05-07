package service;

import exception.MenuException;
import vo.MenuVO;
import java.io.*;
import java.util.ArrayList;

public class MenuService {
    private static MenuService instance = new MenuService();
    private ArrayList<MenuVO> list;

    private MenuService() {
        list = new ArrayList<MenuVO>();
        loadToCSV();
    }

    public static MenuService getInstance() {
        if (instance == null) instance = new MenuService();
        return instance;
    }

    private void loadToCSV() {
        File file = new File("menu.csv");
        if (!file.exists()) return;

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {

            br.readLine();

            while (true) {
                String str = br.readLine();
                if (str == null) break;

                String[] arr = str.split(",");
                if (arr.length < 5) continue;

                String statusStr = arr[4].trim();
                boolean isSoldOut = statusStr.equalsIgnoreCase("true") || statusStr.equals("판매중");

                list.add(new MenuVO(
                        arr[0].trim(),
                        arr[1].trim(),
                        Integer.parseInt(arr[2].trim()),
                        arr[3].trim(),
                        isSoldOut
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportToCSV() {
        try (FileWriter fw = new FileWriter("menu.csv");
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("메뉴번호,메뉴명,가격,카테고리,판매상태");
            for (MenuVO item : list) {
                pw.println(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MenuVO> getList() {
        return list;
    }

    public MenuVO searchMenu(String menuId) throws MenuException {
        int idx = list.indexOf(new MenuVO(menuId, null, 0, null, false));
        if (idx == -1) throw new MenuException("해당 메뉴 정보가 없습니다.");
        return list.get(idx);
    }

    public void checkDuplicateMenuId(String menuId) throws MenuException {
        int idx = list.indexOf(new MenuVO(menuId, null, 0, null, false));
        if (idx != -1) throw new MenuException("메뉴번호가 중복되었습니다.");
    }

    public boolean appendMenu(MenuVO menuVO) {
        return list.add(menuVO);
    }

    public void deleteMenu(String menuId) throws MenuException {
        boolean isRemoved = list.removeIf(menu -> menu.getMenuId().equals(menuId));
        if (!isRemoved) throw new MenuException("삭제할 메뉴 정보가 없습니다.");
    }
}