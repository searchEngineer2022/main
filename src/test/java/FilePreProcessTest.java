import com.EVA.L.FilePreprocess;
import org.junit.Test;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FilePreProcessTest {
    @Test
    public void charactorProcesstest() {
        File helloFile = new File("E:\\programExcercise\\java\\searchEngineToy\\src\\test\\java\\hello.txt");
        try {
            FilePreprocess.charactorProcess(helloFile, "E:\\programExcercise\\java\\searchEngineToy\\src\\test\\java\\hello1.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws Exception{
        File testFile = new File("E:\\programExcercise\\java\\searchEngineToy\\src\\test\\java\\宇宙巨校闪级生.txt");

        String outputDir = "E:\\programExcercise\\java\\searchEngineToy\\src\\test\\java\\preprocessOutputDir\\";
        FilePreprocess filePreprocess = new FilePreprocess();
        try {
            filePreprocess.preprocess(testFile, outputDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() throws Exception {



    }

    @Test
    public void splitToSmallFileTest(){
        File testFile = new File("E:\\programExcercise\\java\\searchEngineToy\\src\\test\\java\\宇宙巨校闪级生.txt");
        String outPathDirectory =".\\src\\test\\java\\preprocessOutputDir\\" ;
        try {
            FilePreprocess.splitToSmallFile(testFile,outPathDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
