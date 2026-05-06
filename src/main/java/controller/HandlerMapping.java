package controller;

public class HandlerMapping {
    private static HandlerMapping instance = new HandlerMapping();

    private static  HandlerMapping getInstance(){
        if(instance == null)
            instance = new HandlerMapping();
        return instance;
    }

    public Controller createController(int no){
        Controller controller = null;
        switch (no){

        }
        return controller;
    }
}
