package dev.justme.snapme.helpers;

public class Profile {
    private final String uuid;
    private final String name;
    private final long birthdayTimestamp;
    private final String[] pictureUrls;
    private final int friends;
    private final String bio;
    private final String[] interests;

    public Profile(String uuid, String name, String bio, long birthdayTimestamp, int friends, String[] pictureUrls, String[] interests) {
        this.uuid = uuid;
        this.name = name;
        this.birthdayTimestamp = birthdayTimestamp;
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

    public long getBirthdayTimestamp() {
        return birthdayTimestamp;
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
