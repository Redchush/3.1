package root.service.parsers.stax;

import root.model.Menu;
import root.service.MenuParser;
import root.service.exceptions.MenuParserException;
import root.service.exceptions.MenuParserIOException;
import root.service.helpers.Mediator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static root.service.helpers.DocumentConstants.*;



public class MenuStaxParser implements MenuParser{

    private static final Mediator mediator = new Mediator();
    private static XMLStreamReader reader;

    @Override
    public Menu parse(String path) throws MenuParserException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream inputSource = null;

        try {
            inputSource = new FileInputStream(new File(path));
            reader = factory.createXMLStreamReader(inputSource);
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new MenuParserIOException();
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
            throw new MenuParserException(e);
        }
       return mediator.getMenu();
    }


    private static String processElement(String name, String counter) {
        switch (name) {
            case SIMPLE_MENU_ITEM:
                mediator.setItemComplex(false);
                break;
            case COMPLEX_MENU_ITEM:
                mediator.setItemComplex(true);
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
                String menuName = reader.getAttributeValue(0);
                String menuVersion = reader.getAttributeValue(1);
                mediator.createMenu(menuName, menuVersion);
                break;
        }

        return counter;
    }


    private static void processChars(String text, String counter, String name) {
        if (text.isEmpty()) {
            return;
        }

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
