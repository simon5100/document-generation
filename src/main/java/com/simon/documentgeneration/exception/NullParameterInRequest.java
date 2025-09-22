package com.simon.documentgeneration.exception;

public class NullParameterInRequest extends RuntimeException {
    public NullParameterInRequest() {
        super("Один или несколько параметров запроса (имя судь, судьи организатора, согласующего, райиона, номер участка, отметка о замещении судьи) отсутвует");
    }
}
