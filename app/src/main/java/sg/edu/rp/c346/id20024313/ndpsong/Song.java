package sg.edu.rp.c346.id20024313.ndpsong;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(String title, String singers, int year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        String starNumber = "";
        if (stars == 1) {
            starNumber = "*";
        } else if (stars == 2) {
            starNumber = "**";
        } else if (stars == 3) {
            starNumber = "***";
        } else if (stars == 4) {
            starNumber = "****";
        } else if (stars == 5) {
            starNumber = "*****";
        }
            return title + "\n"
                    + singers + "-"
                    + year + "\n"
                    + starNumber;
        }
    }

