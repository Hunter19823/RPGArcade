package main.java.pie.ilikepiefoo2.RPGArcade.Entity;

import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.Equipment;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.StatModifier;

import java.io.File;

public abstract class Entity {
    protected String name = "Default";
    protected double baseHealth = 100;
    protected double currentHealth = baseHealth;
    protected double baseDamage = 10;
    protected int level = 1;
    protected final Equipment equipment = new Equipment();

    private void levelUp()
    {
        this.level++;
        this.baseHealth += level*Math.random()*10;
        this.baseDamage += level*Math.random()*7.5;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getBaseHealth()
    {
        return baseHealth;
    }

    public void setBaseHealth(double baseHealth)
    {
        this.baseHealth = baseHealth;
    }

    public void setCurrentHealth(double currentHealth)
    {
        this.currentHealth = currentHealth;
    }

    public double getCurrentHealth()
    {
        return this.currentHealth;
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

    abstract protected void load(File file);

    public String toString()
    {
        return String.format("%s (%dL)",name,level);
    }

}
