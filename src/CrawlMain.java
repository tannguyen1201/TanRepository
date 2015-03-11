import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.TParser;
/**
 * Created by phuong on 3/11/2015.
 */
public class CrawlMain {
    static final String TODAYTV_MAIN_URL = "http://todaytv.vn";
    static final String TODAYTV_TODAY_URL = "http://todaytv.vn/lichchieu/";
    static final String TODAYTV_DBNAME = "todaytvCalendar";
    static final String TODAYTV_TODAY_COLLECTION = "today";
    static final String TYPE = "today";
    static DB db;

    public static void main(String[] args) {
        Page todayTv = new Page(TODAYTV_TODAY_URL);
        getSchedule(todayTv);
        getTomorrow(todayTv);
    }

    public static void getCollectionInDb(String dbName, String collectionName) {
        try {
            Mongo mongoClient = new Mongo("localhost", 27017);
            db = mongoClient.getDB(dbName);
            System.out.println("Connect to database " + dbName + " successfully");
            DBCollection coll = db.getCollection(collectionName);
            System.out.println("Collection " + collectionName +" selected successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void getSchedule(Page todayTvPage){
        Elements rows = todayTvPage.doc.select("div[style=\"width:100%;float:left;background-color:#dad4b6;\"]");
        TvProgram tvTemp = new TvProgram();
        getCollectionInDb(TODAYTV_DBNAME, TODAYTV_TODAY_COLLECTION);
        DBCollection calendar = db.getCollection(TODAYTV_TODAY_COLLECTION);

        for (Element row: rows) {
            Elements parts = row.getElementsByTag("div");
            if (parts == null || parts.size() < 4) continue;{
                tvTemp.setId("");
                tvTemp.setStart_date(parts.get(2).text());
                tvTemp.setDescription(parts.get(3).text());
                tvTemp.setTitle(parts.get(4).text());
                tvTemp.setChannel(TYPE);
//                tvTemp.setEnd_date("");
                tvTemp.setType("");
                //calendar.insert(tvTemp.toMongoObject());
            }
        }
    }

    public static String getTomorrow(Page page){
        String tomorrow = TODAYTV_MAIN_URL+ TParser.getContent(page.doc.html(),
                "(?s)Ngày trước</a>.*?<a href=\"",
                "ngay-sau");
        System.out.println(tomorrow);
        return tomorrow;
    }
}
