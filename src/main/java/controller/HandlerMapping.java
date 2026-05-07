package controller;

public class HandlerMapping {
    private static HandlerMapping instance = new HandlerMapping();

    public static  HandlerMapping getInstance(){
        if(instance == null)
            instance = new HandlerMapping();
        return instance;
    }

    public Controller createController(int no){
        Controller controller = null;
        switch (no){
            case 1:
                controller = new AppendController();
                break;
            case 2:
                controller =new DeleteController();
                break;
            case 5:
                controller = new  PrintAllController();
                break;


        }
        return controller;
    }
}
