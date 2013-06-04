package oratest_pc.phooto.server;


import java.util.Comparator;


public class MyList implements Comparable<MyList> {
    private final int YearsMyList; String HtmlCodeMyList; String FileMyList;
    int RecordNrMyList;

    public MyList(int YearsMyList, String HtmlCodeMyList, String FileMyList,int RecordNrMyList) {
        if (YearsMyList == 0 && HtmlCodeMyList == null && FileMyList == null)
            throw new NullPointerException();
	this.YearsMyList = YearsMyList;
        this.HtmlCodeMyList = HtmlCodeMyList;
        this.FileMyList = FileMyList;
        this.RecordNrMyList = RecordNrMyList;
    }

    public int IncRecordNrMyList() {return ++RecordNrMyList;}
    public int YearsMyList() { return YearsMyList; }
    public String HtmlCodeMyList()  { return HtmlCodeMyList;  }
    public String FileMyList()  { return FileMyList;  }
    public int RecordNrMyList() { return RecordNrMyList; }

    public boolean equals(Object o) {
        if (!(o instanceof MyList))
            return false;
        MyList n = (MyList)o;
        return n.YearsMyList == YearsMyList &&
               n.HtmlCodeMyList.equals(HtmlCodeMyList) &&
               n.FileMyList.equals(FileMyList) && 
               n.RecordNrMyList == RecordNrMyList;
    }
/*
    public int hashCode() {
        return 31*YearsMyList.hashCode() + HtmlCodeMyList.hashCode();
    }
*/
    public String toString() {
	return YearsMyList + " " + HtmlCodeMyList + " " + FileMyList + " " + RecordNrMyList;
    }

    public int compareTo(MyList n) {
        int Cmp = 0;
        if (YearsMyList < n.YearsMyList) Cmp = -1;
        if (YearsMyList > n.YearsMyList) Cmp = 1;
        if (YearsMyList == n.YearsMyList) Cmp = 0;
        if (Cmp == 0)
        {
            if (RecordNrMyList < n.RecordNrMyList) Cmp = -1;
            if (RecordNrMyList > n.RecordNrMyList) Cmp = 1;
            if (RecordNrMyList == n.RecordNrMyList) Cmp = 0;
        }
        return (Cmp != 0 ? Cmp :
                HtmlCodeMyList.compareTo(n.HtmlCodeMyList));
    }
}