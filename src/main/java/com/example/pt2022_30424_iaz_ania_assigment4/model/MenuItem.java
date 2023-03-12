package com.example.pt2022_30424_iaz_ania_assigment4.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class MenuItem  implements Serializable {
    @Serial
    public abstract String title();

    public abstract int computePrice();

    public abstract double computeRating();

    public abstract int computeProtein();

    public abstract int computeFat();

    public abstract int computeSodium();

    public abstract int computeCalories();

    @Override
    public int hashCode() {
        return Objects.hashCode(title());
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MenuItem)){
            return false;
        }
        return title().equals(((MenuItem)obj).title());
    }
}
