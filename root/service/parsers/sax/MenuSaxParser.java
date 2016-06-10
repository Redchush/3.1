package root.service.parsers.sax;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import root.model.Menu;
import root.service.MenuParser;
import root.service.exceptions.MenuParserException;
import root.service.exceptions.MenuParserIOException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuSaxParser implements MenuParser {

    @Override
    public Menu parse(String path) throws MenuParserException {
        Menu menu;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            InputSource inputSource = new InputSource(
                                          new FileInputStream(
                                          new File(path)));

            MenuSaxHandler handler = new MenuSaxHandler();
            reader.setContentHandler(handler);
            reader.parse(inputSource);
            return handler.getMenu();

        } catch (SAXException e) {
            throw new MenuParserException("Serious exception at launching parcer", e);
        } catch (FileNotFoundException e) {
           throw new MenuParserIOException("File at " + path + " doesn't exist" , e);
        } catch (IOException e) {
            throw new MenuParserException ("Problem during parsing ", e);
        }
    }
}
