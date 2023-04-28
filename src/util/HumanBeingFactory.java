package util;

import entities.*;

import java.util.Scanner;

/**
 * Class responsible for
 */
//TODO 27.04.2023 insert command from script
public class HumanBeingFactory {
    private final HumanBeing createdHumanBeing;
    private final Scanner scanner = new Scanner(System.in);

    public HumanBeingFactory(long id) {
        createdHumanBeing = new HumanBeing(id);
    }

    public HumanBeingFactory() {
        createdHumanBeing = new HumanBeing();
    }

    private void setName() {
        String name = Validators.validateStringInput("Enter human's name", false, scanner);
        createdHumanBeing.setName(name);
    }

    private void setCoordinates() {
        Coordinates coordinates = new Coordinates(setX(), setY());
        createdHumanBeing.setCoordinates(coordinates);
    }

    private Integer setY() {
        return Validators.validateInput(arg -> ((int) arg) > Coordinates.getMIN_Y(),
                "Enter integer (long) Y coordinate, it should be greater than " + Coordinates.getMIN_Y(),
                "Expected long type number, try again:",
                "Y coordinate should be greater than " + Coordinates.getMIN_Y() + ", try again:",
                Integer::parseInt,
                false,
                scanner);
    }

    private Integer setX() throws NullPointerException {
        return Validators.validateInput(arg -> true,
                "Enter integer (long) X",
                "Expected long type number, try again:",
                "",
                Integer::parseInt,
                false,
                scanner);
    }

    private void setRealHero() {
        boolean realHero = Validators.validateBooleanInput("Is he/she a real hero", scanner);
        createdHumanBeing.setRealHero(realHero);
    }

    private void setHasToothpick() {
        boolean hasToothpick = Validators.validateBooleanInput("Has a toothpick", scanner);
        createdHumanBeing.setHasToothpick(hasToothpick);
    }

    private void setImpactSpeed() {
        Double impactSpeed = Validators.validateInput(arg -> ((Double) arg) > HumanBeing.getMinImpactSpeed(),
                "Enter real (double) impact speed m/s, it should be greater than " + HumanBeing.getMinImpactSpeed(),
                "Expected double type, try again:",
                "Impact speed should be greater than " + HumanBeing.getMinImpactSpeed() + ", try again:",
                Double::parseDouble,
                true,
                scanner);
        createdHumanBeing.setImpactSpeed(impactSpeed);
    }

    private void setMinutesOfWaitingSpeed() {
        Double minutesOfWaiting = Validators.validateInput(arg -> true,
                "Enter real (double) minutes of waiting",
                "Expected double type, try again:",
                "",
                Double::parseDouble,
                true,
                scanner);
        createdHumanBeing.setMinutesOfWaiting(minutesOfWaiting);
    }

    private void setWeaponType() {
        Integer weaponTypeNumber = Validators.validateEnumInput("Pick a weapon:\n" + WeaponType.show(),
                WeaponType.values().length,
                false,
                scanner);
        createdHumanBeing.setWeaponType(WeaponType.values()[weaponTypeNumber]);
    }

    private void setMood() {
        Integer moodNumber = Validators.validateEnumInput("Pick a mood:\n" + Mood.show(),
                Mood.values().length,
                false,
                scanner);
        createdHumanBeing.setMood(Mood.values()[moodNumber]);
    }

    private void setCar() {
        String carName = Validators.validateStringInput("Enter car's name, if skipped, whole car creation will be skipped",
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

    private Integer setCarCost() {
        return Validators.validateInput(arg -> ((int) arg) > 0,
                "Enter car's cost in tugriks (positive integer)",
                "Expected integer type, try again:",
                "Car's cost must be positive number, try again:",
                Integer::parseInt,
                false,
                scanner);
    }

    private Integer setCarHorsePowers() {
        return Validators.validateInput(arg -> ((int) arg) > 0,
                "Enter car's engine power in h.p. (positive integer):",
                "Expected integer type, try again:",
                "Engine power must be positive number, try again:",
                Integer::parseInt,
                false,
                scanner);
    }

    private CarBrand setCarBrand() {
        Integer carBrandNumber = Validators.validateEnumInput("Pick a car brand:\n" + CarBrand.show(),
                CarBrand.values().length,
                false,
                scanner);
        return CarBrand.values()[carBrandNumber];
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
