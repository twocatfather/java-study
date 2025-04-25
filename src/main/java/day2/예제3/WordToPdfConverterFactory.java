package day2.예제3;

public class WordToPdfConverterFactory extends ConverterFactory{
    @Override
    protected DocumentConverter createConverter() {
        return new WordToPdfConverter();
    }
}
