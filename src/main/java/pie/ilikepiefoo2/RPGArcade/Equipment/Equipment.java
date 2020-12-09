package main.java.pie.ilikepiefoo2.RPGArcade.Equipment;


import java.util.ArrayList;

public class Equipment{
    private StatModifier[] EQUIPMENT;
    private ArrayList<StatModifier> PASSIVE;

    public Equipment()
    {
        this.PASSIVE = new ArrayList<StatModifier>();
        this.EQUIPMENT = new StatModifier[Slot.values().length-1];
    }

    public StatModifier equip(StatModifier replacement, Slot slot)
    {
        StatModifier currentPiece = null;
        if(this.EQUIPMENT[slot.POSITION] != null)
        {
            currentPiece = this.EQUIPMENT[slot.POSITION];
        }else{
            this.EQUIPMENT[slot.POSITION] = replacement;
        }
        return currentPiece;
    }
}

