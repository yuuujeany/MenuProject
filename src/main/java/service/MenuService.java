package service;

public class MenuService {
    private  static MenuService instance = new MenuService();

    private  MenuService() {}

    public static  MenuService getInstance(){
        if(instance == null)
            instance = new MenuService();
        return  instance;
    }
}
