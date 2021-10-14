package com.EVA.L;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexOptions;
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

    /**
     * 创建索引
     * @throws IOException
     */
    public void createdIndex() throws IOException{
        Directory indexStorePath = new NIOFSDirectory(Paths.get(INDEX_STORE_PATH));
        Analyzer analyzer = new CJKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(indexStorePath,config);

        File filesDir = new File(INPUT_DIR);
        File[] files = filesDir.listFiles();

        //设置Field的基本属性
        //设置文件名的索引的基本属性
        FieldType fileNameType = new FieldType();
        fileNameType.setStored(true);
        fileNameType.setIndexOptions(IndexOptions.DOCS);

        FieldType contentType = new FieldType();
        contentType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        contentType.setStored(true);
        contentType.setTokenized(true);
        contentType.setStoreTermVectors(true);
        contentType.setStoreTermVectorPositions(true);
        contentType.setStoreTermVectorOffsets(true);
        contentType.setStoreTermVectorPayloads(true);


    for (File file : files) {
            String fileName = file.getName();
            //System.out.println(fileName);
            if (fileName.substring(fileName.lastIndexOf(".")).equals(".txt")) {
                Document doc = new Document();

               // Field field = new StringField("filename", fileName, Field.Store.YES);
                Field fieldFileName = new Field("filename",fileName,fileNameType);
                doc.add(fieldFileName);
                //field = new TextField("content", loadFileToString(file), Field.Store.NO );
                Field content = new Field("content",loadFileToString(file),contentType);
                doc.add(content);
                writer.addDocument(doc);
                writer.commit();
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
