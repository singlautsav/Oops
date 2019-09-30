package labAP5;

import java.io.IOException;

interface Xero {
    //    public void Attack(Monster a)throws IOException;
    public void Defense(Xonster m)throws IOException;
    public void SpecialPower(Object o) throws IOException;
    public void Attack(Xonster m) throws IOException;
    public boolean getCount();
    public int getHP();
    public void levelup();
    public String getName();

}
