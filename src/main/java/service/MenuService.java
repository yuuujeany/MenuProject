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
        loadToCSV(); // 메서드 호출 시 대소문자 주의
    }


    private void loadToCSV() { // 메서드명을 소문자로 통일 (자바 관례)
        File file = new File("menu.csv");
        if (!file.exists()) {
            System.out.println("menu.csv 파일이 없습니다. 새로 생성을 준비합니다.");
            return;
        }

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {

            // [수정 1] CSV 첫 줄(헤더) 읽기
            // CSV 파일 제일 윗줄에 "메뉴번호,메뉴명..."이 있다면 유지하고, 데이터만 있다면 주석처리하세요.
            br.readLine();

            while (true) {
                String str = br.readLine();
                if (str == null) break;

                String[] arr = str.split(",");
                if (arr.length < 5) continue; // 데이터가 불완전한 줄 건너뛰기

                // [수정 2] 한글 상태값("판매중")을 boolean으로 변환
                // arr[4]가 "판매중"이면 true, 그 외(품절 등)는 false
                boolean isSoldOutStatus = arr[4].trim().equals("판매중");

                list.add(new MenuVO(
                        arr[0].trim(),
                        arr[1].trim(),
                        Integer.parseInt(arr[2].trim()),
                        arr[3].trim(),
                        isSoldOutStatus
                ));
            }
            System.out.println("메뉴 정보 로드 완료 (총 " + list.size() + "건)");

        } catch (IOException e) {
            System.out.println("파일 로드 중 오류 발생: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("가격 데이터 형식이 잘못되었습니다.");
        }
    }

    public void exportToCSV() {
        try (FileWriter fw = new FileWriter("menu.csv");
             PrintWriter pw = new PrintWriter(fw)) {

            // [수정 3] 저장할 때도 헤더를 추가해주면 좋습니다.
            pw.println("메뉴번호,메뉴명,가격,카테고리,판매상태");

            list.forEach(item -> pw.println(item));
            System.out.println("전체 메뉴 정보 저장 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MenuService getInstance() {
        if (instance == null) instance = new MenuService();
        return instance;
    }

    public MenuVO searchMenu(String menuId) throws MenuException {
        // [중요] MenuVO의 equals가 menuId만 비교하게 되어있어야 합니다.
        int idx = list.indexOf(new MenuVO(menuId, null, 0, null, true));
        if (idx == -1) throw new MenuException("해당 메뉴 정보가 없습니다.");
        return list.get(idx);
    }

    public void checkDuplicateMenuId(String menuId) throws MenuException {
        int idx = list.indexOf(new MenuVO(menuId, null, 0, null, true));
        if (idx != -1) throw new MenuException("메뉴번호가 중복되었습니다.");
    }

    public boolean appendMenu(MenuVO menuVO) {
        return list.add(menuVO);
    }

    public void deleteMenu(String menuId) throws MenuException {
        // equals를 통해 리스트에서 바로 객체 삭제 시도
        if (!list.remove(new MenuVO(menuId, null, 0, null, true)))
            throw new MenuException("삭제할 메뉴 정보가 없습니다.");
    }

    public ArrayList<MenuVO> getList() {
        return list;
    }
}