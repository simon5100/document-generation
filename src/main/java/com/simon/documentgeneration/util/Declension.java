package com.simon.documentgeneration.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Declension {

    StringBuilder stringBuilder;

    public String getGenitiveCase(String word) {

        stringBuilder = new StringBuilder(word);


        stringBuilder.replace(word.length()-2, word.length(), "ого");


        return stringBuilder.toString();
    }



}
