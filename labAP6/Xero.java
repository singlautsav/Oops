package labAP6;

import java.io.IOException;

interface Xero {
    //    public void Attack(Monster a)throws IOException;
    public void Defense(Xonster m)throws IOException;
    public void SpecialPower(Object o) throws IOException;
    public void Attack(Xonster m) throws IOException;
    public boolean getCount();
    public int getHP();
    public void levelup();
    public void incXp();
    public String getName();
    public int getxp();
    public void addSide(SideKick s);
    public void reset();
    public SideKick getSideKick();
    public void setSideKick() throws CloneNotSupportedException;
//    public void setSideNorm();

}
