package domain.model;

import java.util.List;


public class Menu {
    private List<MenuCategory> categories;
    private String name;
    private String version;

    public Menu(List<MenuCategory> categories, String name, String version) {
        this.categories = categories;
        this.name = name;
        this.version = version;
    }

    public List<MenuCategory> getCategories() {
        return categories;
    }
    public void addCategory(MenuCategory category){
        categories.add(category);
    }
    public void addCategory(MenuCategory category, int index){
        categories.add(index, category);
    }

    public Menu(List<MenuCategory> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "categories=" + categories +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
