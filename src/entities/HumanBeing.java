package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HumanBeing {
    private static Long currentId = 1L;
    private final Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero; //Поле не может быть null
    private boolean hasToothpick;
    private Double impactSpeed; //Значение поля должно быть больше -611, Поле может быть null
    private Double minutesOfWaiting; //Поле может быть null
    private WeaponType weaponType; //Поле не может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле может быть null

    public HumanBeing() {
        creationDate = LocalDate.now();
        id = currentId++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setRealHero(Boolean realHero) {
        this.realHero = realHero;
    }

    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    public void setImpactSpeed(Double impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public void setMinutesOfWaiting(Double minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + this.id
                + "; Имя: " + this.name
                + "; Координаты: " + this.coordinates
                + "; Создан: " + creationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                + ";" + ((this.realHero) ? "Герой" : "Злодей")
                + ((this.hasToothpick) ? " С зубочисткой во рту" : "")
                + "; Скорость удара: " + ((this.impactSpeed == null) ? "неизвестна" : this.impactSpeed)
                + "Время ожидания: " + ((this.minutesOfWaiting == null) ? "неизвестно" : this.minutesOfWaiting)
                + "Настроение: " + this.mood
                + "Машина: " + ((this.car == null) ? "отсутствует": this.car);
    }
}
