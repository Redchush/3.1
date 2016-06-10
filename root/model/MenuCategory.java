package root.model;


import root.model.menu_items.MenuItemComponent;

import java.util.List;


public class MenuCategory {
    private String name;
    private List<MenuItemComponent> components;

    public MenuCategory(String name){
        this.name = name;
    }

    public MenuCategory(String name, List<MenuItemComponent> components) {
        this.name = name;
        this.components = components;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItemComponent> getComponents() {
        return components;
    }

    public void setComponents(List<MenuItemComponent> components) {
        this.components = components;
    }

    public void addComponent(MenuItemComponent component){
        components.add(component);
    }
    protected void removeComponent(MenuItemComponent component){
        components.remove(component);
    }

    @Override
    public String toString() {
        return "MenuCategory{" +
                "name='" + name + "\'\n" +
                ", components=" + components +
                '}'+ '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuCategory category = (MenuCategory) o;

        if (name != null ? !name.equals(category.name) : category.name != null) {
            return false;
        }
        return components != null ? components.equals(category.components) : category.components == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (components != null ? components.hashCode() : 0);
        return result;
    }


}
