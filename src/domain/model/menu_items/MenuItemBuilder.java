package domain.model.menu_items;

import domain.model.Menu;
import domain.model.MenuCategory;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * separate MenuItemBuilder for providing low coupling with parser results
 * and limiting the model interface
 */
public class MenuItemBuilder {

    Menu menu =new Menu();
    public List<MenuCategory> categoryList = new ArrayList<>();
    public List<MenuItemComponent> itemList = new ArrayList<>();

    public MenuItemComponent createSimpleMenuComponent(String id, String name, String foto, String description, String price,
                                                              List<String> ratio, String ratioUnit, boolean isSimple) {
        File thisFoto = null;
        if (foto!= null&&!foto.equals("undefined")) thisFoto = new File(foto);
        MenuItemConcrete item = new MenuItemConcrete(name, description, price);
        item.setFoto(thisFoto);
        item.setRatio(createListOfIngredients(ratio, ratioUnit));
        item.setId(id);
      //  System.out.println(item);
        if (!isSimple) itemList.add(item);
        return item;
    }
    public void createComplexMenuComponent(String id, String name, String foto,
                                                               Map<String, Map<String, String>> mapOfSubElements,
                                                               List<String> ratio, String ratioUnit){
        List<MenuItemComponent> menuItems = new ArrayList<>();
        int counter = 1;
        int size = mapOfSubElements.size();
        for (Map.Entry<String,Map<String, String>> item : mapOfSubElements.entrySet()){
           String variant = item.getKey();
           Object[] values = item.getValue().values().toArray();
           String concreteDescription = (String) values[0];
           String concretePrice = (String) values[1];
           MenuItemComponent item1 = createSimpleMenuComponent(id + "_" + variant, name, foto, concreteDescription,
                                                                concretePrice, ratio, ratioUnit, false);
           menuItems.add(item1);
        }
        MenuItemComposite items = new MenuItemComposite(name, menuItems);
        File thisFoto = null;
        if (!foto.equals("undefined")) thisFoto = new File(foto);
        items.setFoto(thisFoto);
        itemList.add(items);
    }

    public MenuCategory createMenuCategory(String title, List<MenuItemComponent> components){
        MenuCategory category = new MenuCategory(title, components);
        menu.addCategory(category);
        return new MenuCategory(title, components);
    }
    public void createMenuCategoryByFlushing(String title){
        MenuCategory category = new MenuCategory(title, itemList);
//        System.out.println("!!!!Category creating "){
//
//        }
        menu.addCategory(category);
//        System.out.println("CREATE CATEGORY ");
//        System.out.println(category);
        itemList = new ArrayList<>();
    }

    public Menu createNewMenu(List<MenuCategory> categories, String name, String version){
        menu =  new Menu(categories, name, version);
        return menu;
    }
   public Menu endCreatingMenu(String name, String version){
        menu.setName(name);
        menu.setVersion(version);
        return menu;
   }

    private Ratio createListOfIngredients(List<String> ratio , String ratioUnit){
        List<Ingredient> ingredients = new LinkedList<>();
        for (String ingr : ratio) {
            ingredients.add(new Ingredient(ingr, ratioUnit));
        }
        return new Ratio(ingredients);
    }

    public Menu getMenu() {
        return menu;
    }
}
