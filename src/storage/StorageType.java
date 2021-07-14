package storage;

public enum  StorageType {
    PRODUCTS("products"),
    COINS("coins");

    String name;

    StorageType(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
