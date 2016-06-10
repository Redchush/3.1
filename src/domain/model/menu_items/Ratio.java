package domain.model.menu_items;

import java.util.List;

/**
 * Created by user on 27.05.2016.
 */
public class Ratio {
    private List<Ingredient> ratio;

    public Ratio(List<Ingredient> ratio) {
        this.ratio = ratio;
    }

    public List<Ingredient> getRatio() {
        return ratio;
    }

    public void setRatio(List<Ingredient> ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "Ratio{" +
                "ratio=" + ratio +
                '}';
    }
}
