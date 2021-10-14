import com.EVA.L.IndexProcessor;
import org.junit.Test;

import java.io.IOException;

public class IndexProcessorTest {
    /**
     * 创建索引
     */
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
