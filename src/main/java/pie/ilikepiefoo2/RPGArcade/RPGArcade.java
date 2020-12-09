package main.java.pie.ilikepiefoo2.RPGArcade;

import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Monster;
import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Player;

public class RPGArcade {
    public static void main(String[] args)
    {
        Player p = new Player("Pie");
        Monster monster = new Monster("Bob");
        p.attack(monster);
    }
}
