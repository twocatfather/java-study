package day2.예제3;

public class PdfToWordConverterFactory extends ConverterFactory{

    @Override
    protected DocumentConverter createConverter() {
        return new PdfToWordConverter();
    }
}
