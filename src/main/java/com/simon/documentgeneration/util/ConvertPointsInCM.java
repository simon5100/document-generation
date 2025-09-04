package com.simon.documentgeneration.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
@Setter
public class ConvertPointsInCM {

    public float cm(float cm) {
        return cm * 28.35f;
    }
}
