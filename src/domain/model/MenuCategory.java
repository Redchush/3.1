package domain.model;

import domain.model.menu_items.MenuItemComponent;

import java.util.List;

/**
 * Created by user on 21.05.2016.
 */
public class MenuCategory {
    private String name;
    private List<MenuItemComponent> components;

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
                "name='" + name + '\n' +
                ", components=" + components +
                '}';
    }
}
