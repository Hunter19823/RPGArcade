package main.java.pie.ilikepiefoo2.RPGArcade.Entity;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Equipment;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigException;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigManager;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class Entity {
    public static final String SAVE_LOCATION="src/main/resources/saved/";
    protected String name = "Default";
    protected double baseHealth = 100;
    protected double currentHealth = baseHealth;
    protected double baseDamage = 10;
    protected int level = 1;
    protected final Equipment equipment = new Equipment();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    private void levelUp()
    {
        this.level++;
        this.baseHealth += level*Math.random()*10;
        this.baseDamage += level*Math.random()*7.5;
    }

    public double getBaseHealth()
    {
        return baseHealth;
    }

    public void setBaseHealth(double baseHealth)
    {
        this.baseHealth = baseHealth;
    }

    public double getCurrentHealth()
    {
        return this.currentHealth;
    }

    public void setCurrentHealth(double currentHealth)
    {
        this.currentHealth = currentHealth;
    }

    public double getMaxHealth()
    {
        return this.equipment.getTotalHealthModifiers(this,this.equipment.getBaseHealthModifiers(this));
    }

    public double getBaseDamage()
    {
        return baseDamage;
    }

    public void setBaseDamage(double baseDamage)
    {
        this.baseDamage = baseDamage;
    }

    public double getMaxDamage()
    {
        return this.equipment.getTotalDamageModifiers(this,this.equipment.getBaseDamageModifiers(this));
    }

    public Equipment getEquipment()
    {
        return this.equipment;
    }

    public StatModifier getWeapon()
    {
        return this.equipment.getWeapon();
    }

    public void attack(Entity target)
    {
        double damage = this.getMaxDamage();
        target.hurt(damage,this);
    }

    protected double hurt(double damage, Entity attacker)
    {
        currentHealth -= damage;
        StatModifier weapon = attacker.getWeapon();
        System.out.printf("%s attacked %s with their %s for a total of %,.2f damage! Remaining HP: %,.2f%n",attacker.name, this.name, weapon == null ? "fists" : weapon.getName(), damage, this.currentHealth);
        return currentHealth;
    }

    abstract public void loadFromFile(String filePath) throws ConfigException;
    abstract public void saveToFile(String filePath) throws ConfigException;
    abstract public String getSavingFormat();

    public static String getSaveLocation(String name)
    {
        return SAVE_LOCATION+ ConfigManager.getSafeName(name)+".txt";
    }

    public void save()
    {
        saveToFile(getSaveLocation(name));
    }


    protected void quickLoad()
    {
        try{
            loadFromFile(getSaveLocation(name));
        }catch(ConfigException e)
        {
            e.printStackTrace();
            if(e.getCause().getClass().equals(FileNotFoundException.class)){
                System.out.println("Could not find \""+name+"\". Now saving for future use.");
                save();
            }
        }
    }

    public String toString()
    {
        return String.format("%s (%dL)",name,level);
    }

    public String getStats()
    {
        return String.format("Name: %s%nLevel: %d%nHealth: %,.2f / %,.2f%nDamage: %,.2f",name,level,currentHealth,getMaxHealth(),baseDamage);
    }

    public void displayStats()
    {
        System.out.println(getStats());
    }

}
