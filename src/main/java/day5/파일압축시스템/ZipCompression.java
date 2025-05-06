package day5.파일압축시스템;

import java.io.File;
import java.util.List;

public class ZipCompression implements CompressionStrategy{

    @Override
    public void compressFiles(List<File> files, String destination) {
        System.out.println("Zip Compression");
        //zip 파일 압축 알고리즘 구성해보기
        System.out.println("압축할 파일의 수: " + files.size());
        System.out.println("압축 파일 저장 경로: " + destination);
    }
}
