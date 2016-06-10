package root.service.helpers;


import root.model.Menu;
import root.model.MenuCategory;
import root.model.menu_items.MenuItemComponent;
import root.model.menu_logic.MenuItemBuilder;

import java.util.*;

/**Mediates between MenuItemBuilder and parser
 * Accumulate data before creating model object
 * Each MenuSaxHandler has own Mediator, which has own builder, so
 * despise of big amount of fields this is thread safe
 */

public class Mediator {

    private final MenuItemBuilder builder = new MenuItemBuilder();

    // fields for menu creating //
    private Menu menu;
    private boolean isMenuCompleted = false;

    //temporary fields for category creating //
    private List<MenuItemComponent> componentList = new ArrayList<>();
    private String menuCategoryTitle="";

    //temporary fields for menuItem creating //
    private String id="";
    private String name="";
    private String foto="";
    private String description="";
    private String price="";
    private String ratio="";
    private String ratioUnit="";
    private  Map<String, Map<String, String>> mapOfSubElements = new HashMap<>();
    private boolean isItemComplex = false;

    public void createMenu(String menuName, String menuVersion){
        this.menu = builder.createEmptyMenu(menuName, menuVersion);
    }

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

    public void resetComponentList(){
        componentList = new ArrayList<>();;
    }

    public void completeCategory(){
        if ((componentList != null)&&(!menuCategoryTitle.isEmpty())) {
            MenuCategory category = builder.createMenuCategory(menuCategoryTitle, componentList);
            menu.addCategory(category);
        }
    }

    public boolean isMenuCompleted() {
        return isMenuCompleted;
    }

    public void completeMenuItem(){
        List<String> ratioList = Arrays.asList(ratio.split(" "));
        MenuItemComponent currentComponent;

        if (isItemComplex) {
            currentComponent = builder.createComplexMenuComponent(id, name, foto,
                                            mapOfSubElements, ratioList, ratioUnit);
        }
        else {
            currentComponent = builder.createSimpleMenuComponent(id, name, foto,
                                            description, price, ratioList, ratioUnit);
        }
        componentList.add(currentComponent);
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

    public void setMenuCompleted(boolean menuCompleted) {
        isMenuCompleted = menuCompleted;
    }

    public Menu getMenu() {
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

    public void setItemComplex(boolean itemComplex) {
        isItemComplex = itemComplex;
    }
}
