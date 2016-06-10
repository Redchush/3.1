package service.parsers.helpers;

import domain.model.Menu;
import domain.model.menu_items.MenuItemBuilder;

import java.util.*;

/**
 * Created by user on 30.05.2016.
 */
public class Mediator {
    MenuItemBuilder builder = new MenuItemBuilder();

    String menuCategoryTitle="";

    String id="";;
    String name="";;
    String foto="";;
    String description="";;
    String price="";;
    String ratio="";;
    String ratioUnit="";;
    Map<String, Map<String, String>> mapOfSubElements = new HashMap<>();
    boolean isComplex = false;
    String menuName="";
    String menuVersion="";

    public void resetItem(){
        id="";
        name="";
        foto="";
        description="";
        price="";
        ratio= "";
        ratioUnit="";
        mapOfSubElements= new HashMap<>();
        boolean isComplex=false;
    }

    public void completeCategory(){
    //    System.out.println(" @@@ category " + menuCategoryTitle);
        builder.createMenuCategoryByFlushing(menuCategoryTitle);
    }

    public void completeMenuItem(){
//        System.out.println("COMPLETING ELEMENT " + id + " " + name + " " + foto + " " +
//                mapOfSubElements + " " + ratio + " " + ratioUnit);
        List<String> ratioList = Arrays.asList(ratio.split(" "));
        if (isComplex)
             builder.createComplexMenuComponent(id, name, foto,
                        mapOfSubElements,ratioList, ratioUnit);
        else builder.createSimpleMenuComponent(id, name, foto,
                description, price, ratioList, ratioUnit, false);
    }
    public void setVariant(String value, String elName, String number){
        Map<String, String> innerMap = new HashMap<>();
        innerMap.put(elName, value);
        if (!mapOfSubElements.containsKey(number))
            mapOfSubElements.put(number, innerMap);
        else {
            mapOfSubElements.get(number).put(elName, value);
        }
    }
    public Menu completeMenu() {
        Menu menu = builder.endCreatingMenu(menuName, menuVersion);
     //   System.out.println("ENDING MENU " + menu);
        return menu;
    }

    public void setMenuCategoryTitle(String menuCategoryTitle) {
        this.menuCategoryTitle = menuCategoryTitle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public void setRatioUnit(String ratioUnit) {
        this.ratioUnit = ratioUnit;
    }
    public void setMapOfSubElements(Map<String, Map<String, String>> mapOfSubElements) {
        this.mapOfSubElements = mapOfSubElements;
    }

    public boolean isComplex() {
        return isComplex;
    }

    public void setComplex(boolean complex) {
        isComplex = complex;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public void setMenuVersion(String menuVersion) {
        this.menuVersion = menuVersion;
    }


    @Override
    public String toString() {
        return "Mediator{" +
                "builder=" + builder +
                ", menuCategoryTitle='" + menuCategoryTitle + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", foto='" + foto + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", ratio='" + ratio + '\'' +
                ", ratioUnit='" + ratioUnit + '\'' +
                ", mapOfSubElements=" + mapOfSubElements +
                ", isComplex=" + isComplex +
                '}';
    }


}
