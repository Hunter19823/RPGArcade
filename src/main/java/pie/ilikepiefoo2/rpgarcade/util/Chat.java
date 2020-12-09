package main.java.pie.ilikepiefoo2.rpgarcade.util;

/**
 * Chat Class
 *
 * Used for communicating with the console
 * with calculated pauses.
 */
public class Chat {
    // Sets up a Chat object for every other class to use.
    public final static Chat CHAT = new Chat(false);

    // Whether or not there should be a delay between messages.
    public boolean PAUSE = true;

    /**
     * Chat Constructor
     *
     * @param PAUSE Whether or not to
     *              pause after each message
     */
    public Chat(boolean PAUSE)
    {
        this.PAUSE = PAUSE;
    }

    /**
     * Delays the program.
     *
     * @param duration The number of miliseconds to
     *                 pause the program.
     */
    public void delay(int duration){

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enables/Disables Delay between messages.
     *
     * @param PAUSE
     */
    public void setPAUSE(boolean PAUSE)
    {
        this.PAUSE = PAUSE;
    }

    /**
     * See System.out.println
     *
     * @param o
     */
    public void println(Object o)
    {
        System.out.println(o);
        if(PAUSE) delay((int)(o.toString().length()*(200.0/60.0/4.17 * 100))- 1000);
    }
    /**
     * See System.out.print
     *
     * @param o
     */
    public void print(Object o)
    {
        System.out.print(o);
        if(PAUSE) delay((int)(o.toString().length()*(200.0/60.0/4.17 * 100))- 1000);
    }

    /**
     * See System.out.printf
     *
     * @param string
     * @param args
     */
    public void printf(String string, Object... args)
    {
        System.out.printf(string,args);
        if(PAUSE) delay((int)(String.format(string,args).length()*(200.0/60.0/4.17 * 100)) - 1000);
    }
}
