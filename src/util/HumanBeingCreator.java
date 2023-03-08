package util;

import entities.*;

import java.util.Scanner;

public class HumanBeingCreator {
    private final HumanBeing createdHumanBeing;
    private final Scanner scanner = new Scanner(System.in);

    public HumanBeingCreator() {
        createdHumanBeing = new HumanBeing();
    }

    public HumanBeingCreator(long id) {
        createdHumanBeing = new HumanBeing(id);
    }

    private void setName() {
        String name = Validators.validateStringInput("Введите имя:", false, scanner);
        createdHumanBeing.setName(name);
    }

    private void setCoordinates() {
        Coordinates coordinates = new Coordinates(setX(), setY());
        createdHumanBeing.setCoordinates(coordinates);
    }

    private int setY() {
        return Validators.validateInput(arg -> ((int) arg) > Coordinates.getMIN_Y(),
                "Введите целую координату Y, она должна быть больше " + Coordinates.getMIN_Y() + ":",
                "Ошибка обработки, ожидалось целое число, повторите ввод:",
                "Координата Y должна быть больше " + Coordinates.getMIN_Y() + ", повторите ввод:",
                Integer::parseInt,
                false,
                scanner);
    }

    private int setX() {
        return Validators.validateInput(arg -> true,
                "Введите целую координату X:",
                "Ошибка обработки, ожидалось целое число, повторите ввод:",
                "Непредвиденная ошибка",
                Integer::parseInt,
                false,
                scanner);
    }

    private void setRealHero() {
        boolean realHero = Validators.validateBooleanInput("Он/она герой", scanner);
        createdHumanBeing.setRealHero(realHero);
    }

    private void setHasToothpick() {
        boolean hasToothpick = Validators.validateBooleanInput("С зубочисткой", scanner);
        createdHumanBeing.setHasToothpick(hasToothpick);
    }

    private void setImpactSpeed() {
        Double impactSpeed = Validators.validateInput(arg -> ((Double) arg) > HumanBeing.getMinImpactSpeed(),
                "Введите вещественную скорость удара в м/с, скорость удара должна быть больше " + HumanBeing.getMinImpactSpeed() + " (для пропуска нажмите ENTER):",
                "Ошибка обработки, ожидалось вещественное число, повторите ввод:",
                "Скорость удара должна быть больше " + HumanBeing.getMinImpactSpeed() + " повторите ввод:",
                Double::parseDouble,
                true,
                scanner);
        createdHumanBeing.setImpactSpeed(impactSpeed);
    }

    private void setMinutesOfWaitingSpeed() {
        Double minutesOfWaiting = Validators.validateInput(arg -> true,
                "Введите вещественную время ожидания (для пропуска нажмите ENTER):",
                "Ошибка обработки, ожидалось вещественное число, повторите ввод:",
                "Непредвиденная ошибка, повторите ввод:" + HumanBeing.getMinImpactSpeed(),
                Double::parseDouble,
                true,
                scanner);
        createdHumanBeing.setMinutesOfWaiting(minutesOfWaiting);
    }

    private void setWeaponType() {
        int weaponTypeNumber = Validators.validateEnumInput("Выберите оружие:\n" + WeaponType.show(),
                WeaponType.values().length,
                false,
                scanner);
        createdHumanBeing.setWeaponType(WeaponType.values()[weaponTypeNumber]);
    }

    private void setMood() {
        int moodNumber = Validators.validateEnumInput("Выберите настроение:\n" + Mood.show(),
                Mood.values().length,
                false,
                scanner);
        createdHumanBeing.setMood(Mood.values()[moodNumber]);
    }

    private void setCar() {
        String carName = Validators.validateStringInput("Введите название машины (для пропуска создания машины нажмите ENTER):",
                true,
                scanner);
        if (carName == null) {
            createdHumanBeing.setCar(null);
        } else {
            int cost = setCarCost();
            int horsePower = setCarHorsePowers();
            CarBrand carBrand = setCarBrand();
            createdHumanBeing.setCar(new Car(carName, cost, horsePower, carBrand));
        }
    }

    private int setCarCost() {
        return Validators.validateInput(arg -> ((int) arg) > 0,
                "Введите стоимость машины в тугриках (целая, больше нуля):",
                "Ошибка обработки, ожидалось целое число, повторите ввод:",
                "Стоимость должна быть больше нуля, повторите ввод:",
                Integer::parseInt,
                false,
                scanner);
    }

    private int setCarHorsePowers() {
        return Validators.validateInput(arg -> ((int) arg) > 0,
                "Введите мощность машины в л.с. (целая, больше нуля):",
                "Ошибка обработки, ожидалось целое число, повторите ввод:",
                "Мощность должна быть больше нуля, иначе это не машина, а лада калина, повторите ввод:",
                Integer::parseInt,
                false,
                scanner);
    }

    private CarBrand setCarBrand() {
        return CarBrand.values()[
                Validators.validateEnumInput("Выберите марку машины:\n" + CarBrand.show(),
                CarBrand.values().length,
                false,
                scanner)
                ];
    }

    public void setVariables() {
        this.setName();
        this.setCoordinates();
        this.setRealHero();
        this.setHasToothpick();
        this.setImpactSpeed();
        this.setMinutesOfWaitingSpeed();
        this.setWeaponType();
        this.setMood();
        this.setCar();
    }

    public HumanBeing getCreatedHumanBeing() {
        return createdHumanBeing;
    }
}
