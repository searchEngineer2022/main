package com.EVA.L;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Utils {
    public static void directoryCreate(String outputDir) throws Exception {
        //创建输出文件夹preprocessOutputDir
        String outPathDirectory = outputDir;
        Path path = Paths.get(outPathDirectory);

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        Files.createDirectory(path);
        System.out.println(outputDir+" has been created!");
    }
}
