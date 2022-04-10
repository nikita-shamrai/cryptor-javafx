package com.example.cryptorjfxv2;

public class AlphabetProvider {
    private static final String alphabetString = generateAlphabet();
    private static final char[] alphabetCharArray = convertAlphabetToCharArray();

    public String getAlphabetString() {
        return alphabetString;
    }

    public char[] getAlphabetCharArray() {
        return alphabetCharArray;
    }

    private static char[] convertAlphabetToCharArray() {
        return generateAlphabet().toCharArray();
    }

    private static String generateAlphabet() {
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch = 'а'; ch <= 'я'; ++ch) {
            stringBuilder.append(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            stringBuilder.append(ch);
        }
        stringBuilder.append("0123456789.,”:!?()=- ");
        return stringBuilder.toString();
    }
}
