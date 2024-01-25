package br.com.rsfot.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TVShowData(
        @JsonAlias("Title") String title,
        @JsonAlias("totalSeasons") int seasons,
        @JsonAlias("imdbRating") String rating) {
}
