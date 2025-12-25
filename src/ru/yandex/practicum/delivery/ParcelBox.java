package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {

    private List<T> parcelBoxes = new ArrayList<>();
    private int maxWeight;
    private int currentWeight = 0;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() > maxWeight) {
            System.out.println("Привышен лимит веса");
            return;
        }
        parcelBoxes.add(parcel);
        currentWeight += parcel.getWeight();
        System.out.println("Посылка добавлена в коробку");
    }

    public List<T> getAllParcels() {
        return parcelBoxes;
    }
}
