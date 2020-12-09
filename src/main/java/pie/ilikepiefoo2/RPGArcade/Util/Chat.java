package main.java.pie.ilikepiefoo2.RPGArcade.Util;

public class Chat {
    public final static Chat CHAT = new Chat(false);
    public boolean PAUSE = true;
    public Chat(boolean PAUSE)
    {
        this.PAUSE = PAUSE;
    }

    public void delay(int duration){

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void println(Object o)
    {
        System.out.println(o);
        if(PAUSE) delay((int)(o.toString().length()*(200.0/60.0/4.17 * 100))- 1000);
    }
    public void print(Object o)
    {
        System.out.print(o);
        if(PAUSE) delay((int)(o.toString().length()*(200.0/60.0/4.17 * 100))- 1000);
    }
    public void printf(String string, Object... args)
    {
        System.out.printf(string,args);
        if(PAUSE) delay((int)(String.format(string,args).length()*(200.0/60.0/4.17 * 100)) - 1000);
    }
}
