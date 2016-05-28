package service.parsers.helpers;


public class DocumentConstants {
    // element name constants
    public static final String ROOT = "Menu";

    // metaData constants
    public static final String METADATA = "metadata";
    public static final String STRUCTURE = "structure";
    public static final String COL = "col";

    // menu item type
    public static final String SIMPLE_MENU_ITEM = "simpleMenuItem";
    public static final String COMPLEX_MENU_ITEM = "complexMenuItem";


    public static final String MENU_ITEM = "menuItem";
    public static final String TITLE = "title";

    // menu item content
    public static final String NAME = "name";
    public static final String FOTO = "foto";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String RATIO = "ratio";

    public static final String MENU_ITEM_VARIANT = "menuItemVariant";
    public static final String PRICE_FOR_EXACT_TYPE = "menuItemVariant";

    //attribute constants

    public static final String VERSION = "version";

    public static final String WEIGHT = "weight";
    public static final String UNIT = "unit";
    public static final String PATH = "path";
}
/*

<menuItemVariant variantNumber="1">c курицей</menuItemVariant>
<menuItemVariant variantNumber="2">c грибами</menuItemVariant>
</description>
<ratio unit="грамм">120 1</ratio>
<price>
<priceForExactType variantNumber="1">145</priceForExactType>
<priceForExactType variantNumber="2">155</priceForExactType>
</price>
</menuItem>
</complexMenuItem>*/
