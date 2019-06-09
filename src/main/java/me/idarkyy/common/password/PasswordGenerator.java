package me.idarkyy.common.password;

import java.util.Random;

public class PasswordGenerator {
    public static final String DEFAULT_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String DEFAULT_NUMBERS = "1234567890";
    public static final String DEFAULT_CHARS = "!@#$%^&*()_-+=";

    public static void main(String[] args) {
        long curr = System.currentTimeMillis();
        System.out.println(new PasswordGenerator().generate(1000000000, true, true, false));
        System.out.println((System.currentTimeMillis() - curr) + "ms");
    }

    public String generate(int length, boolean uppercase, boolean numbers, boolean chars) {
        String charset = DEFAULT_LETTERS;

        if (uppercase) {
            charset += DEFAULT_LETTERS.toUpperCase();
        }

        if (numbers) {
            charset += DEFAULT_NUMBERS;
        }

        if (chars) {
            charset += DEFAULT_CHARS;
        }

        return new Generator(charset).generate(length);
    }

    private static class Generator {
        private final Random random;
        private final String charset;

        public Generator(String charset) {
            this.random = new Random();
            this.charset = charset;
        }

        public String generate(int length) {
            if (length < 1) {
                throw new IllegalArgumentException("Password length must be above zero");
            }

            StringBuilder builder = new StringBuilder();
            char last = ' ';
            for (int i = 0; i < length; i++) {
                char c;
                while ((c = randomChar()) == last) {
                    c = randomChar();
                }

                last = c;
                builder.append(c);
            }

            return builder.toString();
        }

        public char randomChar() {
            return charset.charAt(random.nextInt(charset.length()));
        }
    }
}