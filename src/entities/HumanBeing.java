package entities;

import java.time.LocalDate;

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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
    }
}
