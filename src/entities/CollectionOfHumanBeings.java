package entities;

import exceptions.IdNotFoundException;

import java.util.HashMap;
import java.util.Objects;

public class CollectionOfHumanBeings {
    private final String fileName;
    private final HashMap<Long, HumanBeing> humanBeings;

    public CollectionOfHumanBeings(String fileName) {
        this.fileName = fileName;
        humanBeings = new HashMap<>();
    }

    public String getFileName() {
        return fileName;
    }

    public void addHumanBeing(HumanBeing humanBeing) {
        humanBeings.put(humanBeing.getId(), humanBeing);
    }

    public void addByKey(HumanBeing humanBeing, long key) {
        humanBeings.put(key, humanBeing);
    }

    public HashMap<Long, HumanBeing> getHumanBeings() {
        return humanBeings;
    }

    public void removeById(long id) throws IdNotFoundException {
        for (long key: humanBeings.keySet()) {
            if (Objects.equals(humanBeings.get(key).getId(), id)) {
                humanBeings.remove(key);
                return;
            }
        }
        throw new IdNotFoundException();
    }

    public void updateById(long id, HumanBeing humanBeing) throws IdNotFoundException {
        for (long key: humanBeings.keySet()) {
            if (Objects.equals(humanBeings.get(key).getId(), id)) {
                humanBeings.replace(key, humanBeing);
                return;
            }
        }
    }

    public void removeLowerKey(long greaterKey) {
        for (long key: humanBeings.keySet()) {
            if (key < greaterKey) {
                humanBeings.remove(key);
                System.out.println("Удален элемент с ключом: " + key);
            }
        }
    }

    public HashMap<Long, HumanBeing> getCollection() {
        return humanBeings;
    }
}
