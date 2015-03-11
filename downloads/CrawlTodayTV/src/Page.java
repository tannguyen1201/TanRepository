import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by phuong on 3/11/2015.
 */
public class Page {
    Document doc;
    Element element;
    String url;

    public Page(String url) {
        this.url = url;
        Init();
    }

    public void Init() {
        doc = InitDoc(url);
        element = doc.body();
    }

    public Document InitDoc(String url) {
        Document result = null;
        try {
            result = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
