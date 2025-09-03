package com.simon.documentgeneration.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ConvertPointsInCM {

    public float cm(float cm) {
        return cm * 28.35f;
    }
}
