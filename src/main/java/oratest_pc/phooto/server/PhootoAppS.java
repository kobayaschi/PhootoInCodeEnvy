package oratest_pc.phooto.server;


import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.Content;
import com.google.gdata.data.Link;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.CommentEntry;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.GphotoFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.TagEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ParseException;
import com.google.gdata.util.ServiceException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.mail.*;

import oratest_pc.phooto.server.MyUsrPass_S;

public class PhootoAppS {
    public static PicasawebService service = new PicasawebService("exampleClient");

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
    
    public static void PhootoAppS_showAlbums3(List<MyUsrPass_S> myusrpass, List<MyList> mylist) {
        
        Integer IndexIntY;
        Integer IndexIntM;
        Integer IndexIntD;
        
        List IndexIntYL = new ArrayList();    
        List IndexIntML = new ArrayList();    
        List IndexIntDL = new ArrayList();    
        
        int d = 0;
        int f = 0;

        mylist.add(new MyList(1, "<table align=center><p></p>", "INDEX.html",1));

            String naja = "";
            MyUsrPass_S myusrpass2 = new MyUsrPass_S(naja,naja);
            Object myusrpass3;
            
        for (int usr=0; usr < myusrpass.size(); usr++)
        {
            myusrpass3 = myusrpass.get(0);
            naja = myusrpass3.toString();
            myusrpass2.Username = naja;
            
        List<AlbumEntry> Albumok = null;
        List<PhotoEntry> PhotoLista = null;
            
        Albumok = PhootoAppS.PhootoAppS_showAlbums2(myusrpass2.Username(), myusrpass2.Password());

            if (Albumok.size() == 0) {
              System.out.println("No albums found.");
            } else {
              for (AlbumEntry entry : Albumok) {
                    try {
                        PhotoLista = PhootoAppS.showAlbumPhotos2(entry);
                    } catch (Exception e) {
                        // TODO
                    }
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
                             catch (ParseException ee){PhootMonth=0;}
                            
                            int PhootYear;
                            try 
                            {PhootYear=entry2.getExifTags().getTime().getYear();
                            }catch (NullPointerException ee){PhootYear=0;}
                             catch (ParseException ee){PhootYear=0;}

                            int PhootDate;
                            try 
                            {PhootDate=entry2.getExifTags().getTime().getDate();
                            }catch (NullPointerException ee){PhootDate=0;}
                             catch (ParseException ee){PhootDate=0;}
                            
                            int PhootoTime;
                            try 
                            {PhootoTime = ((entry2.getExifTags().getTime().getHours() -2) * 10000) 
                                            + (entry2.getExifTags().getTime().getMinutes() * 100)
                                            + (entry2.getExifTags().getTime().getSeconds())
                                            ;
                            }catch (NullPointerException ee){PhootoTime=0;}
                             catch (ParseException ee){PhootoTime=0;}
                            
                            int PhootoTimeH;
                            try 
                            {PhootoTimeH = (entry2.getExifTags().getTime().getHours() -2);
                            }catch (NullPointerException ee){PhootoTimeH=0;}
                             catch (ParseException ee){PhootoTimeH=0;}
                            
                            int PhootoTimeM;
                            try 
                            {PhootoTimeM = entry2.getExifTags().getTime().getMinutes();
                            }catch (NullPointerException ee){PhootoTimeM=0;}
                             catch (ParseException ee){PhootoTimeM=0;}

                            int PhootoTimeS;
                            try 
                            {PhootoTimeS = (entry2.getExifTags().getTime().getSeconds());
                            }catch (NullPointerException ee){PhootoTimeS=0;}
                             catch (ParseException ee){PhootoTimeS=0;}

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
        
/*        
        for (MyList listtowrite : mylist) {
        
            boolean exists = (new File(listtowrite.FileMyList())).exists();
            FileWriter fstream = new FileWriter(listtowrite.FileMyList(), true);
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
*/        
        }

    public static List<AlbumEntry> PhootoAppS_showAlbums2(String Username, String Password) {
        if (Username != null && Password != null) {
          try {
            service.setUserCredentials(Username, Password);
          } 
              catch (AuthenticationException e) {
            throw new IllegalArgumentException(
                "Illegal username/password combination.");
          }
        }
        
        
        try {
            List<AlbumEntry> albums = getAlbums();
            return albums;
        }
        catch (Exception e)
        {
        throw new IllegalArgumentException(
          "Illegal username/password combination.");
        }

    }    
    
  public static void PhootoAppS_showAlbums(String Username, String Password) {

      if (Username != null && Password != null) {
        try {
          service.setUserCredentials(Username, Password);
        } 
            catch (AuthenticationException e) {
          throw new IllegalArgumentException(
              "Illegal username/password combination.");
        }
      }
    
    
      try {
        showAlbums();
      }
      catch (Exception e)
      {
      throw new IllegalArgumentException(
        "Illegal username/password combination.");
      }
    
      
  }
  
    
    public static void showAlbums() throws Exception {
      List<AlbumEntry> albums = getAlbums();
      int count = 1;
      if (albums.size() == 0) {
        println("No albums found.");
      } else {
        for (AlbumEntry entry : albums) {
          println("Album " + count++ + ") " + entry.getTitle().getPlainText());
          println(entry.getDescription().getPlainText());
          println(entry.getDate().toString());
        }
      }
    }
  
    public static void println(String str) {
      System.out.println(str);
    }
 
    public static final String API_PREFIX
        = "http://picasaweb.google.com/data/feed/api/user/";

    

    /**
     * Retrieves the albums for the given user.
     */
    public static List<AlbumEntry> getAlbums(String username) throws IOException,
        ServiceException {

      String albumUrl = API_PREFIX + username;
      UserFeed userFeed = getFeed(albumUrl, UserFeed.class);
      List<GphotoEntry> entries = userFeed.getEntries();
      List<AlbumEntry> albums = new ArrayList<AlbumEntry>();
      for (GphotoEntry entry : entries) {
        GphotoEntry adapted = entry.getAdaptedEntry();
        if (adapted instanceof AlbumEntry) {
          albums.add((AlbumEntry) adapted);
        }
      }
      return albums;
    }

    /**
     * Retrieves the albums for the currently logged-in user.  This is equivalent
     * to calling {@link #getAlbums(String)} with "default" as the username.
     */
    public static List<AlbumEntry> getAlbums() throws IOException, ServiceException {
      return getAlbums("default");
    }

    /**
     * Retrieves the tags for the given user.  These are tags aggregated across
     * the entire account.
     */
    public static List<TagEntry> getTags(String uname) throws IOException,
        ServiceException {
      String tagUrl = API_PREFIX + uname + "?kind=tag";
      UserFeed userFeed = getFeed(tagUrl, UserFeed.class);

      List<GphotoEntry> entries = userFeed.getEntries();
      List<TagEntry> tags = new ArrayList<TagEntry>();
      for (GphotoEntry entry : entries) {
        GphotoEntry adapted = entry.getAdaptedEntry();
        if (adapted instanceof TagEntry) {
          tags.add((TagEntry) adapted);
        }
      }
      return tags;
    }

    /**
     * Retrieves the tags for the currently logged-in user.  This is equivalent
     * to calling {@link #getTags(String)} with "default" as the username.
     */
    public static List<TagEntry> getTags() throws IOException, ServiceException {
      return getTags("default");
    }

    /**
     * Retrieves the photos for the given album.
     */
    public static List<PhotoEntry> getPhotos(AlbumEntry album) throws IOException,
        ServiceException {

      String feedHref = getLinkByRel(album.getLinks(), Link.Rel.FEED);
      AlbumFeed albumFeed = getFeed(feedHref, AlbumFeed.class);

      List<GphotoEntry> entries = albumFeed.getEntries();
      List<PhotoEntry> photos = new ArrayList<PhotoEntry>();
      for (GphotoEntry entry : entries) {
        GphotoEntry adapted = entry.getAdaptedEntry();
        if (adapted instanceof PhotoEntry) {
          photos.add((PhotoEntry) adapted);
        }
      }
      return photos;
    }

    /**
     * Retrieves the comments for the given photo.
     */
    public static List<CommentEntry> getComments(PhotoEntry photo) throws IOException,
        ServiceException {

      String feedHref = getLinkByRel(photo.getLinks(), Link.Rel.FEED);
      AlbumFeed albumFeed = getFeed(feedHref, AlbumFeed.class);

      List<GphotoEntry> entries = albumFeed.getEntries();
      List<CommentEntry> comments = new ArrayList<CommentEntry>();
      for (GphotoEntry entry : entries) {
        GphotoEntry adapted = entry.getAdaptedEntry();
        if (adapted instanceof CommentEntry) {
          comments.add((CommentEntry) adapted);
        }
      }
      return comments;
    }

    /**
     * Retrieves the tags for the given taggable entry.  This is valid on user,
     * album, and photo entries only.
     */
    public static List<TagEntry> getTags(GphotoEntry<?> parent) throws IOException,
        ServiceException {

      String feedHref = getLinkByRel(parent.getLinks(), Link.Rel.FEED);
      feedHref = addKindParameter(feedHref, "tag");
      AlbumFeed albumFeed = getFeed(feedHref, AlbumFeed.class);

      List<GphotoEntry> entries = albumFeed.getEntries();
      List<TagEntry> tags = new ArrayList<TagEntry>();
      for (GphotoEntry entry : entries) {
        GphotoEntry adapted = entry.getAdaptedEntry();
        if (adapted instanceof TagEntry) {
          tags.add((TagEntry) adapted);
        }
      }
      return tags;
    }

    /**
     * Album-specific insert method to insert into the gallery of the current
     * user, this bypasses the need to have a top-level entry object for parent.
     */
/*
    public AlbumEntry insertAlbum(AlbumEntry album)
        throws IOException, ServiceException {
      String feedUrl = API_PREFIX + "default";
      return service.insert(new URL(feedUrl), album);
    }
*/
    /**
     * Insert an entry into another entry.  Because our entries are a hierarchy,
     * this lets you insert a photo into an album even if you only have the
     * album entry and not the album feed, making it quicker to traverse the
     * hierarchy.
     */
/*
    public <T extends GphotoEntry> T insert(GphotoEntry<?> parent, T entry)
        throws IOException, ServiceException {

      String feedUrl = getLinkByRel(parent.getLinks(), Link.Rel.FEED);
      return service.insert(new URL(feedUrl), entry);
    }
*/
    /**
     * Helper function to allow retrieval of a feed by string url, which will
     * create the URL object for you.  Most of the Link objects have a string
     * href which must be converted into a URL by hand, this does the conversion.
     */
    public static <T extends GphotoFeed> T getFeed(String feedHref,
        Class<T> feedClass) throws IOException, ServiceException {
/*      
      println("Get Feed URL: " + feedHref);
*/      
      return service.getFeed(new URL(feedHref), feedClass);
      }

    /**
     * Helper function to add a kind parameter to a url.
     */
    public static String addKindParameter(String url, String kind) {
      if (url.contains("?")) {
        return url + "&kind=" + kind;
      } else {
        return url + "?kind=" + kind;
      }
    }

    /**
     * Helper function to get a link by a rel value.
     */
    public static String getLinkByRel(List<Link> links, String relValue) {
      for (Link link : links) {
        if (relValue.equals(link.getRel())) {
          return link.getHref();
        }
      }
      throw new IllegalArgumentException("Missing " + relValue + " link.");
    }


    public static void showAlbumPhotos(List<AlbumEntry> albums) throws Exception {

        AlbumEntry albumEntry;
        try {
          albumEntry = getEntry(albums, "album");
        } catch (ExitException ee) {
          return;
        }

        List<PhotoEntry> photos = getPhotos(albumEntry);
        int count = 1;
        if (photos.size() == 0) {
          println("No photos found.");
        } else {
          for (PhotoEntry entry : photos) {
            println("Photo " + count++ + ") " + entry.getTitle().getPlainText());
            println(entry.getDescription().getPlainText());
          }
        }
    }

    public static List<PhotoEntry> showAlbumPhotos2(AlbumEntry albumEntry) throws Exception {
        List<PhotoEntry> photos = getPhotos(albumEntry);
        return photos;
    }

    public static <T extends GphotoEntry> T getEntry(List<T> entries, String name)
          throws Exception {
        while (true) {
          int index = getInt("which " + name) - 1;
          if (index >= 0 && index < entries.size()) {
            return entries.get(index);
          } else {
            println("Invalid index " + index);
          }
        }
      } 
      
      
    public static int getInt(String name) throws ExitException, IOException {
      while (true) {
        String input = getString(name);
        try {
          return Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
          println("Invalid number " + input);
        }
      }
    }      

    public static String getString(String name) throws ExitException,
        IOException {
      print("Please enter ");
      print(name);
      println(":");
      System.out.flush();
      String result = "0";
      result = result.trim();
      if (result.length() == 0) {
        return "-1";
      }
      return result;
    }

    public static void print(String str) {
      System.out.print(str);
    }
    
    public static class ExitException extends Exception {
      // Empty, just used to exit quickly.
    }
    
}
