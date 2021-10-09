package com.EVA.L;

import java.io.File;

public class FilePreprocess {
    private ToSmallFile toSmallFile;
    private CharactorProcess charactorProcess;
    public void preprocess(File file, String outputDir){
        try {
            toSmallFile.splitToSmallFile(charactorProcess.charactorProcess(file,outputDir+"output.all"),outputDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
