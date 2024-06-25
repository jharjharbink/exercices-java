package org.example.exo.films;
import java.time.LocalDate;

public class Film {
    private String title = "";
    private String realisator = "";
    private LocalDate release_year;
    private String type = "";

    public Film(String title, String realisator, LocalDate release_year, String type) {
        this.title = title;
        this.realisator = realisator;
        this.release_year = release_year;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRealisator() {
        return realisator;
    }

    public void setRealisator(String realisator) {
        this.realisator = realisator;
    }

    public LocalDate getRelease_year() {
        return release_year;
    }

    public void setRelease_year(LocalDate release_year) {
        this.release_year = release_year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Films{" +
                "title='" + title + '\'' +
                ", realisator='" + realisator + '\'' +
                ", release_year='" + release_year + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
