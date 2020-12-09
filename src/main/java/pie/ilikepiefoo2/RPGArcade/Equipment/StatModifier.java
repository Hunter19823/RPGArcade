package main.java.pie.ilikepiefoo2.RPGArcade.Equipment;

import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Entity;

public abstract class StatModifier {
    protected String name = "Default";
    protected double baseHealthModifier = 1;
    protected double baseDamageModifier = 1;
    protected double armorModifier = 1;
    protected double totalDamageModifier = 1;
    protected double totalHealthModifier = 1;

    public double applyBaseDamageModifier(Entity entity)
    {
        return entity.getBaseDamage()*baseDamageModifier;
    }
    public double applyBaseHealthModifier(Entity entity)
    {
        return entity.getBaseHealth()*baseHealthModifier;
    }
    public double applyTotalDamageModifier(double totalDamage)
    {
        return totalDamage*totalDamageModifier;
    }
    public double applyTotalHealthModifier(double totalHealth)
    {
        return totalHealth*totalHealthModifier;
    }
    public double applyArmorModifier(double totalDamage)
    {
        return totalDamage*armorModifier;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getBaseHealthModifier()
    {
        return baseHealthModifier;
    }

    public void setBaseHealthModifier(double baseHealthModifier)
    {
        this.baseHealthModifier = baseHealthModifier;
    }

    public double getBaseDamageModifier()
    {
        return baseDamageModifier;
    }

    public void setBaseDamageModifier(double baseDamageModifier)
    {
        this.baseDamageModifier = baseDamageModifier;
    }

    public double getTotalDamageModifier()
    {
        return totalDamageModifier;
    }

    public void setTotalDamageModifier(double totalDamageModifier)
    {
        this.totalDamageModifier = totalDamageModifier;
    }

    public double getArmorModifier()
    {
        return armorModifier;
    }

    public void setArmorModifier(double armorModifier)
    {
        this.armorModifier = armorModifier;
    }

    public double getTotalHealthModifier()
    {
        return totalHealthModifier;
    }

    public void setTotalHealthModifier(double totalHealthModifier)
    {
        this.totalHealthModifier = totalHealthModifier;
    }
}
