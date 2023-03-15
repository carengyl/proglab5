package entities;

import java.util.Collections;
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

    public boolean checkForId(long id) {
        for (long key: humanBeings.keySet()) {
            if (humanBeings.get(key).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void addByKey(long key, HumanBeing humanBeing) {
        humanBeings.put(key, humanBeing);
    }

    public HashMap<Long, HumanBeing> getHumanBeings() {
        return humanBeings;
    }

    public void updateById(long id, HumanBeing humanBeing) {
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

    public long generateKey() {
        for (Object key: humanBeings.keySet().stream().sorted().toArray()) {
            if (!humanBeings.containsKey((long) key + 1)) {
                return (long) key + 1;
            }
        }
        return Collections.max(humanBeings.keySet()) + 1;
    }

    public void removeByKey(long key) {
        humanBeings.remove(key);
    }
}
