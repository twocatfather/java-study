package day2.예제3;

public abstract class ConverterFactory {
    public final byte[] convertDocument(byte[] input) {
        DocumentConverter converter = createConverter();
        return converter.convert(input);
    }

    protected abstract DocumentConverter createConverter();
}
