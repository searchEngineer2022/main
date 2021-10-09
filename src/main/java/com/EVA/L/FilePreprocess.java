package com.EVA.L;

import java.io.File;

public class FilePreprocess {
    ToSmallFile toSmallFile = new ToSmallFile();
    CharactorProcess charactorProcess=new CharactorProcess();
    public void preprocess(File file, String outputDir){
        try {
            toSmallFile.splitToSmallFile(charactorProcess.charactorProcess(file,outputDir+"output.all"),outputDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
