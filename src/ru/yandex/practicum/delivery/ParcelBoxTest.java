package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {
    ParcelBox<StandardParcel> standardBox = new ParcelBox<>(10);

    @Test //вес не превышен
    void addParcelWhenWeightNotExceededTest() {
        StandardParcel parcel1 = new StandardParcel("Посылка 1", 4, "Адрес 1", 1);
        StandardParcel parcel2 = new StandardParcel("Посылка 2", 3, "Адрес 2", 1);

        standardBox.addParcel(parcel1);
        List<StandardParcel> parcels = standardBox.getAllParcels();
        assertEquals(1, parcels.size());
        assertTrue(parcels.contains(parcel1));

        standardBox.addParcel(parcel2);
        parcels = standardBox.getAllParcels();
        assertEquals(2, parcels.size());
        assertTrue(parcels.contains(parcel2));
    }

    @Test //превышен
    void addParcelWhenWeightExceededTest() {
        StandardParcel parcel1 = new StandardParcel("Посылка 1", 4, "Адрес 1", 1);
        StandardParcel parcel2 = new StandardParcel("Посылка 2", 7, "Адрес 2", 1);

        standardBox.addParcel(parcel1);
        standardBox.addParcel(parcel2);

        List<StandardParcel> parcels = standardBox.getAllParcels();
        assertEquals(1, parcels.size());
        assertTrue(parcels.contains(parcel1));
        assertFalse(parcels.contains(parcel2));
    }

    @Test //равен макс коробки
    void AddParcelExactWeightTest() {
        StandardParcel parcel1 = new StandardParcel("Посылка 1", 5, "Адрес 1", 1);
        StandardParcel parcel2 = new StandardParcel("Посылка 2", 5, "Адрес 2", 1);

        standardBox.addParcel(parcel1);
        standardBox.addParcel(parcel2);
        assertEquals(2, standardBox.getAllParcels().size());
    }
}