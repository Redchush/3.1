package root.service;

import root.service.parsers.sax.MenuSaxParser;
import root.service.parsers.stax.MenuStaxParser;

/**
 * Created by user on 10.06.2016.
 */
public class MenuParserFactory {
    public static final String SAX = "SAX";
    public static final String STAX = "STAX";

    private static MenuParserFactory instance;
    private MenuParserFactory(){};

    public static MenuParserFactory getInstance() {
        if (instance == null)
        {
            instance = new MenuParserFactory();
        }
        return instance;
    }
    public MenuParser getParser(String type) {
        switch (type){
            case SAX : return new MenuSaxParser();
            case STAX : return new MenuStaxParser();
        }
        throw new UnsupportedOperationException();
    }
}
