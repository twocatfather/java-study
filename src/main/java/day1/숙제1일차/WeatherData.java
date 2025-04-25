package day1.숙제1일차;

/**
 *  날씨정보를 나타내는 불변 클래스
 *  double temperatureCelsius,
 *  int humidityPercent,
 *  double pressureHPa,
 *  double windSpeedMps,
 *  int widDirectionDegrees
 *  1. 유효성 검증 (온도 범위, 습도 범위등)
 *  2. 온도 단위 변환 메소드 (섭씨 <-> 화씨)
 *  3. 날씨 상태 계산 메소드 (추움, 더움, 적당함)
 *  4. 불쾌지수 계산메서드
 *  5. withCelsius, withHumidity
 */

enum WeatherCondition {
    FREEZING("매우 추움"),
    COLD("추움"),
    COOL("서늘함"),
    MILD("적당함"),
    WARM("따듯함"),
    HOT("더움"),
    VERY_HOT("매우 더움");

    private final String description;

    WeatherCondition(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

enum DiscomfortLevel {
    COMFORTABLE("쾌적함"),
    SLIGHTLY_UNCOMFORTABLE("약간 쾌적함"),
    UNCOMFORTABLE("불쾌함"),
    VERY_UNCOMFORTABLE("매우 불쾌함"),
    ;

    private final String description;

    DiscomfortLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

public record WeatherData(
        double temperatureCelsius,
        int humidityPercent,
        double pressureHPa,
        double windSpeedMps,
        int widDirectionDegrees
) {
    private static final double MIN_TEMP_CELSIUS = -100.0;
    private static final double MAX_TEMP_CELSIUS = 100.0;
    private static final int MIN_HUMIDITY = 0;
    private static final int MAX_HUMIDITY = 100;
    private static final double MIN_PRESSURE = 800.0;
    private static final double MAX_PRESSURE = 1200.0;
    private static final double MIN_WIND_SPEED = 0.0;
    private static final double MAX_WIND_SPEED = 200.0;

    public WeatherData {
        // 온도 유효성 검증
        if (temperatureCelsius < MIN_TEMP_CELSIUS || temperatureCelsius > MIN_TEMP_CELSIUS) {
            throw new IllegalArgumentException(
                    String.format("Temperature must be between %.1f and %.1f Celsius",
                            MIN_TEMP_CELSIUS, MAX_TEMP_CELSIUS)
            );
        }
    }

    public double getTemperatureFahrenheit() {
        return (temperatureCelsius * 9/5) + 32;
    }

    public WeatherCondition getWeatherCondition() {
        if (temperatureCelsius < -10) return WeatherCondition.FREEZING;
        if (temperatureCelsius < 0) return WeatherCondition.COLD;
        if (temperatureCelsius < 10) return WeatherCondition.COOL;
        if (temperatureCelsius < 20) return WeatherCondition.MILD;
        if (temperatureCelsius < 30) return WeatherCondition.WARM;
        if (temperatureCelsius < 35) return WeatherCondition.HOT;
        return WeatherCondition.VERY_HOT;
    }

    /**
     *  0.81 * 기온 + 0.01 * 습도 * (0.99 * 기온 - 14.3) + 46.3;
     */
    public double getDiscomfortIndex() {
        return 0.81 * temperatureCelsius +
                0.01 * humidityPercent * (0.99 * temperatureCelsius - 14.3)
                + 46.3;
    }

    public DiscomfortLevel getDiscomfortLevel() {
        double index = getDiscomfortIndex();

        if (index < 68) return DiscomfortLevel.COMFORTABLE;
        if (index < 75) return DiscomfortLevel.SLIGHTLY_UNCOMFORTABLE;
        if (index < 80) return DiscomfortLevel.UNCOMFORTABLE;
        return DiscomfortLevel.VERY_UNCOMFORTABLE;
    }

    public WeatherData withHumidity(int newHumidity) {
        return new WeatherData(
                this.temperatureCelsius,
                newHumidity,
                this.pressureHPa,
                this.windSpeedMps,
                this.widDirectionDegrees
        );
    }
}
