package domain.model.menu_items;

/**
 * Created by user on 21.05.2016.
 */
class MenuItemConcrete extends MenuItemComponent {
    private String description="";
    private String price="";

    /**
     * for distigushing variats in complex menu item,
     * String was chosen for maintaining opportunity
     * to marking variants with letters, such as a) b) c) and other
     */
    private String variantNumber = "1";

    public MenuItemConcrete(String name, String description, String price) {
        super(name);
        this.description = description;
        this.price = price;
    }

    public MenuItemConcrete(String name, String description, String price, String variantNumber) {
        super(name);
        this.description = description;
        this.price = price;
        this.variantNumber = variantNumber;
    }

    public String getVariantNumber() {
        return variantNumber;
    }

    public void setVariantNumber(String variantNumber) {
        this.variantNumber = variantNumber;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getPrice() {
        return price;
    }

    @Override
    public void setPrice(String price) {
        this.price = price;
    }
    @Override
    public String toString() {
      return super.toString() + " description= " + getDescription() + " price= " + getPrice() + " }\n";
    }

    @Override
    public String getId() {
        return super.getId();
    }
}
