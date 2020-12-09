package main.java.pie.ilikepiefoo2.RPGArcade;

import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Entity;
import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Human;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons.Sword;

public class RPGArcade {
    public static void main(String[] args)
    {
        //Human player = Human.load("src/main/resources/saved/pie.txt");
        Human player = new Human("pie");

        player.displayStats();

        Sword sword = new Sword("Pie's Epic Sword.");
        sword.displayStats();




    }
}
