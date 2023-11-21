package entities.players;

import entities.FantasyCharacter;

public class Player extends FantasyCharacter {

    public Player(String name, int healthPoints, int strength, int dexterity, int experience, int gold) {
        super(name, healthPoints, strength, dexterity, experience, gold);
    }
}
