package root.service.parsers.sax;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import root.model.Menu;
import root.service.helpers.Mediator;

import static root.service.helpers.DocumentConstants.*;


public class MenuSaxHandler extends DefaultHandler {

    private final Mediator mediator = new Mediator();
    private StringBuilder text = new StringBuilder();
    private String counter = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
                             throws SAXException {
        switch (qName)
        {
            case SIMPLE_MENU_ITEM :
                mediator.setItemComplex(false);
                break;
            case COMPLEX_MENU_ITEM:
                mediator.setItemComplex(true);
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
            case MENU: {
                String menuName = attributes.getValue(NAME);
                String menuVersion = attributes.getValue(VERSION);
                mediator.createMenu(menuName, menuVersion);
                break;
            }
        }
        text = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        switch (qName)
        {
            case CATEGORY :
                mediator.completeCategory();
                mediator.resetComponentList();
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

    }

    @Override
    public void endDocument() throws SAXException {
        mediator.setMenuCompleted(true);
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

    public Menu getMenu() {
        if (!mediator.isMenuCompleted()) throw new UnsupportedOperationException("Menu isn't completed yet");
        else return mediator.getMenu();
    }
}
