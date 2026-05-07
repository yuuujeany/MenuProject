package vo;

import java.util.Objects;

public class MenuVO {
    private String menuId;
    private String name;
    private int price;
    private String category;
    private String isSoldOut; // boolean에서 String("판매중"/"품절")으로 변경됨을 가정 [cite: 32]

    public MenuVO(String menuId, String name, int price, String category, String isSoldOut) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.isSoldOut = isSoldOut;
    }

    // Getter & Setter 생략 (기존과 동일)

    @Override
    public String toString() {
        return menuId + "," + name + "," + price + "," + category + "," + isSoldOut;
    }

    // [변경됨] menuId만 비교하도록 수정: 이 부분이 모든 필드를 비교하면 searchMenu에서 객체를 찾지 못함
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuVO menuVO = (MenuVO) o;
        return Objects.equals(menuId, menuVO.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId);
    }

    public void setName(String s) {
    }

    public void setPrice(int i) {
    }

    public void setCategory(String s) {
    }

    public void setSoldOut(String s) {
    }
}