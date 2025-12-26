package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();

    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(50);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(50);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(50);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addParcel();
                case 2 -> sendParcels();
                case 3 -> calculateCosts();
                case 4 -> trackParcels();
                case 5 -> showBoxContents();
                case 0 -> running = false;
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Обновить трекинг");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 - Стандартная посылка");
        System.out.println("2 - Хрупкая посылка");
        System.out.println("3 - Скоропортящаяся посылка");
        int choice = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите описание посылки:");
        String description = scanner.nextLine();

        System.out.println("Введите вес посылки:");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите адрес доставки:");
        String deliveryAddress = scanner.nextLine();

        System.out.println("Введите день отправки(1-31):");
        int sendDay = Integer.parseInt(scanner.nextLine());


        switch (choice) {
            case 1 -> {
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(standardParcel);
                standardBox.addParcel(standardParcel);
                System.out.println("Посылка добавлена");
            }
            case 2 -> {
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(fragileParcel);
                trackableParcels.add(fragileParcel);
                fragileBox.addParcel(fragileParcel);
                System.out.println("Посылка добавлена");
            }
            case 3 -> {
                System.out.println("Введите срок годности посылки в днях:");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishableParcel = new PerishableParcel(description, weight,
                        deliveryAddress, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                perishableBox.addParcel(perishableParcel);
                System.out.println("Посылка добавлена");
            }
            default -> {
                System.out.println("Неверный выбор.");
            }
        }
    }

    private static void sendParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("Для начала добавьте посылку");
            return;
        }

        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        if (allParcels.isEmpty()) {
            System.out.println("Для начала добавьте посылку");
            return;
        }

        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            int cost = parcel.calculateDeliveryCost();
            totalCost += cost;
        }
        System.out.println("Общая стоимость всех доставок составляет: " + totalCost + '.');
    }

    private static void trackParcels() {
        if (trackableParcels.isEmpty()) {
            System.out.println("Нет посылок с поддержкой трекинга");
            return;
        }

        System.out.println("Введите местоположение:");
        String newLocation = scanner.nextLine();

        for (Trackable trackable : trackableParcels) {
            trackable.reportStatus(newLocation);
        }
    }

    private static <T extends Parcel> void boxContents(String name, List<T> parcelBoxes) {
        if (parcelBoxes.isEmpty()) {
            System.out.println("Коробка с посылками(" + name + ") пуста");
        } else {
            System.out.println("Содержание коробки с посылками(" + name + "):");
            for (Parcel parcel : parcelBoxes) {
                System.out.println("- " + parcel.getDescription());
            }
        }
    }

    private static void showBoxContents() {
        System.out.println("Выберите коробку");
        System.out.println("1 - Коробка со стандартными посылками");
        System.out.println("2 - Коробка с хрупкими посылками");
        System.out.println("3 - Коробка со скоропортящимися посылками");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> boxContents("стандартные", standardBox.getAllParcels());
            case 2 -> boxContents("хрупкие", fragileBox.getAllParcels());
            case 3 -> boxContents("скоропортящиеся", perishableBox.getAllParcels());
            default -> System.out.println("Неверный выбор");
        }

    }

}

