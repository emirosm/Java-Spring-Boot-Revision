package com.example.nobsv2.catfact;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatFactDTO {
    private String catFact;

    public CatFactDTO(String catFact) {
        this.catFact = catFact;
    }

    public String getCatFact() {
        return catFact;
    }
}
