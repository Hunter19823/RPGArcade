package main.java.pie.ilikepiefoo2.RPGArcade.Equipment;

import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Entity;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons.Sword;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons.Weapons;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigException;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigManager;

import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class StatModifier {
    public static final String SAVE_LOCATION="src/main/resources/saved/";
    protected String name = "Default";
    protected Slot slot = null;
    protected double baseHealthModifier = 1;
    protected double baseDamageModifier = 1;
    protected double baseDamageReduction = 0;
    protected double totalDamageModifier = 1;
    protected double totalHealthModifier = 1;
    protected double totalDamageReductionModifier = 1;

    public double applyBaseDamageModifier(Entity entity)
    {
        return entity.getBaseDamage()*baseDamageModifier;
    }
    public double applyBaseHealthModifier(Entity entity)
    {
        return entity.getBaseHealth()*baseHealthModifier;
    }
    public double applyBaseDamageReduction(double totalDamage)
    {
        return totalDamage-baseDamageReduction;
    }
    public double applyTotalDamageModifier(double totalDamage)
    {
        return totalDamage*totalDamageModifier;
    }
    public double applyTotalHealthModifier(double totalHealth)
    {
        return totalHealth*totalHealthModifier;
    }
    public double applyTotalDamageReductionModifier(double totalDamage)
    {
        return totalDamage* totalDamageReductionModifier;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Slot getSlot()
    {
        return slot;
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

    public double getBaseDamageReduction()
    {
        return baseDamageReduction;
    }

    public void setBaseDamageReduction(double baseDamageReduction)
    {
        this.baseDamageReduction = baseDamageReduction;
    }

    public double getTotalDamageReductionModifier()
    {
        return totalDamageReductionModifier;
    }

    public void setTotalDamageReductionModifier(double totalDamageReductionModifier)
    {
        this.totalDamageReductionModifier = totalDamageReductionModifier;
    }

    public double getTotalHealthModifier()
    {
        return totalHealthModifier;
    }

    public void setTotalHealthModifier(double totalHealthModifier)
    {
        this.totalHealthModifier = totalHealthModifier;
    }

    public static String getSaveLocation(StatModifier stat)
    {
        return SAVE_LOCATION+ConfigManager.getSafeName(stat.getName())+".txt";
    }

    public static String getSaveLocation(String name)
    {
        return SAVE_LOCATION+ConfigManager.getSafeName(name)+".txt";
    }

    public static StatModifier loadStatModifiers(String equipmentName) throws ConfigException
    {
        String toolDetails = ConfigManager.loadFile(getSaveLocation(equipmentName));

        StatModifier stat = null;

        String[] components = toolDetails.substring(0,toolDetails.indexOf("\n")).split("=");

        if(components[0].equals("Type")) {
            if(components[1].equals(Weapons.SWORD.CLASS_NAME))
            {
                stat = new Sword();
            }
            else{
                throw new ConfigException("Equipment Type not Supported: \""+components[1]+"\"");
            }
        }

        loadStatModifiers(toolDetails,stat);
        
        return stat;
    }

    private static StatModifier loadStatModifiers(String blueprint, StatModifier mod) throws ConfigException
    {
        Scanner scanner = new Scanner(blueprint);
        String[] components;
        StatModifier stat = mod;
        while(scanner.hasNextLine())
        {
            components = scanner.nextLine().split("=");
            switch(components[0])
            {
                case "Name":
                    stat.setName(components[1]);
                    break;
                case "Slot":
                    stat.slot = Slot.valueOf(components[1]);
                    break;
                case "baseHealthModifier":
                    stat.setBaseHealthModifier(Double.parseDouble(components[1]));
                    break;
                case "baseDamageModifier":
                    stat.setBaseDamageModifier(Double.parseDouble(components[1]));
                    break;
                case "baseDamageReduction":
                    stat.setBaseDamageReduction(Double.parseDouble(components[1]));
                    break;
                case "totalDamageModifier":
                    stat.setTotalDamageModifier(Double.parseDouble(components[1]));
                    break;
                case "totalHealthModifier":
                    stat.setTotalHealthModifier(Double.parseDouble(components[1]));
                    break;
                case "totalDamageReductionModifier":
                    stat.setTotalDamageReductionModifier(Double.parseDouble(components[1]));
                    break;
            }
        }
        return stat;
    }

    protected void quickLoad()
    {
        try{
            loadStatModifiers(ConfigManager.loadFile(getSaveLocation(this.name)), this);
        }catch(ConfigException e)
        {
            if(e.getCause().getClass().equals(FileNotFoundException.class)){
                System.out.println("Could not find \""+name+"\". Now saving for future use...");
                save();
            }
        }
    }

    public void save()
    {
        ConfigManager.saveFile(getSaveLocation(this),getSavingFormat());
    }

    public String getSavingFormat()
    {
        return String.format(
                "Type=%s%n" +
                "Name=%s%n" +
                "Slot=%s%n" +
                "baseHealthModifier=%f%n" +
                "baseDamageModifier=%f%n" +
                "baseDamageReduction=%f%n" +
                "totalDamageModifier=%f%n" +
                "totalHealthModifier=%f%n" +
                "totalDamageReductionModifier=%f%n",
                this.getClass().toString(),
                this.name,
                this.slot,
                this.baseHealthModifier,
                this.baseDamageModifier,
                this.baseDamageReduction,
                this.totalDamageModifier,
                this.totalHealthModifier,
                this.totalDamageReductionModifier
        );
    }

    public void displayStats()
    {
        System.out.print(getStats());
    }

    public String getStats()
    {
        String output = String.format(
                "Class: %s%n" +
                "Name: %s%n" +
                "Slot: %s%n" ,
                this.getClass().getSimpleName(),
                this.name,
                this.slot.NAME
        );
        if(baseHealthModifier != 1)
            output += String.format("Base Health Bonus: %,.2f%%%n",baseHealthModifier*100);
        if(baseDamageModifier != 1)
            output += String.format("Base Damage Bonus: %,.2f%%%n",baseDamageModifier*100);
        if(baseDamageReduction != 0)
            output += String.format("Damage Reduction Bonus: %,.2f Damage%n",baseDamageModifier*-1);
        if(totalDamageModifier != 1)
            output += String.format("Stackable Damage Bonus: %,.2f%%%n",totalDamageModifier*100);
        if(totalHealthModifier != 1)
            output += String.format("Stackable Health Bonus: %,.2f%%%n",totalHealthModifier*100);
        if(totalDamageReductionModifier != 1)
            output += String.format("Stackable Damage Reduction Bonus: %,.2f%%%n",totalDamageReductionModifier*100);

        return output;
    }
}
