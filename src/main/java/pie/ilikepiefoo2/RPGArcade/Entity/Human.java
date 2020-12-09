package main.java.pie.ilikepiefoo2.RPGArcade.Entity;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigException;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigManager;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Human extends Entity {

    public Human() { }

    public Human(String name)
    {
        this.name = name;
        this.quickLoad();
    }


}
