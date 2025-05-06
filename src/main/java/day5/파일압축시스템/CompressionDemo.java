package day5.파일압축시스템;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CompressionDemo {
    public static void main(String[] args) {
        List<File> files = new ArrayList<>();
        files.add(new File("document1.txt"));
        files.add(new File("image1.jpg"));
        files.add(new File("spreadsheet1.xlsx"));

        CompressionStrategy zipCompression = new ZipCompression();

        FileCompressor compressor = new FileCompressor();
        compressor.setCompressionStrategy(zipCompression);

        compressor.compressFiles(files, "archive.zip");

        CompressionStrategy rarCompression = new RarCompression();
        compressor.setCompressionStrategy(rarCompression);
        compressor.compressFiles(files, "archive.rar");

        // gz, 7z, tar
    }
}
