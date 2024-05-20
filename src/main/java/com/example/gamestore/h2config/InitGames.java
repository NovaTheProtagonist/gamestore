package com.example.gamestore.h2config;

import java.util.List;

import com.example.gamestore.entity.Game;
import com.example.gamestore.repository.GameRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitGames implements CommandLineRunner {

    private GameRepository gameRepository;

    @Override
    public void run(String... args) throws Exception {
        initGames();
    }

    @Transactional
    private void initGames() {
        Game game = new Game();
        game.setName("gametest");
        game.setPrice(10f);
        game.setGameCategories(List.of(Game.GameCategory.ACTION));
        game.setThumbnail("image1");
        gameRepository.save(game);
    }
}
