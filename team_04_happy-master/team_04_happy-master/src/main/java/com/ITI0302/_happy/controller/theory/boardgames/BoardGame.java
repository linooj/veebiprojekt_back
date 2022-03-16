package com.ITI0302._happy.controller.theory.boardgames;

public class BoardGame {

    private Long id;
    private String name;
    private String genre;
    private String numberOfPlayers;
    private int gameplayTime;
    private int yearReleased;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getGameplayTime() {
        return gameplayTime;
    }

    public void setGameplayTime(int gameplayTime) {
        this.gameplayTime = gameplayTime;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }
}
