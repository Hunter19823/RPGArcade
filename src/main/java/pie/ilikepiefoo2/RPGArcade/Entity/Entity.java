package main.java.pie.ilikepiefoo2.RPGArcade.Entity;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Equipment;

import java.io.File;

public abstract class Entity {
    String name = "Default";
    double baseHealth = 100;
    double currentHealth = baseHealth;
    double baseDamage = 10;
    int level = 1;
    Equipment equipment;

    public Entity()
    {}
    public Entity(String name)
    {
        this.name = name;
    }

    private void levelUp()
    {
        this.level++;
        this.baseHealth += level*Math.random()*10;
        this.baseDamage += level*Math.random()*7.5;
    }

    public double getCurrentHealth()
    {
        return this.currentHealth;
    }
    abstract protected void attack(Entity them);
    abstract protected void load(File file);

    public String toString()
    {
        return String.format("%-15s (lvl %3d): ",name,level);
    }

}
