package main.java.pie.ilikepiefoo2.RPGArcade.Entity;

/**
 * Human Class.
 */
public class Human extends Entity {

    /**
     * Manual Constructor.
     * Will not save until either:
     * 1. Something has been equipped.
     * 2. Entity has leveled up.
     */
    public Human() { }

    /**
     * Auto-Loaded Constructor.
     * @param name Will load any entities
     *             with this name, otherwise
     *             it will create one itself.
     */
    public Human(String name)
    {
        super(name);
    }


}
