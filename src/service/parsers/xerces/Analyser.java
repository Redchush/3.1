package service.parsers.xerces;


import domain.model.Menu;
import domain.model.MenuCategory;
import domain.model.menu_items.MenuItemBuilder;
import domain.model.menu_items.MenuItemComponent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 23.05.2016.
 */
public class Analyser {

    public static Menu parceDocumentSkippingMeta(Element element){
        NodeList list = element.getElementsByTagName("category");
        List<MenuCategory> menuCategoris = parceCategoryList(list);
        String name  = element.getAttribute("name");
        String version = element.getAttribute("version");
        return  MenuItemBuilder.createMenu(menuCategoris, name, version);

    }

    public static List<MenuCategory> parceCategoryList(NodeList list){
       List<MenuCategory> menuCategoris = new ArrayList<>();
       for (int i = 0; i < list.getLength(); i++) {
           MenuCategory category = parceCategory(list.item(i));
           menuCategoris.add(category);
        }
        return menuCategoris;

    }

    private static MenuCategory parceCategory(Node node){
        List<MenuItemComponent> componentList = new ArrayList<>();
        String title = "";
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            MenuItemComponent component;
            Node item = children.item(i);
            if ( item.getNodeType() == Node.ELEMENT_NODE){
                switch (item.getNodeName()){
                    case "title" :
                        title = item.getTextContent();
                        break;
                    case "simpleMenuItem" :
                        MenuItemAnalyser simpleBuilder = new MenuItemAnalyser((Element) item);
                        component = simpleBuilder.buildSimpleMenuItem();
                        componentList.add(component);
                        break;
                    case "complexMenuItem" :
                        MenuItemAnalyser complexBuilder = new MenuItemAnalyser((Element) item);
                        component =  complexBuilder.buildComplexMenuItem();
                        componentList.add(component);
                        break;
                }
            }

        }
        MenuCategory menuCategory = MenuItemBuilder.createMenuCategory(title, componentList);

        return menuCategory;
    }



}
