package root.model.menu_items;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ratio ratio1 = (Ratio) o;

        return ratio != null ? ratio.equals(ratio1.ratio) : ratio1.ratio == null;
    }

    @Override
    public int hashCode() {
        return ratio != null ? ratio.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Ratio{" +
                "ratio=" + ratio +
                '}';
    }
}
