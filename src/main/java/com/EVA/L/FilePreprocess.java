package com.EVA.L;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FilePreprocess {
    /**
     * 全角半角转换核心函数
     * @param a
     * @return
     */
    public static String replace(String a) {
        char[] c = a.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65288 && c[i] < 65375) c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 处理指定文件的全角半角符号
     * @param file
     * @param destFile
     * @return
     * @throws Exception
     */
    public static File charactorProcess(File file,String destFile) throws Exception {


        BufferedWriter writer = new BufferedWriter(new FileWriter(destFile));
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line!=null){
            String newline = replace(line);
            writer.write(newline);
            writer.newLine();
            line = reader.readLine();
        }
        reader.close();
        writer.close();
        return new File(destFile);
    }

    /**
     * 大文件切分
     * @param file
     * @param outputPath
     * @throws IOException
     */
    public static void splitToSmallFile(File file,String outputPath) throws IOException {
        //文件计数器，参与产生文件名
        int filePointer =0;
        //控制单个文件的最大长度
        int MaxSize = 10240;
        //创建输出流
        BufferedWriter writer = null;
        //创建输入流
        BufferedReader reader = new BufferedReader(new FileReader(file));
        //创建字符缓冲区
        StringBuffer buffer = new StringBuffer();
        String line = reader.readLine();
        while(line!=null){
            buffer.append(line).append("\r\n");
            if(buffer.toString().getBytes().length>=MaxSize){
                writer=new BufferedWriter(new FileWriter(outputPath+"output"+filePointer+".txt"));
                writer.write(buffer.toString());
                writer.close();
                filePointer++;
                buffer=new StringBuffer();
            }
            line = reader.readLine();
        }
        writer=new BufferedWriter(new FileWriter(outputPath+"output"+filePointer+".txt"));
        writer.write(buffer.toString());
        writer.close();
    }

    /**
     * 文件预处理接口
     * @param file
     * @param outputDir
     */
    public void preprocess(File file, String outputDir) throws Exception{


        Utils.directoryCreate(outputDir);


        try {
            splitToSmallFile(charactorProcess(file,outputDir+"output.all"),outputDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
