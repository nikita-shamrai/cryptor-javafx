package com.example.cryptorjfxv2;

public class DecryptorWithOffsetKey {

    private final char[] alphabetCharArray;

    public DecryptorWithOffsetKey(char[] alphabetCharArray){
        this.alphabetCharArray = alphabetCharArray;
    }

    public String decryptTextWithOffsetKey(String cryptoText, int key) {
        StringBuilder sb = new StringBuilder();
        char[] cryptoTextArray = cryptoText.toCharArray();
        for (int i = 0; i < cryptoTextArray.length; i++) {
            for (int j = 0; j < alphabetCharArray.length; j++) {
                if (Character.isUpperCase(cryptoTextArray[i])) {
                    char temp = Character.toLowerCase(cryptoTextArray[i]);
                    if (temp == alphabetCharArray[j]) {
                        if ((j - key) % alphabetCharArray.length < 0) {
                            char c = alphabetCharArray[(j + alphabetCharArray.length - key) % alphabetCharArray.length];
                            sb.append(Character.toUpperCase(c));
                        } else {
                            char c = alphabetCharArray[(j - key) % alphabetCharArray.length];
                            sb.append(Character.toUpperCase(c));
                        }
                    }
                } else {
                    if (cryptoTextArray[i] == alphabetCharArray[j]) {
                        if ((j - key) % alphabetCharArray.length < 0) {
                            char c = alphabetCharArray[(j + alphabetCharArray.length - key) % alphabetCharArray.length];
                            sb.append(c);
                        } else {
                            char c = alphabetCharArray[(j - key) % alphabetCharArray.length];
                            sb.append(c);
                        }
                    }
                }
            }
        }
        return sb.toString();
    }
}
