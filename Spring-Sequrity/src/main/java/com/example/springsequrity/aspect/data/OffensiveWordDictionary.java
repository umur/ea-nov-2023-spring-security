package com.example.springsequrity.aspect.data;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public enum OffensiveWordDictionary {
    SPRING("spring"),
    EXAMPLE("example"),
    SAMPLE("sample");

    private final String offensiveWord;

    OffensiveWordDictionary(String offensiveWord) {
        this.offensiveWord = offensiveWord;
    }
    public String getOffensiveWord() {
        return offensiveWord;
    }

    public static boolean isOffensiveWord(String word){
        return Arrays.stream(OffensiveWordDictionary.values())
                .anyMatch(offensiveWordDictionary -> offensiveWordDictionary.getOffensiveWord().equalsIgnoreCase(word));
    }

    public static boolean hasOffensiveWord(Set<String> words){
        return Arrays.stream(OffensiveWordDictionary.values())
                .anyMatch(offensiveWordDictionary -> words.contains(offensiveWordDictionary.getOffensiveWord().toLowerCase()));
    }
}
