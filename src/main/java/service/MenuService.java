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

    private void loadToCSV() {
        try(FileReader fr = new FileReader("menu.csv");
            BufferedReader br = new BufferedReader(fr)) {
            br.readLine();
            while (true){
                String str = br.readLine();
                if(str == null) break;
                String [] arr = str.split(",");
                // [변경됨] Boolean.parseBoolean 제거: "판매중/품절" 문자열을 그대로 사용하기 위함
                list.add(new MenuVO(arr[0].trim(), arr[1].trim(), Integer.parseInt(arr[2].trim()), arr[3].trim(), arr[4].trim()));
            }
            System.out.println("메뉴 정보 로드 완료");
        } catch (Exception e) {
            System.out.println("로드 실패: " + e.getMessage());
        }
    }

    public void exportToCSV(){
        try(FileWriter fw = new FileWriter("menu.csv");
            PrintWriter pw = new PrintWriter(fw)) {
            list.forEach(item -> pw.println(item));
            System.out.println("전체메뉴정보 저장완료");
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static MenuService getInstance(){
        if(instance == null) instance = new MenuService();
        return instance;
    }

    public MenuVO searchMenu(String menuId) throws MenuException {
        // [참고] MenuVO의 equals가 ID만 비교하므로 이제 정상적으로 idx를 찾음 [cite: 33]
        int idx = list.indexOf(new MenuVO(menuId, null, 0, null, null));
        if(idx == -1) throw new MenuException("해당 메뉴 정보가 없습니다.");
        return list.get(idx);
    }

    public void checkDuplicateMenuId(String menuId) throws MenuException {
        int idx = list.indexOf(new MenuVO(menuId, null, 0, null, null));
        if(idx != -1) throw new MenuException("메뉴번호가 중복되었습니다.");
    }

    public boolean appendMenu(MenuVO menuVO) { return list.add(menuVO); }

    public void deleteMenu(String menuId) throws MenuException {
        if(!list.remove(new MenuVO(menuId, null, 0, null, null)))
            throw new MenuException("삭제할 메뉴 정보가 없습니다.");
    }

    public ArrayList<MenuVO> getList() { return list; }
}