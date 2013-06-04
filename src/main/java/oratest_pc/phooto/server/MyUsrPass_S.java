package oratest_pc.phooto.server;


public class MyUsrPass_S implements Comparable<MyUsrPass_S> {
    String Username; String Password;

    public MyUsrPass_S(String Username, String Password) {
        if (Username == null && Password == null)
            throw new NullPointerException();
        this.Username = Username;
        this.Password = Password;
    }

    public String Username() {
        return Username;
    }

    public String Password() {
        return Password;
    }

    public boolean equals(Object o) {
        if (!(o instanceof MyUsrPass_S))
            return false;
        MyUsrPass_S n = (MyUsrPass_S)o;
        return n.Username.equals(Username) && n.Password.equals(Password);
    }

    public String toString() {
        return Username + " " + Password;
    }

    public int compareTo(MyUsrPass_S n) {
        int Cmp = 0;
        Cmp = Username.compareTo(n.Username);
        return (Cmp != 0 ? Cmp : Password.compareTo(n.Password));
    }

}
