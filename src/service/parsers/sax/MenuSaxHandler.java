package service.parsers.sax;

import domain.model.Menu;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import service.parsers.helpers.Mediator;

import static service.parsers.helpers.DocumentConstants.*;

/**
 * Created by user on 30.05.2016.
 */
public class MenuSaxHandler extends DefaultHandler {
    Mediator mediator = new Mediator();
    StringBuilder text = new StringBuilder();
    String counter = "";
    Menu menu;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        switch (qName)
        {
            case SIMPLE_MENU_ITEM :
                mediator.setComplex(false);
                break;
            case COMPLEX_MENU_ITEM:
                mediator.setComplex(true);
                break;
            case MENU_ITEM:
                mediator.setId(attributes.getValue(ID));
                break;
            case FOTO : {
                mediator.setFoto(attributes.getValue(PATH));
                break;
            }
            case RATIO : {
                mediator.setRatioUnit(attributes.getValue(UNIT));
                break;
            }
            case MENU_ITEM_VARIANT : {
                counter = attributes.getValue(VARIANT_NUMBER);
                break;
            }
            case PRICE_FOR_EXACT_TYPE :{
                counter = attributes.getValue(VARIANT_NUMBER);
                break;
            }
            case MENU:
                mediator.setMenuName(attributes.getValue(NAME));
                mediator.setMenuVersion(attributes.getValue(VERSION));
                break;
        }
        text = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    //    System.out.println(" end qname " + qName + " " + text.toString());
        switch (qName)
        {
            case CATEGORY :
                mediator.completeCategory();
                break;
            case TITLE : {
                mediator.setMenuCategoryTitle(text.toString());
                break;
            }
            case NAME:
                mediator.setName(text.toString());
                break;
            case PRICE:
                mediator.setPrice(text.toString());
                break;
            case DESCRIPTION:
                mediator.setDescription(text.toString());
                break;
            case RATIO:
                mediator.setRatio(text.toString());
                break;
            case MENU_ITEM_VARIANT : {
                mediator.setVariant(text.toString(), MENU_ITEM_VARIANT, counter );
                break;
            }
            case PRICE_FOR_EXACT_TYPE :{
                mediator.setVariant(text.toString(), PRICE_FOR_EXACT_TYPE, counter);
                break;
            }
            case MENU_ITEM :
                mediator.completeMenuItem();
                break;
        }
        text = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length).trimToSize();
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Document starts successfully");
    }

    @Override
    public void endDocument() throws SAXException {
       menu = mediator.completeMenu();
    }
    @Override
    public void warning(SAXParseException e) throws SAXException {
        super.warning(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        super.fatalError(e);
    }

    public Menu getMenu() throws Exception {
        if (menu != null) return menu;
        else throw new Exception("Menu is not completed yet");
    }
}
