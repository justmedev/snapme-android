package dev.justme.snapme.helpers;

import java.util.Date;

public class Profile {
    private String uuid;
    private final String name;
    private final Date birthday;
    private final String[] pictureUrls;
    private final int friends;
    private final String bio;
    private final String[] interests;

    public Profile(String uuid, String name, String bio, Date birthday, int friends, String[] pictureUrls, String[] interests) {
        this.uuid = uuid;
        this.name = name;
        this.birthday = birthday;
        this.pictureUrls = pictureUrls;
        this.friends = friends;
        this.bio = bio;
        this.interests = interests;
    }

    public Profile(String name, String bio, Date birthday, int friends, String[] pictureUrls, String[] interests) {
        this.name = name;
        this.birthday = birthday;
        this.pictureUrls = pictureUrls;
        this.friends = friends;
        this.bio = bio;
        this.interests = interests;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String[] getPictureUrls() {
        return pictureUrls;
    }

    public int getFriends() {
        return friends;
    }

    public String getBio() {
        return bio;
    }

    public String[] getInterests() {
        return interests;
    }
}
