import java.util.function.Predicate;

public interface IList {

    /**
     * adds value into list tail
     * */
    void add(int value);

    /**
     * adds value into list by index
     * @param idx
     * @param value
     */
    void add(int idx, int value);

    /**
     * removes element by index
     * @param idx
     */
    void remove(int idx);

    /**
     * removes element by condition
     * @param predicate
     */
    void remove(Predicate<Integer> predicate);

    /**
     * returns element by index
     * @param idx
     * @return
     */
    int get(int idx);
    int size ();
}