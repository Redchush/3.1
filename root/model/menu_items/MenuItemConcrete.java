package root.model.menu_items;

/**
 * Created by user on 21.05.2016.
 */
public class MenuItemConcrete extends MenuItemComponent {

    private String description="";
    private String price="";

    /**
     * for distigushing variats in complex menu item,
     * String was chosen for maintaining opportunity
     * to marking variants with letters, such as a) b) c) and other
     */
    private String variantNumber = "1";

    public MenuItemConcrete(String id, String name, String description, String price) {
        super(name, id);
        this.description = description;
        this.price = price;
    }

    public MenuItemConcrete(String name, String description, String price) {
        super(name);
        this.description = description;
        this.price = price;
    }

    public MenuItemConcrete(String id, String name, String description, String price, String variantNumber) {
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
    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "MenuItemConcrete{" +
                super.toString() +
                "description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", variantNumber='" + variantNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuItemConcrete that = (MenuItemConcrete) o;

        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (price != null ? !price.equals(that.price) : that.price != null) {
            return false;
        }
        return variantNumber != null ? variantNumber.equals(that.variantNumber) : that.variantNumber == null;

    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (variantNumber != null ? variantNumber.hashCode() : 0);
        return result;
    }
}
