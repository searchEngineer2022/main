package com.EVA.L;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;

import javax.print.Doc;
import java.nio.file.Paths;
import java.util.Date;

public class IndexSearch {
    private String INDEX_STORE_PATH;
    public IndexSearch(String indexStorePath){
        this.INDEX_STORE_PATH = indexStorePath;
    }

    /**
     *
     * @param searchType 要检索的Field
     * @param searchKey
     * @param numberOfResult
     * @throws Exception
     */
    public void indexSearch(String searchType,String searchKey,int numberOfResult ) throws Exception{
        System.out.println("==========使用索引方式进行搜索==========");
        System.out.println("------------------------------------");
        //创建索引搜索器
        Directory indexStoreDirectory = new NIOFSDirectory(Paths.get(INDEX_STORE_PATH));
        DirectoryReader directoryReader = DirectoryReader.open(indexStoreDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        //分词器
        Analyzer analyzer = new CJKAnalyzer();

        //查询生成器
        QueryParser parser = new QueryParser(searchType,analyzer);
        Query query = parser.parse(searchKey);

        Date beginTime = new Date();
        //执行查询并返回结果
        TopDocs topDocs = indexSearcher.search(query,numberOfResult);
        System.out.println(topDocs.totalHits);
        int i = 1;
        for(ScoreDoc sdoc:topDocs.scoreDocs){
            Document doc = indexSearcher.doc(sdoc.doc);
            //System.out.println("文档编号 :"+sdoc.doc);
            System.out.println("第"+i+"个记录");
            System.out.println("文档编号:"+doc.get("filename"));
            System.out.println("文档评分:" + sdoc.score);
            i++;
        }
        directoryReader.close();
        indexStoreDirectory.close();
        Date endTime = new Date();
        long timeOfSearch = endTime.getTime()-beginTime.getTime();
        System.out.println(timeOfSearch+" ms");
    }
}
