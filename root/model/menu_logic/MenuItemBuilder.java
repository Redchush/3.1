package root.model.menu_logic;


import root.model.Menu;
import root.model.MenuCategory;
import root.model.menu_items.*;

import java.io.File;
import java.util.*;

/**
 * separate MenuItemBuilder for providing low coupling with parser results
 * and limiting the model interface
 */
public class MenuItemBuilder {
    public static final String UNDEFINED = "undefined";

    public MenuItemBuilder(){};

    public MenuItemComponent createSimpleMenuComponent(String id, String name, String foto, String description, String price,
                                                       List<String> ratio, String ratioUnit) {
        File thisFoto = null;

        if (!foto.equals(UNDEFINED)) thisFoto = new File(foto);

        description = validateData(description);
        price = validateData(price);
        ratioUnit = validateData(ratioUnit);
        if (ratio == null) ratio = Collections.emptyList();

        MenuItemConcrete item = new MenuItemConcrete(id, name, description, price);
        item.setFoto(thisFoto);
        item.setRatio(createListOfIngredients(ratio, ratioUnit));

        return item;
    }

    public MenuItemComponent createComplexMenuComponent(String id, String name, String foto,
                                                        Map<String, Map<String, String>> mapOfSubElements,
                                                        List<String> ratio, String ratioUnit){

        List<MenuItemComponent> menuItems = createListOfSubComponents(id, name, foto, mapOfSubElements,
                                                                      ratio, ratioUnit);

        MenuItemComposite items = new MenuItemComposite(id, name, menuItems);
        File thisFoto = null;
        if (!foto.equals(UNDEFINED)) thisFoto = new File(foto);
        items.setFoto(thisFoto);
        return items;
    }

    public MenuCategory createMenuCategory(String title, List<MenuItemComponent> components){
        return new MenuCategory(title, components);
    }

    public Menu createMenu(List<MenuCategory> categories, String name, String version){
        return new Menu(categories, name, version);
    }

    public Menu createEmptyMenu(String name, String version){
        Menu menu = new Menu();
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

    private List<MenuItemComponent> createListOfSubComponents(String id, String name, String foto,
                                                                    Map<String, Map<String, String>> mapOfSubElements,
                                                                    List<String> ratio, String ratioUnit){

       List<MenuItemComponent> menuItems = new ArrayList<>();

       for (Map.Entry<String,Map<String, String>> item : mapOfSubElements.entrySet()) {
           String variant = item.getKey();
           Object[] values = item.getValue().values().toArray();
           String concreteDescription = (String) values[0];
           String concretePrice = (String) values[1];

           MenuItemComponent item1 = createSimpleMenuComponent(id, name, foto,
                                        concreteDescription, concretePrice, ratio, ratioUnit);

           menuItems.add(item1);
       }
       return menuItems;
   }

    private String validateData(String text){
        return text == null ? "" : text.trim();
    }
}
