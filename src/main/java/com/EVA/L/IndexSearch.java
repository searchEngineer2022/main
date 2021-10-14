package com.EVA.L;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexCommit;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

public class IndexSearch {
    private String INDEX_STORE_PATH;
    public void indexSearch(String searchType,String searchKey,int numberOfResult ) throws Exception{
        System.out.println("==========使用索引方式进行搜索==========");
        System.out.println("------------------------------------");

        Directory indexStoreDirectory = new NIOFSDirectory(Paths.get(INDEX_STORE_PATH));
        DirectoryReader directoryReader = DirectoryReader.open(indexStoreDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        Term t = new Term(searchType,searchKey);
        Query q =new TermQuery(t);
        Date beginTime = new Date();
        TopDocs topDocs = indexSearcher.search(q,numberOfResult);
        for(ScoreDoc sdoc:topDocs.scoreDocs){
            System.out.println("find "+sdoc);
        }

    }
}
