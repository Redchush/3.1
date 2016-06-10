package domain.model.menu_items;

import java.io.File;

/**
 * This class is the core of Composite pattern to provide
 * oppotunity deal  with both complex and simple components
 * by common interface
 */
public abstract class MenuItemComponent {

    private File foto;
    private String name;
    private Ratio ratio;
    private String id;
    private final static File defaultFoto = new File("\\images\\no_image.jpg");

    public MenuItemComponent(String name) {
        this.name = name;
    }

    public File getFoto() {
        return foto;
    }
    public void setFoto(File foto) {
        if (foto == null){
            this.foto = defaultFoto;
        } else this.foto = foto;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Ratio getRatio() {
        return ratio;
    }
    public void setRatio(Ratio ratio) {
        this.ratio = ratio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static File getDefaultFoto() {
        return defaultFoto;
    }

    @Override
    public String toString() {
        return "MenuItemComponent{" +
                "foto=" + foto +
                ", name='" + name + '\'' +
                ", ratio=" + ratio;
    }

    public abstract String getDescription();
    public abstract void setDescription(String description);
    public abstract String getPrice();
    public abstract void setPrice(String price);

}
