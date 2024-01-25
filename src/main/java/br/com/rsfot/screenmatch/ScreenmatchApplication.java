package br.com.rsfot.screenmatch;

import br.com.rsfot.screenmatch.model.*;
import br.com.rsfot.screenmatch.service.ConsumeApiService;
import br.com.rsfot.screenmatch.util.ConverterData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String json = ConsumeApiService.getData("https://www.omdbapi.com/?t=Game+of+Thrones&apikey=");

        ConverterData converterData = new ConverterData();
        TVShowData tvShow = converterData.getData(json, TVShowData.class);


//		json = ConsumeApiService.getData("https://www.omdbapi.com/?t=Game+of+Thrones&season=1&episode=1&apikey=8e02932d");

        List<SeasonData> seasons = new ArrayList<>();
        int totalSeasons = tvShow.seasons();
        for (int seasonNumber = 1; seasonNumber <= totalSeasons; seasonNumber++) {
            json = ConsumeApiService.getData("https://www.omdbapi.com/?t=Game+of+Thrones&season=%d&apikey=8e02932d".formatted(seasonNumber));
            SeasonData season = converterData.getData(json, SeasonData.class);
            seasons.add(season);
        }



//		System.out.println("Exibindo os títulos de cada episódio");
//		seasons.forEach(season -> season.episodes().forEach(episode -> System.out.println(episode.title())));

//		seasons.stream()
//				.flatMap(season -> season.episodes().stream())
//				.filter(episode -> !"N/A".equalsIgnoreCase(episode.rating()))
//				.sorted(Comparator.comparing(EpisodeData::rating).reversed())
//				.limit(5)
//				.forEach(System.out::println);


        List<Episode> episodes = seasons.stream()
                .flatMap(seasonData -> seasonData.episodes().stream().map(episode -> {
                    Episode model = episode.toModel();
                    model.setSeason(seasonData.season());
                    return model;
                }))
                .toList();


        System.out.println("Exibindo episódios lançados antes de 2014");

        LocalDate dataLimite = LocalDate.of(2014, 1, 1);

        episodes.stream()
                .filter(episode -> episode != null && episode.getReleased().isBefore(dataLimite))
                .forEach(System.out::println);


    }
}
