/*    */ package oratest_pc.phooto.server;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class MyCompare
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object obj1, Object obj2)
/*    */   {
/* 10 */     ListaEvek emp1 = (ListaEvek)obj1;
/* 11 */     ListaEvek emp2 = (ListaEvek)obj2;
/*    */ 
/* 14 */     int nameComp = 0;
/* 15 */     if (emp1.getEvek() == emp2.getEvek()) nameComp = 1;
/*    */ 
/* 17 */     return nameComp == 0 ? emp1.getHtmlCode().compareTo(emp2.getHtmlCode()) : nameComp;
/*    */   }
/*    */ 
/*    */   class ListaEvek implements Comparable {
/*    */     int Evek;
/*    */     String HtmlCode;
/*    */ 
/*    */     public int compareTo(Object obj) {
/* 26 */       ListaEvek emp = (ListaEvek)obj;
/* 27 */       int deptComp = 0;
/* 28 */       if (this.Evek == emp.getEvek()) deptComp = 1;
/* 29 */       return deptComp == 0 ? this.HtmlCode.compareTo(emp.getHtmlCode()) : deptComp;
/*    */     }
/*    */ 
/*    */     public boolean equals(Object obj)
/*    */     {
/* 34 */       if (!(obj instanceof ListaEvek)) {
/* 35 */         return false;
/*    */       }
/* 37 */       ListaEvek emp = (ListaEvek)obj;
/* 38 */       return (this.Evek == emp.getEvek()) && (this.HtmlCode.equals(emp.getHtmlCode()));
/*    */     }
/*    */ 
/*    */     public ListaEvek(int evek, String htmlcode)
/*    */     {
/* 43 */       this.Evek = evek;
/* 44 */       this.HtmlCode = htmlcode;
/*    */     }
/*    */ 
/*    */     public int getEvek() {
/* 48 */       return this.Evek;
/*    */     }
/*    */ 
/*    */     public String getHtmlCode() {
/* 52 */       return this.HtmlCode;
/*    */     }
/*    */ 
/*    */     public String toString() {
/* 56 */       return "[ Years=" + this.Evek + ",HtmlCode=" + this.HtmlCode + "]";
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\qka\qka\PhootoV1\Phooto\PhootoCommandLine\
 * Qualified Name:     oratest_pc.phooto.server.MyCompare
 * JD-Core Version:    0.6.2
 */