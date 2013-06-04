package oratest_pc.phooto.server;


import com.google.gdata.data.Content;

import com.google.gdata.data.MediaContent;

import com.google.gdata.data.photos.AlbumEntry;

import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.util.ServiceException;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import java.util.StringTokenizer;

import oratest_pc.phooto.server.PhootoAppS;

import oratest_pc.phooto.server.MyCompare;


/*        
import org.apache.tools.ant.ExitException;

import sample.photos.PicasawebCommandLine;
*/        

public class Phooto{

        public static void PhootoAppS_show(String Username, String Password) {            
        }
    

    public static boolean find(List<MyList> A, int N, List IndexInt) {
       
        for (MyList listaarray : A) {
/*        
        MyList lista = (MyList) listaarray.toArray();
*/        
        if (listaarray.YearsMyList() == N) 
            {
            IndexInt.set (0, new Integer(listaarray.IncRecordNrMyList())); 
            return true;
            }
        }
        if (IndexInt.size() > 0) 
        {
            IndexInt.set (0, new Integer(1));
        }
        else
        {
            IndexInt.add (new Integer(1));
        }
       return false;
    }

    
  public static void main(String args[]) throws ServiceException, Exception {
        
        
        List<MyList> mylist = new ArrayList();
     
        String Username = "";
        String Username_User = "";
        String Username_Domain = "";
        String FileNamePath = "";
        int Username_Max = 0;
        String Password = "";
        Integer IndexIntY;
        Integer IndexIntM;
        Integer IndexIntD;
        String TheString;
        
        List IndexIntYL = new ArrayList();    
        List IndexIntML = new ArrayList();    
        List IndexIntDL = new ArrayList();    
        
        List UsernameArray = new ArrayList();
        List PasswordArray = new ArrayList();
        
        
        int d = 0;
        int f = 0;

        
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        
        
        if (args.length == 0 || !(args[0].contentEquals("FROMTERM") 
                    || args[0].contentEquals("FROMFILE") || args[0].contentEquals("FROMARGS")))
        {
            System.out.println("USAGE:");
            System.out.println("There are 3 way to use the program:");
            System.out.println("_______________________________________________________________");
            System.out.println("1st: FROMTERM");
            System.out.println("If u specify FROMTERM in the first argument the program will ask u the input params one by one");
            System.out.println("_______________________________________________________________");
            System.out.println("2nd: FROMFILE");
            System.out.println("If u specify FROMFILE in the first argument the program will read the file (what u specified in the second argument) with the input params");
            System.out.println("In the second argument file u must specify USERNAME PASSWORD like this:");
            System.out.println("USERNAME1 PASSWORD1");
            System.out.println("USERNAME2 PASSWORD2");
            System.out.println(". .");
            System.out.println(". .");
            System.out.println("On the 3rd argument u must specify the place where the html files should be placed");
            System.out.println("_______________________________________________________________");
            System.out.println("3rd: FROMARGS");
            System.out.println("If u specify FROMARGS in the first argument the program will read the arguments specified on commane line");
            System.out.println("In this case u must pass arguments like:");
            System.out.println("FROMARGS myuser mydomain password occurance path");
            System.out.println("Where each parameters means:");
            System.out.println("myuser: the username which used to connect to picasaweb");
            System.out.println("mydomain: the domain name which used to connect to picasaweb");
            System.out.println("password: the password which used to connect to picasaweb");
            System.out.println("occurance: the last number what u got in picasaweb");
            System.out.println("path: the place where the html files should be placed");
            System.out.println("_______________________________________________________________");
            return;
        }
        
        if (args[0].contentEquals("FROMTERM"))
        {
            System.out.println("Please Specify User (mynameXXX@mydomain.org - now u should type myname) :");
            Username_User = stdIn.readLine();
            System.out.println("Please Specify Domain (mynameXXX@mydomain.org - now u should type mydomain.org) :");
            Username_Domain = stdIn.readLine();
            System.out.println("Please Specify Password (same password for all) :");
            Password = stdIn.readLine();
            System.out.println("Please Specify Occurance (mynameXXX@mydomain.org - now u should type the max XXX) :");
            Username_Max = Integer.parseInt(stdIn.readLine());
            System.out.println("Please Specify Output Path (if empty it will be where u started the prog) :");
            FileNamePath = stdIn.readLine();
        }
        
        
            if (args[0].contentEquals("FROMFILE"))
            {
                if (args.length < 2)
                {
                    System.out.println("Please Specify Input File!!");
                    return;
                }
                boolean exists = (new File(args[1])).exists();
                if (!exists)
                {
                    System.out.println("No Input File Found!!");
                    return;
                }
                else
                {
                    if (args.length >= 3)
                    {
                        FileNamePath = args[2];
                    }
                    FileReader fstream = new FileReader(args[1]);
                    String FromFileLine;
                    BufferedReader FROMFILE = new BufferedReader(fstream);
                    while ((FromFileLine = FROMFILE.readLine()) != null) 
                    {
                        Username_Max++;
                        StringTokenizer matcher = new StringTokenizer(FromFileLine);
                        UsernameArray.add((String) matcher.nextToken());
                        PasswordArray.add((String) matcher.nextToken());
                    }    
                }
            }
        
        
            if (args[0].contentEquals("FROMARGS"))
            {
                Username_User = args[1];
                Username_Domain = args[2];
                Password = args[3];
                Username_Max = Integer.parseInt(args[4]);
                if (args.length >= 6)
                {
                    FileNamePath = args[5];
                }
            }


        
     for (int k=1; k <= Username_Max ; k++)
        {
        if (args[0].contentEquals("FROMFILE"))
        {
            Username = (String)UsernameArray.get(k-1);
            Password = (String)PasswordArray.get(k-1);
        }
        else
        {
            Username = Username_User+k+"@"+Username_Domain;
        }
        
        System.out.println("User Started: " + Username + " Password: " + Password);
            
        List<AlbumEntry> Albumok = null;
        List<PhotoEntry> PhotoLista = null;
        
            
            
        Albumok = PhootoAppS.PhootoAppS_showAlbums2(Username, Password);

            if (Albumok.size() == 0) {
              System.out.println("No albums found.");
            } else {
              for (AlbumEntry entry : Albumok) {
                PhotoLista = PhootoAppS.showAlbumPhotos2(entry);
                for (PhotoEntry entry2 : PhotoLista) {
                        Content content = entry2.getContent(); 
                        if(content instanceof MediaContent) { 
                            MediaContent mediaContent = (MediaContent) content; 
                            String PhootoUri = mediaContent.getUri();
                            String PhootoTitle = entry2.getTitle().getPlainText();
                            
                            int PhootMonth;
                            try 
                            {PhootMonth=entry2.getExifTags().getTime().getMonth();
                            }catch (NullPointerException ee){PhootMonth=0;}
                            
                            int PhootYear;
                            try 
                            {PhootYear=entry2.getExifTags().getTime().getYear();
                            }catch (NullPointerException ee){PhootYear=0;}

                            int PhootDate;
                            try 
                            {PhootDate=entry2.getExifTags().getTime().getDate();
                            }catch (NullPointerException ee){PhootDate=0;}
                            
                            int PhootoTime;
                            try 
                            {PhootoTime = ((entry2.getExifTags().getTime().getHours() -2) * 10000) 
                                            + (entry2.getExifTags().getTime().getMinutes() * 100)
                                            + (entry2.getExifTags().getTime().getSeconds())
                                            ;
                            }catch (NullPointerException ee){PhootoTime=0;}
                            
                            int PhootoTimeH;
                            try 
                            {PhootoTimeH = (entry2.getExifTags().getTime().getHours() -2);
                            }catch (NullPointerException ee){PhootoTimeH=0;}
                            
                            int PhootoTimeM;
                            try 
                            {PhootoTimeM = entry2.getExifTags().getTime().getMinutes();
                            }catch (NullPointerException ee){PhootoTimeM=0;}

                            int PhootoTimeS;
                            try 
                            {PhootoTimeS = (entry2.getExifTags().getTime().getSeconds());
                            }catch (NullPointerException ee){PhootoTimeS=0;}

                            if (PhootYear == 0)
                            {
                                d++;
                                if (d % 200 == 0)
                                {
                                    ++f;
                                }
                                PhootMonth = PhootMonth + f;
                            }
                            PhootYear = PhootYear + 1900;
                            PhootMonth= PhootMonth + 1;
                            PhootoTitle = PhootoTitle.replace(" ","%20");
                            PhootoUri = PhootoUri.replace(PhootoTitle, "s144/" + PhootoTitle);
                            
                            
                            boolean exists_years = find(mylist, PhootYear, IndexIntYL);
                            
                            boolean exists_months = find(mylist,(PhootYear * 100) + PhootMonth , IndexIntML);
                            
                            boolean exists_days = find(mylist,(PhootYear * 10000) + (PhootMonth * 100) + PhootDate , IndexIntDL);
                            

                            IndexIntY = (Integer)IndexIntYL.get(0);
                            IndexIntM = (Integer)IndexIntML.get(0);
                            IndexIntD = (Integer)IndexIntDL.get(0);
/*
                            System.out.println("IndexIntY " + PhootYear + " :" + IndexIntY); 
                            System.out.println("IndexIntM " + PhootYear + PhootMonth  + " :" + IndexIntM); 
                            System.out.println("IndexIntD " + PhootYear + PhootMonth  + PhootDate + " :" + IndexIntD); 
 */                           

                            if (!exists_years){
                                mylist.add(new MyList(PhootYear, 
                                  "<p> <a href=\""
                                + "PHOOTO"+"_"+PhootYear+".html"
                                + "\">"
                                + PhootYear
                                , "INDEX.html"
                                ,IndexIntY));
                            }

                            
                            if (!exists_months){
                                mylist.add(new MyList((PhootYear * 100) + PhootMonth, 
                                  "<p> <a href=\""
                                + "PHOOTO"+"_"+PhootYear+"_"+PhootMonth+".html"
                                + "\">"
                                + PhootYear+"_"+PhootMonth
                                , "PHOOTO"+"_"+PhootYear+".html"
                                ,IndexIntM));
                            }
                            
                            
                            
                            if (!exists_days){
                                mylist.add(new MyList((PhootYear * 10000) + (PhootMonth * 100) + PhootDate, 
                                  "<p> <a href=\""
                                + "PHOOTO"+"_"+PhootYear+"_"+PhootMonth+"_"+PhootDate+".html"
                                + "\">"
                                + PhootYear+"_"+PhootMonth+"_"+PhootDate
                                ,"PHOOTO"+"_"+PhootYear+"_"+PhootMonth+".html"
                                ,IndexIntD));
                            }
                            
                            
                             mylist.add(new MyList((PhootYear * 1000000) + (PhootMonth * 10000) + (PhootDate * 100),
                             "<td> <p> <a href=\""
                             + mediaContent.getUri()
                             + "\">"
                             + entry2.getTitle().getPlainText() 
                             + " (" + PhootoTimeH + ":" + PhootoTimeM + ":"+ PhootoTimeS + ")"
                             + "</a></p>"
                             + "<p> <a href=\"" 
                             + entry2.getHtmlLink().getHref() 
                             + "\" ><img src=\""
                             + PhootoUri
                             + "\">"
                             + "</a></p></td>"
                             ,"PHOOTO"+"_"+PhootYear+"_"+PhootMonth+"_"+PhootDate+".html"
                             ,PhootoTime));
                                
                        }
                      }
            }
            }
        
            System.out.println("User Ended: " + Username);
        }
        Collections.sort(mylist);
        int YearsMyListBefore = 0;
        int k = 1;
        for (MyList listtowrite : mylist) {
            if (listtowrite.YearsMyList() > 1000000000) {
                if (listtowrite.YearsMyList() == YearsMyListBefore)
                {
                   listtowrite.RecordNrMyList = ++k;
                }
                else
                {
                   YearsMyListBefore = listtowrite.YearsMyList();
                   k = 1;
                   listtowrite.RecordNrMyList = k;
                }
            }
        }
        
        
        for (MyList listtowrite : mylist) {
        
            boolean exists = (new File(FileNamePath+listtowrite.FileMyList())).exists();
            FileWriter fstream = new FileWriter(FileNamePath+listtowrite.FileMyList(), true);
            BufferedWriter out = new BufferedWriter(fstream);
            if (!exists)
            {
                out.append("<table align=center> <tr>");
            }

            out.append(listtowrite.HtmlCodeMyList());
            
            if (listtowrite.YearsMyList() < 1000000000) {
                out.append("  (" + listtowrite.RecordNrMyList() + ")" + "</p></a>");
            }
            else
            {
               if (listtowrite.RecordNrMyList() %4 == 0)
               {
                   out.append("</tr><tr>");
               }
            }
            out.close();
        }
    }
}
