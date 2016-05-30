package service.parsers.sax;

import domain.model.Menu;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by user on 30.05.2016.
 */
public class SaxLauncher {
    public static final String xmlFilePath = "src\\source\\xml\\Menu_XML.xml";
    public static void main(String[] args) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            InputSource inputSource = new InputSource(
                                        new FileInputStream(
                                                new File(xmlFilePath)));
            MenuSaxHandler handler = new MenuSaxHandler();
            reader.setContentHandler(handler);
            reader.parse(inputSource);
            Menu menu = handler.getMenu();
            System.out.println(menu);

        } catch (SAXException e) {
            System.out.println("Serious exception at launching parcer" + e);
        } catch (FileNotFoundException e) {
            System.out.println("File at " + xmlFilePath + " doesn't exist");
        } catch (IOException e) {
            System.out.println("Problem during parsing " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
