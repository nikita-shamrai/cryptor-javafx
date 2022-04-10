package com.example.cryptorjfxv2;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecryptorWithBruteForce {

    private final char[] alphabetCharArray;
    private final String regexDotSpaceUpperCase = "\\.\\s([А-Я]|[A-Z])";
    private final String regexComaSpaceLowerCase = "([а-я]|[a-z]),\\s([а-я]|[a-z])";
    private final Pattern dotPattern = Pattern.compile(regexDotSpaceUpperCase);
    private final Pattern comaPattern = Pattern.compile(regexComaSpaceLowerCase);

    public static Map <Integer, String> tempMap = new HashMap<>();
    public static int yourOffsetKeyResult;

    public DecryptorWithBruteForce(char[] alphabetCharArray) {
        this.alphabetCharArray = alphabetCharArray;
    }

//    public String bruteForceDecryptor(String incomingCryptoText) {
//        char[] incomingCryptoTextCharArray = incomingCryptoText.toCharArray();
//        int offsetKey = 0;
//        String result = "Text doesn't contain test criterion (dots and comas)";
//        Map<Integer, String> decryptedMap = new HashMap<>();
//        while (offsetKey < alphabetCharArray.length) {
//            StringBuilder sb = new StringBuilder();
//            for (char cryptoCharValue : incomingCryptoTextCharArray) {
//                if (cryptoCharValue == '\n') {
//                    sb.append('\n');
//                    continue;
//                }
//                for (int j = 0; j < alphabetCharArray.length; j++) {
//                    if (Character.isUpperCase(cryptoCharValue)) {
//                        char tempChar = Character.toLowerCase(cryptoCharValue);
//                        if (tempChar == alphabetCharArray[j]) {
//                            char c = alphabetCharArray[(j + offsetKey) % alphabetCharArray.length];
//                            sb.append(Character.toUpperCase(c));
//                        }
//                    } else if (cryptoCharValue == alphabetCharArray[j]) {
//                        sb.append(alphabetCharArray[(j + offsetKey) % alphabetCharArray.length]);
//                    }
//                }
//            }
//            decryptedMap.put(alphabetCharArray.length-offsetKey, sb.toString());
//            offsetKey++;
//        }
//
//        Map<Integer, String> filteredOneDecryptedMap = new HashMap<>();
//        Map<Integer, String> filteredTwoDecryptedMap = new HashMap<>();
//        Map<Integer, String> NonFilteredDecryptedMap = new HashMap<>();
//        for (int key :
//                decryptedMap.keySet()) {
//            String value = decryptedMap.get(key);
//            Matcher dotMatcher = dotPattern.matcher(value);
//            Matcher comaMatcher = comaPattern.matcher(value);
//            boolean dotFound = dotMatcher.find();
//            boolean comaFound = comaMatcher.find();
//            if (dotFound && comaFound) {
//                filteredOneDecryptedMap.put(key, value);
//            } else if (dotFound || comaFound) {
//                filteredTwoDecryptedMap.put(key, value);
//            } else {
//                //System.out.println("None fits criterion. All variants added.\n");
//                NonFilteredDecryptedMap.put(key, value);
//            }
//        }
//
//        if (filteredOneDecryptedMap.size() > 1) {
//            System.out.println("More than 1 variant fits. Choose one manually:");
//            printMap(filteredOneDecryptedMap);
//            result = chooseCorrectBForceResult(filteredOneDecryptedMap, result);
//        } else if (filteredOneDecryptedMap.size() == 1){
//            for (var entry : filteredOneDecryptedMap.entrySet()) {
//                System.out.println("Your OFFSET KEY is: "+(entry.getKey()));
//                result = entry.getValue();
//            }
//        } else if (filteredOneDecryptedMap.isEmpty() && filteredTwoDecryptedMap.size()==1){
//            for (var entry : filteredTwoDecryptedMap.entrySet()) {
//                System.out.println("Your OFFSET KEY is: "+(entry.getKey()));
//                result = entry.getValue();
//            }
//        }
//        else if (filteredOneDecryptedMap.isEmpty() && filteredTwoDecryptedMap.size()>0){
//            System.out.println("More than 1 variant fits. Choose one manually:");
//            printMap(filteredTwoDecryptedMap);
//            result = chooseCorrectBForceResult(filteredTwoDecryptedMap, result);
//        }
//        else if (filteredOneDecryptedMap.isEmpty() && filteredTwoDecryptedMap.size()==1){
//            for (var entry : filteredTwoDecryptedMap.entrySet()) {
//                System.out.println("Your OFFSET KEY is: "+(entry.getKey()));
//                result = entry.getValue();
//            }
//        }
//        else if (filteredOneDecryptedMap.isEmpty() && filteredTwoDecryptedMap.isEmpty() && NonFilteredDecryptedMap.size()>0){
//            System.out.println("More than 1 variant fits. Choose one manually:");
//            printMap(NonFilteredDecryptedMap);
//            result = chooseCorrectBForceResult(NonFilteredDecryptedMap, result);
//        }
//        return result;
//    }

    public String bruteForceDecryptorForJFX(String incomingCryptoText) {
        char[] incomingCryptoTextCharArray = incomingCryptoText.toCharArray();
        int offsetKey = 0;
        String result = "";
        Map<Integer, String> decryptedMap = new HashMap<>();
        while (offsetKey < alphabetCharArray.length) {
            StringBuilder sb = new StringBuilder();
            for (char cryptoCharValue : incomingCryptoTextCharArray) {
                if (cryptoCharValue == '\n') {
                    sb.append('\n');
                    continue;
                }
                for (int j = 0; j < alphabetCharArray.length; j++) {
                    if (Character.isUpperCase(cryptoCharValue)) {
                        char tempChar = Character.toLowerCase(cryptoCharValue);
                        if (tempChar == alphabetCharArray[j]) {
                            char c = alphabetCharArray[(j + offsetKey) % alphabetCharArray.length];
                            sb.append(Character.toUpperCase(c));
                        }
                    } else if (cryptoCharValue == alphabetCharArray[j]) {
                        sb.append(alphabetCharArray[(j + offsetKey) % alphabetCharArray.length]);
                    }
                }
            }
            decryptedMap.put(alphabetCharArray.length-offsetKey, sb.toString());
            offsetKey++;
        }

        Map<Integer, String> filteredOneDecryptedMap = new HashMap<>();
        Map<Integer, String> filteredTwoDecryptedMap = new HashMap<>();
        Map<Integer, String> NonFilteredDecryptedMap = new HashMap<>();
        for (int key :
                decryptedMap.keySet()) {
            String value = decryptedMap.get(key);
            Matcher dotMatcher = dotPattern.matcher(value);
            Matcher comaMatcher = comaPattern.matcher(value);
            boolean dotFound = dotMatcher.find();
            boolean comaFound = comaMatcher.find();
            if (dotFound && comaFound) {
                filteredOneDecryptedMap.put(key, value);
            } else if (dotFound || comaFound) {
                filteredTwoDecryptedMap.put(key, value);
            } else {
                //System.out.println("None fits criterion. All variants added.\n");
                NonFilteredDecryptedMap.put(key, value);
            }
        }

        if (filteredOneDecryptedMap.size() > 1) {
//            System.out.println("More than 1 variant fits. Choose one manually:");
//            printMap(filteredOneDecryptedMap);
            tempMap = filteredOneDecryptedMap;
//            result = chooseCorrectBForceResult(filteredOneDecryptedMap, result);
        } else if (filteredOneDecryptedMap.size() == 1){
            for (var entry : filteredOneDecryptedMap.entrySet()) {
               // System.out.println("Your OFFSET KEY is: "+(entry.getKey()));
                yourOffsetKeyResult = entry.getKey();
                result = entry.getValue();
            }
        } else if (filteredOneDecryptedMap.isEmpty() && filteredTwoDecryptedMap.size()==1){
            for (var entry : filteredTwoDecryptedMap.entrySet()) {
               // System.out.println("Your OFFSET KEY is: "+(entry.getKey()));
                yourOffsetKeyResult = entry.getKey();
                result = entry.getValue();
            }
        }
        else if (filteredOneDecryptedMap.isEmpty() && filteredTwoDecryptedMap.size()>0){
//            System.out.println("More than 1 variant fits. Choose one manually:");
//            printMap(filteredTwoDecryptedMap);
//            result = chooseCorrectBForceResult(filteredTwoDecryptedMap, result);
            tempMap = filteredTwoDecryptedMap;
        }
        else if (filteredOneDecryptedMap.isEmpty() && filteredTwoDecryptedMap.size()==1){
            for (var entry : filteredTwoDecryptedMap.entrySet()) {
                //System.out.println("Your OFFSET KEY is: "+(entry.getKey()));
                yourOffsetKeyResult = entry.getKey();
                result = entry.getValue();
            }
        }
        else if (filteredOneDecryptedMap.isEmpty() && filteredTwoDecryptedMap.isEmpty() && NonFilteredDecryptedMap.size()>0){
//            System.out.println("More than 1 variant fits. Choose one manually:");
//            printMap(NonFilteredDecryptedMap);
//            result = chooseCorrectBForceResult(NonFilteredDecryptedMap, result);
            tempMap = NonFilteredDecryptedMap;
        }
        return result;
    }

    public String chooseCorrectBForceResultJFX(Map<Integer, String> temp, int offsetKey){
        String result = "notFound";
        if (temp.containsKey(offsetKey)) {
            result = temp.get(offsetKey);
        } else {
            System.out.println(Warnings.VALIDNUM);
        }
        System.out.println("choosecorrect -- "+result);
        return result;
    }

//    private String chooseCorrectBForceResult(Map<Integer, String> filteredOneDecryptedMap, String result) {
//        boolean choiceRightChecker = false;
//        do {
//            Scanner scanner = new Scanner(System.in);
//            if (!scanner.hasNextInt()) {
//                System.out.println(Warnings.ONLYNUM);
//            } else {
//                int selectedKey = scanner.nextInt();
//                if (filteredOneDecryptedMap.containsKey(selectedKey)) {
//                    choiceRightChecker=true;
//                    System.out.println("Your OFFSET KEY is: "+(selectedKey));
//                    result = filteredOneDecryptedMap.get(selectedKey);
//                } else {
//                    System.out.println(Warnings.VALIDNUM);
//                }
//            }
//        } while (!choiceRightChecker);
//        return result;
//    }

//    private void printMap(Map<Integer, String> filteredOneDecryptedMap) {
//        for (var entry : filteredOneDecryptedMap.entrySet()) {
//            if (entry.getValue().length()>=100) {
//                System.out.println(entry.getKey() + "   :   " + entry.getValue().substring(0, 100) + "......");
//            }
//            else {
//                System.out.println(entry.getKey() + "   :   " + entry.getValue() + "......");
//            }
//        }
//    }

    public String mapToString(Map<Integer, String> filteredOneDecryptedMap) {
        StringBuilder sb = new StringBuilder();
        for (var entry : filteredOneDecryptedMap.entrySet()) {
            if (entry.getValue().length()>=100) {
                sb.append(entry.getKey() + "   :   " + entry.getValue().substring(0, 100) + "......");
                sb.append("\n");
            }
            else {
                sb.append(entry.getKey() + "   :   " + entry.getValue());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}