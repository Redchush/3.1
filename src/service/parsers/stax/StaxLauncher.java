package service.parsers.stax;

import domain.model.Menu;
import service.parsers.helpers.Mediator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static service.parsers.helpers.DocumentConstants.*;

/**
 * Created by user on 30.05.2016.
 */
public class StaxLauncher {

    private static final Mediator mediator = new Mediator();
    private static XMLStreamReader reader;
    public static final String xmlFilePath = "src\\source\\xml\\Menu_XML.xml";

    public static void main(String[] args) {
        //    StringReader stringReader = new StringReader()
        Menu menu = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream inputSource = null;
        try {
            inputSource = new FileInputStream(new File(xmlFilePath));
            reader = factory.createXMLStreamReader(inputSource);
        } catch (FileNotFoundException | XMLStreamException e) {
            System.out.println("Problem in opening file");
            System.exit(-1);
        }

        String name="";
        String counter="";
        String text="";
        try {
            while (reader.hasNext()){
                int type = reader.next();

                switch (type){
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        counter = processElement(name, counter);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        text = reader.getText().trim();
                        processChars(text, counter, name);
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        switch (name){
                            case CATEGORY:
                                mediator.completeCategory();
                            case MENU_ITEM:
                                mediator.completeMenuItem();
                                break;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        menu = mediator.completeMenu();
        System.out.println(menu);
    }
    private static String processElement(String name, String counter) {
        switch (name) {
            case SIMPLE_MENU_ITEM:
                mediator.setComplex(false);
                break;
            case COMPLEX_MENU_ITEM:
                mediator.setComplex(true);
                break;
            case MENU_ITEM:
                mediator.setId(reader.getAttributeValue(0));
                break;
            case FOTO: {
                mediator.setFoto(reader.getAttributeValue(0));
                break;
            }
            case RATIO: {
                mediator.setRatioUnit(reader.getAttributeValue(0));
                break;
            }
            case MENU_ITEM_VARIANT: {
                return reader.getAttributeValue(0);
            }
            case PRICE_FOR_EXACT_TYPE: {
                return reader.getAttributeValue(0);
            }
            case MENU:
                mediator.setMenuName(reader.getAttributeValue(0));
                mediator.setMenuVersion(reader.getAttributeValue(1));
                break;
        }
        return counter;
    }
    private static void processChars(String text, String counter, String name) {
        if (text.isEmpty()) {
            return;
        }
//        System.out.println("in chars");
//       System.out.println(text + " " + counter + " " + name);
       switch (name)
        {
            case TITLE : {
                mediator.setMenuCategoryTitle(text);
                break;
            }
            case NAME:
                mediator.setName(text);
                 break;
            case PRICE:
                mediator.setPrice(text);
                break;
            case DESCRIPTION:
                mediator.setDescription(text);
                break;
            case RATIO:
                mediator.setRatio(text);
                break;
            case MENU_ITEM_VARIANT : {
                mediator.setVariant(text, MENU_ITEM_VARIANT, counter );
                break;
            }
            case PRICE_FOR_EXACT_TYPE :{
                mediator.setVariant(text, PRICE_FOR_EXACT_TYPE, counter);
                break;
            }
            case SIMPLE_MENU_ITEM :
                mediator.completeMenuItem();
                break;
            case COMPLEX_MENU_ITEM:
                mediator.completeMenuItem();
                break;
        }
    }
}
