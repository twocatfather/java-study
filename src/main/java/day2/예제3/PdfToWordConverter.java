package day2.예제3;

public class PdfToWordConverter implements DocumentConverter{
    @Override
    public byte[] convert(byte[] input) {
        return new byte[0];
    }

    @Override
    public String getOutputFormat() {
        return "";
    }
}
