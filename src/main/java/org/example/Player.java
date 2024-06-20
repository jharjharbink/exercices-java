package org.example;

public class Player {
    private String name;
    private int level = 0;
    private int expPoint = 0;
    private int neededExpToLevelUp = 100;
    private int performedQuest = 0;

    public Player(String name) {
        this.name = name;
    }

    public void aQuest(){  // A Tribe Called Quest ?
        this.expPoint += 10;
        this.performedQuest ++;
        System.out.println("le joueur " + this.name + " fais la quête n°" + this.performedQuest);
    }

    public void levelUp(){
        if(this.expPoint > 100){
            this.level ++;
            this.expPoint = 0;
            this.neededExpToLevelUp += 10;
            System.out.println("le joueur " + this.name + " passe niveau " + this.level);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public int getExpPoint() {
        return expPoint;
    }
}