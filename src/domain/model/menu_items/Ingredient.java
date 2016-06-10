package domain.model.menu_items;

/**
 * Created by user on 21.05.2016.
 */
public class Ingredient {

    private String weight;
    private String unit;

    public Ingredient(String weight, String unit) {
        this.weight = weight;
        this.unit = unit;
    }
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                ", weight='" + weight + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
