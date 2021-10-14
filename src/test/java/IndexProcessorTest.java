import com.EVA.L.IndexProcessor;
import org.junit.Test;

import java.io.IOException;

public class IndexProcessorTest {
    @Test
    public void test1(){
        IndexProcessor indexProcessor = new IndexProcessor(".\\src\\test\\java\\preprocessOutputDir");
        try {
            indexProcessor.createdIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
