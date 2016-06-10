package root.model;

import java.util.ArrayList;
import java.util.List;


public class Menu {
    private List<MenuCategory> categories;
    private String name;
    private String version;

    public Menu() {
        this.categories = new ArrayList<>();
    }

    public Menu(List<MenuCategory> categories, String name, String version) {
        this.categories = categories;
        this.name = name;
        this.version = version;
    }

    public List<MenuCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<MenuCategory> categories) {
        this.categories = categories;
    }

    public void addCategory(MenuCategory category){
        if (category != null) categories.add(category);
    }

    public void addCategory(MenuCategory category, int index){
        categories.add(index, category);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "categories=" + categories +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
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

        Menu menu = (Menu) o;

        if (categories != null ? !categories.equals(menu.categories) : menu.categories != null) {
            return false;
        }
        if (name != null ? !name.equals(menu.name) : menu.name != null) {
            return false;
        }
        return version != null ? version.equals(menu.version) : menu.version == null;

    }

    @Override
    public int hashCode() {
        int result = categories != null ? categories.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
