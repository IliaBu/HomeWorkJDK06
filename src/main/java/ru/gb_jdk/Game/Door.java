package ru.gb_jdk.Game;

public record Door(Prize prize) {
    public String openDoor() {
        return this.prize.getName();
    }

}
