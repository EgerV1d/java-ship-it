package ru.yandex.practicum.delivery;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParcelTest {

    @Test
    void testCalculateDeliveryCost() {
        StandardParcel standardParcel = new StandardParcel("Посылка 1", 5,
                "Адрес 1", 15);
        FragileParcel fragileParcel = new FragileParcel("Посылка 2", 3,
                "Адрес 2", 10);
        PerishableParcel perishableParcel = new PerishableParcel("Посылка 3", 2,
                "Адрес 3", 12, 5);

        assertEquals(10, standardParcel.calculateDeliveryCost());
        assertEquals(12, fragileParcel.calculateDeliveryCost());
        assertEquals(6, perishableParcel.calculateDeliveryCost());
    }

    @Test
    void testCalculateDeliveryCostZero() {
        StandardParcel standardParcel = new StandardParcel("Посылка 1", 0,
                "Адрес 1", 1);
        FragileParcel fragileParcel = new FragileParcel("Посылка 2", 0,
                "Адрес 2", 1);
        PerishableParcel perishableParcel = new PerishableParcel("Посылка 3", 0,
                "Адрес 3", 1, 30);

        assertEquals(0, standardParcel.calculateDeliveryCost());
        assertEquals(0, fragileParcel.calculateDeliveryCost());
        assertEquals(0, perishableParcel.calculateDeliveryCost());
    }

}