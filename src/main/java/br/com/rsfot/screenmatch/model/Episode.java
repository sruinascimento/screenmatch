package br.com.rsfot.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private String title;
    private Integer season;
    private Integer episode;
    private Double rating;
    private LocalDate released;

    public Episode(String title, Integer episode, String rating, String released) {
        this.title = title;
        this.episode = episode;
        this.rating = initializeRating(rating);
        this.released = initializeReleased(released);
    }

    public String getTitle() {
        return title;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public Double getRating() {
        return rating;
    }

    public LocalDate getReleased() {
        return released;
    }

    private Double initializeRating(String ratingString) {
        Double rating;
        try {
            rating =  Double.valueOf(ratingString);
        } catch (NumberFormatException e) {
            rating = 0.0;
        }
        return rating;
    }

    private LocalDate initializeReleased(String releasedString) {
        LocalDate released;
        try {
            released = LocalDate.parse(releasedString);
        } catch (DateTimeParseException e) {
            released = null;
        }

        return  released;
    }
    @Override
    public String toString() {
        return  "title='" + title + '\'' +
                ", season=" + season +
                ", episode=" + episode +
                ", rating=" + rating +
                ", released=" + released;
    }
}
