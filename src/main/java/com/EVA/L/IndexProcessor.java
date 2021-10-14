package com.EVA.L;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;

import java.io.*;
import java.nio.file.Paths;

public class IndexProcessor {
    public static String INDEX_STORE_PATH;
    private String INPUT_DIR;

    public IndexProcessor(String inputdir){
        this.INPUT_DIR = inputdir;
        String indexStorePath = ".\\src\\test\\java\\indexStoreDirectory";
        try {
            Utils.directoryCreate(indexStorePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.INDEX_STORE_PATH = indexStorePath;
    }

    public void createdIndex() throws IOException{
        Directory indexStorePath = new NIOFSDirectory(Paths.get(INDEX_STORE_PATH));
        Analyzer analyzer = new CJKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(indexStorePath,config);

        File filesDir = new File(INPUT_DIR);
        File[] files = filesDir.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.substring(fileName.lastIndexOf(".")).equals(".txt")) {
                Document doc = new Document();
                Field field = new StringField("filename", file.getName(), Field.Store.YES);
                doc.add(field);
                field = new TextField("content", loadFileToString(file), Field.Store.NO);
                doc.add(field);
                writer.addDocument(doc);
            }
        }
        writer.close();
    }

    /**
     * 从文件中把内容读出，所有的内容放在一个String中返回
     * @param file
     * @return
     */
    public String loadFileToString(File file) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuffer sb = new StringBuffer();
        String line = br.readLine();
        while (null !=line){
            sb.append(line);
            line = br.readLine();
        }
        br.close();
        return sb.toString();
    }
}
