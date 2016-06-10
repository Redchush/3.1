package root.service;

import root.model.Menu;
import root.service.exceptions.MenuParserException;

/**
 * Created by user on 10.06.2016.
 */
public interface MenuParser {
    Menu parse(String path) throws MenuParserException;
}
