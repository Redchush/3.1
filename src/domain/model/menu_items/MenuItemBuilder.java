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
    public static MenuItemComponent createSimpleMenuComponent(String name, String foto, String description, String price,
                                                              List<String> ratio, String ratioUnit) {
        File thisFoto = null;
        if (!foto.equals("undefined")) thisFoto = new File(foto);
        MenuItemConcrete item = new MenuItemConcrete(name, description, price);
        item.setFoto(thisFoto);
        item.setRatio(createListOfIngredients(ratio, ratioUnit));
      //  System.out.println(item);
        return item;
    }
    public static MenuItemComponent createComplexMenuComponent(String name, String foto,
                                                               Map<String, Map<String, String>> mapOfSubElements,
                                                               List<String> ratio, String ratioUnit){
        List<MenuItemComponent> menuItems = new ArrayList<>();
        for (Map.Entry<String,Map<String, String>> item : mapOfSubElements.entrySet()){
           String variant = item.getKey();
           Object[] values = item.getValue().values().toArray();
           String concreteDescription = (String) values[0];
           String concretePrice = (String) values[1];
           MenuItemComponent item1 = createSimpleMenuComponent(name, foto, concreteDescription, concretePrice, ratio, ratioUnit);
           menuItems.add(item1);
        }
        MenuItemComposite items = new MenuItemComposite(name, menuItems);
        File thisFoto = null;
        if (!foto.equals("undefined")) thisFoto = new File(foto);
        items.setFoto(thisFoto);
        return items;
    }

    public static MenuCategory createMenuCategory(String title, List<MenuItemComponent> components){
        return new MenuCategory(title, components);
    }

    public static Menu createMenu(List<MenuCategory> categories, String name, String version){
        return new Menu(categories, name, version);
    }

    private static Ratio createListOfIngredients(List<String> ratio , String ratioUnit){
        List<Ingredient> ingredients = new LinkedList<>();
        for (String ingr : ratio) {
            ingredients.add(new Ingredient(ingr, ratioUnit));
        }
        return new Ratio(ingredients);
    }

}
