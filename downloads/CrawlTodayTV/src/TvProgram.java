import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by phuong on 3/11/2015.
 */
public class TvProgram {
    private String id;
    private String title = "";
    private String description = "";
    private String type = "";
    private String start_date;
    private String end_date;
    private String channel = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public DBObject toMongoObject() {
        BasicDBObject ob = new BasicDBObject();
        ob.put("title" , title);
        ob.put("description" , description);
        ob.put("id" , id);
        ob.put("chanel" , channel);
        ob.put("start_date" , start_date);
        ob.put("end_date" , end_date);
        ob.put("type" , type);
        return ob;
    }

}
