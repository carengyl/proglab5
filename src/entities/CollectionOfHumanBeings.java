package entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import util.OutputUtil;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * Collection data structure
 *
 * @author carengyl
 */
@XStreamAlias("collection")
public class CollectionOfHumanBeings {
    /**
     * File name field
     */
    private Path fileName;
    /**
     * Initialization date field
     */
    private final LocalDate initDate;
    /**
     * Hashmap of {@code HumanBeing}
     */
    @XStreamImplicit
    private final HashMap<Long, HumanBeing> humanBeings;

    /**
     * Constructs new instance with given path
     *
     * @param fileName path to file
     */
    public CollectionOfHumanBeings(Path fileName) {
        this.fileName = fileName;
        humanBeings = new HashMap<>();
        initDate = LocalDate.now();
    }

    /**
     * Constructs new instance with given path and existing hashmap
     *
     * @param fileName path to file
     * @param humanBeings parsed {@code Hashmap<Long, HumanBeing>}
     */
    public CollectionOfHumanBeings(Path fileName, HashMap<Long,HumanBeing> humanBeings) {
        this.fileName = fileName;
        this.initDate = LocalDate.now();
        this.humanBeings = humanBeings;
        HumanBeing.setCurrentId(getMaxID());
    }

    /**
     * @return max ID of {@code HumanBeing} from collection
     */
    private long getMaxID() {
        long maxId = 1;
        for (long key: humanBeings.keySet()) {
            if (humanBeings.get(key).getId() > maxId) {
                maxId = humanBeings.get(key).getId();
            }
        }
        return maxId;
    }

    /**
     * @return path to file
     */
    public Path getFileName() {
        return fileName;
    }

    /**
     * @param fileName new File name
     */
    public void setFileName(Path fileName) {
        this.fileName = fileName;
    }

    /**
     * @return Initialization date
     */
    public String getInitDate() {
        return initDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * @param id given id
     * @return true if given id is in collection
     */
    public boolean checkForId(long id) {
        for (long key: humanBeings.keySet()) {
            if (humanBeings.get(key).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds new {@code HumanBeing} to collection
     *
     * @param key {@code long} key
     * @param humanBeing parsed {@code HumanBeing}
     */
    public void addByKey(long key, HumanBeing humanBeing) {
        humanBeings.put(key, humanBeing);
    }

    /**
     * @return collection
     */
    public HashMap<Long, HumanBeing> getHumanBeings() {
        return humanBeings;
    }

    /**
     * Replaces {@code HumanBeing} by given id with new {@code HumanBeing}
     *
     * @param id id of old HumanBeing
     * @param humanBeing new parsed HumanBeing
     */
    public void updateById(long id, HumanBeing humanBeing) {
        for (long key: humanBeings.keySet()) {
            if (Objects.equals(humanBeings.get(key).getId(), id)) {
                humanBeings.replace(key, humanBeing);
                return;
            }
        }
    }

    /**
     * Removes all {@code HumanBeing} from collection, if their key is lower than given
     *
     * @param greaterKey given key
     */
    public void removeLowerKey(long greaterKey) {
        Set<Long> keysCopy = Set.copyOf(humanBeings.keySet());
        for(long key: keysCopy) {
            if (key < greaterKey) {
                this.removeByKey(key);
            }
        }
    }

    /**
     * Removes instance from collection by given key
     *
     * @param key given key
     */
    public void removeByKey(long key) {
        humanBeings.remove(key);
        OutputUtil.printSuccessfulMessage("Deleted element by key: " + key);
    }
}
