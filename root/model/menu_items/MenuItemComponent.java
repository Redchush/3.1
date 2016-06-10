package root.model.menu_items;

import java.io.File;

/**
 * This class is the core of Composite pattern to provide
 * oppotunity deal  with both complex and simple components
 * by common interface
 */
public abstract class MenuItemComponent {

    protected File foto;
    protected String name;
    protected Ratio ratio;
    protected String id;

    private final static File defaultFoto = new File("\\images\\no_image.jpg");

    public MenuItemComponent(String name) {
        this.name = name;
    }
    public MenuItemComponent(String name, String id) {
        this.name = name;
        this.id = id;
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



    public abstract String getDescription();

    public abstract String getPrice();

    @Override
    public String toString() {
        return "MenuItemComponent{" +
                "id=" + id +
                ", foto=" + foto +
                ", name='" + name + '\'' +
                ", ratio=" + ratio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuItemComponent that = (MenuItemComponent) o;

        if (foto != null ? !foto.equals(that.foto) : that.foto != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (ratio != null ? !ratio.equals(that.ratio) : that.ratio != null) {
            return false;
        }
        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        int result = foto != null ? foto.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ratio != null ? ratio.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
