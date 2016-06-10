package root.launcher;


import org.xml.sax.SAXException;
import root.model.Menu;
import root.service.MenuParser;
import root.service.MenuParserFactory;
import root.service.exceptions.MenuParserException;

import java.io.IOException;

;

public class Main {

    private static final String GREETING = "Hello, user.\nEnter the path to file, please";
    private static final String RESULT = "After building document we have this result: ";
    private static final String MENU_FROM_SAX = "MENU FROM SAX: ";
    private static final String MENU_FROM_STAX = "MENU FROM STAX: ";

    public static void main(String[] args) throws IOException, SAXException, MenuParserException {

        MenuParserFactory factory = MenuParserFactory.getInstance();

        ConsoleHelper.writeMessage(GREETING);
        String path = ConsoleHelper.readString();

        MenuParser saxParser = factory.getParser(MenuParserFactory.SAX);
        Menu menuFromSax = saxParser.parse(path);
        ConsoleHelper.writeMessage(MENU_FROM_STAX);
        ConsoleHelper.writeMessage(menuFromSax.toString());


        MenuParser staxParser = factory.getParser(MenuParserFactory.STAX);
        Menu menuFromStax = staxParser.parse(path);
        ConsoleHelper.writeMessage(MENU_FROM_SAX);
        ConsoleHelper.writeMessage(menuFromStax.toString());
   }
}

