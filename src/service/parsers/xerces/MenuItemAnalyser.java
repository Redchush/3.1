package service.parsers.xerces;


import domain.model.menu_items.MenuItemBuilder;
import domain.model.menu_items.MenuItemComponent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

/**
 * Created by user on 23.05.2016.
 */
public class MenuItemAnalyser {
    private Element element;
    private final static String DEFAULT = "default";

    public MenuItemAnalyser(Element item) {
       this.element = item;
    }
    public MenuItemComponent buildSimpleMenuItem(){
//        System.out.println(this.element.getTextContent());
//        System.out.println(this.element.getNodeName());
       String price ="";
       List<String> ratio;
       String name = getTextContentOfElement("name");
       String description =  getTextContentOfElement("description");
       String foto =  getElementByName("foto").getAttribute("path");
       if (getElementByName("price") != (null)) {
            price = getTextContentOfElement("price");
        }
       ratio = getRatio();
       String ratioUnit = getElementByName("ratio").getAttribute("unit");
//       System.out.println("name: " + name
//        + ", foto: " + foto
//        + ", desc: " + description
//        + ", price " + price
//        + ", ratio " + ratio
//        + ", ratioUnit " + ratioUnit
//       );
        return MenuItemBuilder.createSimpleMenuComponent(name,foto,description,price,ratio,ratioUnit);
    }

    
    public MenuItemComponent buildComplexMenuItem(){
        String price ="";
        List<String> ratio;
        int counter = 1;
        String name = getTextContentOfElement("name");
        Map<String, Map<String, String>> mapOfSubElements = new HashMap<>();

        mapOfSubElements =  getComplexContentOfElement("description", mapOfSubElements, counter++ );
        mapOfSubElements = getComplexContentOfElement("price",mapOfSubElements, counter++ );
        String foto =  getElementByName("foto").getAttribute("path");
        ratio = getRatio();
        String ratioUnit = getElementByName("ratio").getAttribute("unit");
        return MenuItemBuilder.createComplexMenuComponent(name, foto, mapOfSubElements, ratio, ratioUnit);

        /*System.out.println("name: " + name
                + ", foto: " + foto
                + ", desc: " + mapOfSubElements
                + ", ratio " + ratio
                + ", ratioUnit " + ratioUnit
        );*/

    }
    private Element getElementByName(String name){
        return (Element)element
                .getElementsByTagName(name)
                .item(0);
    }
    private String getTextContentOfElement(String name){
        return getElementByName(name).getTextContent();
    }

    private Map<String, Map<String, String>> getComplexContentOfElement(String name, Map<String, Map<String, String>> map,
                                                                         int propertyNumber){
        Element el = getElementByName(name);
        String elName = "";
        for (Node childNode = el.getFirstChild(); childNode != null; childNode = childNode.getNextSibling() ) {
           if (childNode.getNodeType() == Node.ELEMENT_NODE){
               elName = childNode.getNodeName();
               break;
           }
        }

        NodeList childrens = el.getElementsByTagName(elName);
        for (int i = 0; i < childrens.getLength() ; i++) {
            Element item = (Element) childrens.item(i);
            Map<String, String> innerMap = new HashMap<>();
            innerMap.put(elName, item.getTextContent());

       //     System.out.println(map.containsKey(item.getAttribute("variantNumber")));
            if (!map.containsKey(item.getAttribute("variantNumber")))
                map.put(item.getAttribute("variantNumber"), innerMap);
            else {
                map.get(item.getAttribute("variantNumber")).put(elName, item.getTextContent());
            }
          //  System.out.println(childrens.item(i) + " " + innerMap);
        }
    //    System.out.println(map);
        return map;

    }

    private List<String> getRatio(){
        List<String> ratioList = new LinkedList<>();
        Element ratio = getElementByName("ratio");
        ratioList.addAll(Arrays.asList
                        (ratio.getTextContent().split(" ")));
        return ratioList;

    }
}
