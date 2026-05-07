package service;

import exception.MenuException;
import vo.MenuVO;

import java.io.*;
import java.util.ArrayList;

public class MenuService {
    private  static MenuService instance = new MenuService();
    private ArrayList<MenuVO> list;


    private  MenuService() {
        list = new ArrayList<MenuVO>();
        loadToCSV();
    }

    public ArrayList<MenuVO> getList() {
        return list;
    }

    private void loadToCSV() {
        try(FileReader fr =new FileReader("menu.csv");
            BufferedReader br = new BufferedReader(fr)) {
            br.readLine();
            while (true){
                String str = br.readLine();
                if(str == null) break;
//                System.out.println(str);
                String [] arr = str.split(",");
                list.add(new MenuVO(arr[0], arr[1],
                        Integer.parseInt(arr[2]), arr[3]));
            }

            System.out.println("메뉴 정보 로드 완료");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportToCSV(){
        try(FileWriter fw = new FileWriter("menu.csv");
        PrintWriter pw = new PrintWriter(fw)) {
            list.forEach(item ->pw.println(item));
            System.out.println("전체메뉴정보 저장완료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  MenuService getInstance(){
        if(instance == null)
            instance = new MenuService();
        return  instance;
    }

    public void checkDuplicateMenuId(String menuId) throws MenuException {
       int idx = list.indexOf(new MenuVO(menuId,null,0,null));
       if(idx != -1)
           throw new MenuException("메뉴번호가 중복되었습니다.");

    }

    public boolean appendMenu(MenuVO menuVO) {
        return list.add(menuVO);
    }
}
