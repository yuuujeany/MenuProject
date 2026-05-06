package vo;

import java.util.Objects;

public class MenuVO {
        private int menuId;        // 메뉴 번호
        private String name;       // 메뉴명
        private int price;         // 가격
        private String category;   // 카테고리 (Main, Side, Drink 등)
        private boolean isSoldOut; // 판매 여부 (true: 판매중, false: 품절)

    public MenuVO(int menuId, String name, int price, String category, boolean isSoldOut) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.isSoldOut = isSoldOut;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
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

    @Override
    public String toString() {
        return menuId + "," + name + "," + price + "," + category + "," + isSoldOut;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MenuVO menuVO = (MenuVO) o;
        return menuId == menuVO.menuId && price == menuVO.price && isSoldOut == menuVO.isSoldOut && Objects.equals(name, menuVO.name) && Objects.equals(category, menuVO.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, name, price, category, isSoldOut);
    }
}
