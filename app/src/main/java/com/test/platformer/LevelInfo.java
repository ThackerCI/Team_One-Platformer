package com.test.platformer;

import java.io.Serializable;

/**
 * Created by Isaiah on 3/3/2016.
 */
public class LevelInfo implements Serializable {
    private Level level;
    private Character character;

    public Level getLevel() {
        return level;
    }

    public LevelInfo(Level level, Character character) {
        this.level = level;
        this.character = character;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
