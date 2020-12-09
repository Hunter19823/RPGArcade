package main.java.pie.ilikepiefoo2.RPGArcade.Equipment;


import java.util.ArrayList;

public class Equipment{
    private StatModifier HEAD;
    private StatModifier CHEST;
    private StatModifier LEGS;
    private StatModifier BOOTS;
    private StatModifier LEFT_HAND;
    private StatModifier RIGHT_HAND;
    private ArrayList<StatModifier> PASSIVE;

    public Equipment()
    {
        this.PASSIVE = new ArrayList<StatModifier>();
    }

    public StatModifier equip(StatModifier replacement, Slot slot)
    {
        StatModifier currentPiece = null;
        switch(slot)
        {
            case HEAD:
                if(this.HEAD != null)
                {
                    currentPiece = this.HEAD;
                }
                this.HEAD = replacement;
                break;
            case CHEST:
                if(this.CHEST != null)
                {
                    currentPiece = this.CHEST;
                }
                this.CHEST = replacement;
                break;
            case LEGS:
                if(this.LEGS != null)
                {
                    currentPiece = this.LEGS;
                }
                this.LEGS = replacement;
                break;
            case FEET:
                if(this.BOOTS != null)
                {
                    currentPiece = this.BOOTS;
                }
                this.BOOTS = replacement;
                break;
        }
        return currentPiece;
    }

}

