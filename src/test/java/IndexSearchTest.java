import com.EVA.L.IndexSearch;
import org.apache.lucene.search.IndexSearcher;
import org.junit.Test;

public class IndexSearchTest {
    @Test
    public void test1(){
        IndexSearch indexSearch = new IndexSearch(".\\src\\test\\java\\indexStoreDirectory");
        try {
            indexSearch.indexSearch("content","西门",10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
