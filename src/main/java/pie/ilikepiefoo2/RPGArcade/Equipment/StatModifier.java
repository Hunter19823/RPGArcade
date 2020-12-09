package main.java.pie.ilikepiefoo2.RPGArcade.Equipment;

import main.java.pie.ilikepiefoo2.RPGArcade.Entity.Entity;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.armor.Armor;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons.Sword;
import main.java.pie.ilikepiefoo2.RPGArcade.Equipment.weapons.Weapon;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigException;
import main.java.pie.ilikepiefoo2.RPGArcade.Util.ConfigManager;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public abstract class StatModifier {
    public static final String SAVE_LOCATION="src/main/resources/saved/";
    protected String name = "Default";
    protected Slot slot = null;
    protected double baseHealthModifier = 1;
    protected double baseDamageModifier = 1;
    protected double baseDamageReduction = 0;
    protected double totalDamageModifier = 0;
    protected double totalHealthModifier = 0;
    protected double totalDamageReductionModifier = 0;

    public StatModifier() { }

    public StatModifier(String name)
    {
        this.name = name;
        quickLoad();
    }

    public double applyBaseDamageModifier(Entity entity)
    {
        return entity.getBaseDamage()*baseDamageModifier - entity.getBaseDamage();
    }
    public double applyBaseHealthModifier(Entity entity)
    {
        return entity.getBaseHealth()*baseHealthModifier - entity.getBaseHealth();
    }
    public double applyBaseDamageReduction(double totalDamage)
    {
        return -baseDamageReduction;
    }
    public double applyTotalDamageModifier(double totalDamage)
    {
        return totalDamage * totalDamageModifier;
    }
    public double applyTotalHealthModifier(double totalHealth)
    {
        return totalHealth * totalHealthModifier;
    }
    public double applyTotalDamageReductionModifier(double totalDamage)
    {
        return totalDamage * totalDamageReductionModifier;
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
        save();
    }

    public double getBaseDamageModifier()
    {
        return baseDamageModifier;
    }

    public void setBaseDamageModifier(double baseDamageModifier)
    {
        this.baseDamageModifier = baseDamageModifier;
        save();
    }

    public double getTotalDamageModifier()
    {
        return totalDamageModifier;
    }

    public void setTotalDamageModifier(double totalDamageModifier)
    {
        this.totalDamageModifier = totalDamageModifier;
        save();
    }

    public double getBaseDamageReduction()
    {
        return baseDamageReduction;
    }

    public void setBaseDamageReduction(double baseDamageReduction)
    {
        this.baseDamageReduction = baseDamageReduction;
        save();
    }

    public double getTotalDamageReductionModifier()
    {
        return totalDamageReductionModifier;
    }

    public void setTotalDamageReductionModifier(double totalDamageReductionModifier)
    {
        this.totalDamageReductionModifier = totalDamageReductionModifier;
        save();
    }

    public double getTotalHealthModifier()
    {
        return totalHealthModifier;
    }

    public void setTotalHealthModifier(double totalHealthModifier)
    {
        this.totalHealthModifier = totalHealthModifier;
        save();
    }

    public static String getSaveLocation(StatModifier stat)
    {
        return SAVE_LOCATION+ConfigManager.getSafeName(stat.getName())+".txt";
    }


    public static String getSaveLocation(String name)
    {
        return SAVE_LOCATION+ConfigManager.getSafeName(name)+".txt";
    }

    public static StatModifier loadStatModifiers(String equipmentName) throws ConfigException, FileNotFoundException
    {
        String toolDetails = ConfigManager.loadFile(getSaveLocation(equipmentName));

        StatModifier stat = null;

        String[] components = toolDetails.substring(0,toolDetails.indexOf("\n")-1).split("=");

        if(components[0].equals("Type")) {
            for(Weapon weapon : Weapon.values()){
                if(weapon.CLASS.toString().equals(components[1]))
                {
                    try {
                        stat = (StatModifier) weapon.CLASS.getConstructor().newInstance();
                        break;
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
            for(Armor armor : Armor.values()){
                if(components[1].equals(armor.CLASS.toString()))
                {
                    try {
                        stat = (StatModifier) armor.CLASS.getConstructor().newInstance();
                        break;
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(stat == null)
                throw new ConfigException("Equipment Type not Supported: "+components[1]+"");
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
        }catch(FileNotFoundException e)
        {
            System.out.println("Could not find \""+name+"\". Now saving for future use...");
            save();
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
                "\tName: %s%n" +
                "\tClass: %s%n" +
                "\tSlot: %s%n" ,
                this.name,
                this.getClass().getSimpleName(),
                this.slot.NAME
        );
        if(baseHealthModifier != 1)
            output += String.format("\tBase Health Bonus: %,.2f%%%n",(baseHealthModifier-1)*100);
        if(baseDamageModifier != 1)
            output += String.format("\tBase Damage Bonus: %,.2f%%%n",(baseDamageModifier-1)*100);
        if(baseDamageReduction != 0)
            output += String.format("\tDamage Reduction Bonus: %,.2f Damage%n",baseDamageReduction);
        if(totalDamageModifier != 0)
            output += String.format("\tStackable Damage Bonus: %,.2f%%%n",(totalDamageModifier)*100);
        if(totalHealthModifier != 0)
            output += String.format("\tStackable Health Bonus: %,.2f%%%n",(totalHealthModifier)*100);
        if(totalDamageReductionModifier != 0)
            output += String.format("\tStackable Damage Reduction Bonus: %,.2f%%%n",(totalDamageReductionModifier)*100);

        return output;
    }
}
