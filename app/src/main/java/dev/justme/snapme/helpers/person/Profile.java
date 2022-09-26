package dev.justme.snapme.helpers.person;


import java.util.List;
import java.util.UUID;

public class Profile {
    public Integer age;
    public String name;
    public String profilePictureUrl;
    public List<String> images;
    public Location location;
    public int friends;


    public void factory(Integer age, String name, String profilePictureUrl, List<String> images, Location location, int friends) {
        this.age = age;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.images = images;
        this.location = location;
        this.friends = friends;
    }

    public void fetch(UUID uuid) {
        // TODO: get profile from backend
    }
}
