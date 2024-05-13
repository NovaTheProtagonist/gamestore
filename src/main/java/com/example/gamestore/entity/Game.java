package com.example.gamestore.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "long_description")
    private String longDescription;
    @NonNull
    private String thumbnail;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "game_images")
    private List<String> images;
    @Column(name = "game_categories")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = GameCategory.class, fetch = FetchType.EAGER)
    private List<GameCategory> gameCategories;
    @OneToMany
    private List<Achievement> achievements;
    @OneToMany
    private List<Review> reviews;

    public enum GameCategory {
        ACTION,
        ADVENTURE,
        RPG,
        FPS,
        RTS,
        SIMULATION,
        SPORTS,
        PUZZLE,
        PLATFORMER,
        FIGHTING,
        HORROR,
        SURVIVAL,
        MMORPG,
        MOBA,
        STRATEGY,
        RACING,
        SHOOTER,
        SANDBOX,
        STEALTH,
        ARCADE,
        INDIE,
        EDUCATIONAL,
        TRIVIA,
        CARD,
        BOARD,
        PARTY,
        MUSIC,
        DANCE,
        RHYTHM,
        VR,
        MMORTS,
        TACTICAL,
        MMOFPS,
        TOWER_DEFENSE,
        MMOARPG,
        WARGAME,
        BATTLE_ROYALE,
        SURVIVAL_HORROR,
        OPEN_WORLD,
        TEXT_BASED,
        PIXEL_ART,
        HACK_AND_SLASH,
        COUCH_COOP,
        VRMMO,
        PUZZLE_PLATFORMER,
        SCI_FI,
        FANTASY,
        SINGLEPLAYER,
        MULTIPLAYER,
        GACHA
    }
}
