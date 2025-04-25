package day2.실습;

import java.util.Objects;

public class Person {
    // 필수 필드 (final 로 불변성 확보)
    private final String name;
    private final int age;

    // 선택적 필드 (final 로 불변성 확보)
    private final String address;
    private final String phoneNumber;
    private final String email;

    // age 를 나이 유효성 검증 0 미만 보다 작을 순 없고 150 세 초과
    // email 간단한 유효성 검증을 할것인데 @가 들어가있는지, (null 아니거나 비어있지 않을 경우)
    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
    }

    public static class Builder {
        // 필수 필드 (final 로 불변성 확보)
        private final String name;
        private final int age;

        // 선택적 필드 (final 로 불변성 확보)
        private String address = "";
        private String phoneNumber = "";
        private String email = "";

        public Builder(String name, int age) {
            this.name = Objects.requireNonNull(name, "Name must not be null");
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("Age must be between 0 and 150");
            }
            this.age = age;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder email(String email) {
            if (email != null && !email.isEmpty() && !email.contains("@")) {
                throw new IllegalArgumentException("Email must contain @");
            }
            this.email = email;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
