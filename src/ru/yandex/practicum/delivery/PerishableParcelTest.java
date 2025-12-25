package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerishableParcelTest {

    @Test
    void isExpiredTest() {
        PerishableParcel parcel = new PerishableParcel("Посылка", 2,
                "Адрес", 12, 5);

        //12+5=17 16,17,18
        assertFalse(parcel.isExpired(16));
        assertFalse(parcel.isExpired(17));
        assertTrue(parcel.isExpired(18));
    }

    @Test
    void isExpiredZero() {
        PerishableParcel parcel = new PerishableParcel("Посылка", 2,
                "Адрес", 12, 0);

        assertFalse(parcel.isExpired(12));
        assertTrue(parcel.isExpired(13));
    }
}