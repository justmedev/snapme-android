package dev.justme.snapme.helpers;

import dev.justme.snapme.helpers.person.Profile;

public class DataManager {
    private static DataManager instance;
    public Profile mySelf = new Profile();

    private DataManager() {}

    public static synchronized DataManager getDataManager() {
        if(instance == null) instance = new DataManager();
        return instance;
    }
}
