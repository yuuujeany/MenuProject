package service;

import vo.MenuVO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
                list.add(new MenuVO(Integer.parseInt(arr[0]), arr[1],
                        Integer.parseInt(arr[2]), arr[3].trim(), Boolean.parseBoolean(arr[4])));
            }

            System.out.println("메뉴 정보 로드 완료");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  MenuService getInstance(){
        if(instance == null)
            instance = new MenuService();
        return  instance;
    }
}
