package vo;

import java.util.Objects;

public class MenuVO {
    private String menuId;
    private String name;
    private int price;
    private String category;
    private boolean isSoldOut; // boolean에서 String("판매중"/"품절")으로 변경됨을 가정 [cite: 32]

    public MenuVO(String menuId, String name, int price, String category, boolean isSoldOut) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.isSoldOut = isSoldOut;
    }


    @Override
    public String toString() {
        // true일 때 "판매중", false일 때 "품절"로 출력되도록 수정
        String status = isSoldOut ? "판매중" : "품절";
        return menuId + "," + name + "," + price + "," + category + "," + status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MenuVO menuVO = (MenuVO) o;
        return Objects.equals(menuId, menuVO.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(menuId);
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }
}