package service.parsers.xerces;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import domain.model.Menu;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by user on 23.05.2016.
 *   C:\\Users\\user\\IdeaProjects\\XMLparserProject\\src\\xml_files\\Menu_XMD.xsd
 *   \XMLparserProject\out\production\XMLparserProject\xml_files
 */
public class DOMLogic {
    public static final String PATH = "src\\source\\xml\\Menu_XML.xml";
    public static void main(String[] args) throws IOException, SAXException {
        DOMParser parser = new DOMParser();
        parser.parse(PATH);
        Document document = parser.getDocument();
        document.normalizeDocument();
        Element root = document.getDocumentElement();
        Menu menu = Analyser.parceDocumentSkippingMeta(root);
        System.out.println(menu);

    }
}
