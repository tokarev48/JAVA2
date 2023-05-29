public class Main {
    public static void main(String[] args) {
        HashTable hashtable = new HashTable(10);

        hashtable.put(1, "Саша");
        hashtable.put(2, "Гриша");
        hashtable.put(11, "Максим");

        // Получение значения по ключу
        String value1 = hashtable.get(1);
        String value2 = hashtable.get(2);
        String value3 = hashtable.get(11);

        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);

        hashtable.delete(2);

        String removedValue = hashtable.get(2);
        System.out.println(removedValue);
    }
}