package root.model.menu_items;

import java.util.List;

/**
 * Created by user on 21.05.2016.
 *
 */
public class MenuItemComposite extends MenuItemComponent {

    private List<MenuItemComponent> printedMenuItem;

    public MenuItemComposite(String id, String name, List<MenuItemComponent> printedMenuItem) {
        super(name, id);
        this.printedMenuItem = printedMenuItem;
    }

    public MenuItemComposite(String name, List<MenuItemComponent> printedMenuItem) {
        super(name);
        this.printedMenuItem = printedMenuItem;
    }

    @Override
    public String getDescription() {

        StringBuilder description = new StringBuilder("");
        int counter = 0;
        for (MenuItemComponent item : printedMenuItem) {
            String localDescription = item.getDescription();
            if (localDescription != null && localDescription.length() != 0) {
                description.append(counter++)
                        .append(". ")
                        .append(item.getDescription())
                        .append(" ");
            }
        }
        if (description.length() != 0)
            description.deleteCharAt(description.length() -1);
        return new String(description);
    }

    @Override
    public Ratio getRatio() {
        return super.getRatio();
    }

    @Override
    public void setRatio(Ratio ratio) {
        super.setRatio(ratio);
    }

    @Override
    public String getPrice() {
        StringBuilder allPrices = new StringBuilder("");
        int counter = 0;
        for (MenuItemComponent item : printedMenuItem) {
            allPrices.append(counter++)
                        .append(". ")
                        .append(item.getPrice())
                        .append(" ");
        }
        if (allPrices.length() != 0)
            allPrices.deleteCharAt(allPrices.length() -1);
        return new String(allPrices);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        MenuItemComposite that = (MenuItemComposite) o;

        return printedMenuItem != null ? printedMenuItem.equals(that.printedMenuItem) : that.printedMenuItem == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (printedMenuItem != null ? printedMenuItem.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuItemComposite{" + super.toString() +
                "printedMenuItem=" + printedMenuItem +
                ", description='" + getDescription() + '\'' +
                ", price='" + getPrice() + '\'' +
                '}';
    }
}
