import java.util.LinkedList;

class HashTable {
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private LinkedList<DataItem>[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        table = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hashFunc(int key) {
        return key % size;
    }

    public void put(int key, String value) {
        int hash = hashFunc(key);
        LinkedList<DataItem> chain = table[hash];

        for (DataItem item : chain) {
            if (item.getKey() == key) {
                item.setValue(value);
                return;
            }
        }

        DataItem newItem = new DataItem(key, value);
        chain.add(newItem);

        double loadFactor = (double) countItems() / size;
        if (loadFactor > LOAD_FACTOR_THRESHOLD) {
            resize();
        }
    }

    public String get(int key) {
        int hash = hashFunc(key);
        LinkedList<DataItem> chain = table[hash];

        for (DataItem item : chain) {
            if (item.getKey() == key) {
                return item.getValue();
            }
        }

        return null;
    }

    public void delete(int key) {
        int hash = hashFunc(key);
        LinkedList<DataItem> chain = table[hash];

        for (DataItem item : chain) {
            if (item.getKey() == key) {
                chain.remove(item);
                return;
            }
        }
    }

    private void resize() {
        int newSize = size * 2;
        LinkedList<DataItem>[] newTable = new LinkedList[newSize];

        for (int i = 0; i < newSize; i++) {
            newTable[i] = new LinkedList<>();
        }

        for (LinkedList<DataItem> chain : table) {
            for (DataItem item : chain) {
                int newHash = hashFunc(item.getKey()) % newSize;
                LinkedList<DataItem> newChain = newTable[newHash];
                newChain.add(item);
            }
        }

        table = newTable;
        size = newSize;
    }

    private int countItems() {
        int count = 0;
        for (LinkedList<DataItem> chain : table) {
            count += chain.size();
        }
        return count;
    }
}
