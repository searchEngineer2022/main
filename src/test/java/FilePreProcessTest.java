import com.EVA.L.FilePreprocess;
import org.junit.Test;

import java.io.File;

public class FilePreProcessTest {
    @Test
    public void test(){
        File testFile = new File("E:\\programExcercise\\java\\searchEngineToy\\src\\test\\java\\宇宙巨校闪级生.txt");
        String outputDir = "E:\\programExcercise\\java\\searchEngineToy\\src\\test\\java\\preprocessOutputDir";
        FilePreprocess filePreprocess = new FilePreprocess();
        filePreprocess.preprocess(testFile,outputDir);
    }
}
