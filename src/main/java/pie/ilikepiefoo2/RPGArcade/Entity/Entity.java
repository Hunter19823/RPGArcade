package main.java.pie.ilikepiefoo2.RPGArcade.Entity;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Equipment;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigException;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class Entity {
    protected String name = "Default";
    protected double baseHealth = 100;
    protected double currentHealth = baseHealth;
    protected double baseDamage = 10;
    protected int level = 1;
    protected final Equipment equipment = new Equipment();

    public static String getSaveLocation(String name)
    {
        StringBuilder builder = new StringBuilder();
        for(char c : name.toCharArray())
        {
            if((c >= 'a' && c <='z') || (c >= 'A' && c <= 'Z'))
            {
                builder.append(c);
            }else if(c == ' '){
                builder.append('_');
            }
        }
        return "src/main/resources/saved/"+builder.toString()+".txt";
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
        return 0;
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
        double previousHealth = target.getCurrentHealth();
        target.hurt(damage,this);
        StatModifier weapon = getWeapon();
        System.out.printf("%s attacked %s with their %s for a total of %,.2f damage! Remaining HP: %,.2f%n",this.name, target.name, weapon == null ? "fists" : weapon.getName(),previousHealth-target.currentHealth, target.currentHealth);
    }

    protected double hurt(double damage, Entity attacker)
    {
        currentHealth -= damage;
        return currentHealth;
    }

    abstract public void loadFromFile(String filePath) throws ConfigException;
    abstract public void saveToFile(String filePath) throws ConfigException;
    abstract public String getSavingFormat();

    public String toString()
    {
        return String.format("%s (%dL)",name,level);
    }

    public String getStats()
    {
        return String.format("Name: %s%nLevel: %d%nHealth: %,.2f / %,.2f%nDamage: %,.2f",name,level,currentHealth,baseHealth,baseDamage);
    }

    public void displayStats()
    {
        System.out.println(getStats());
    }

}
