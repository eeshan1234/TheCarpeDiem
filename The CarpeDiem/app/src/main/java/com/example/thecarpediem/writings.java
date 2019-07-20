package com.example.thecarpediem;

import java.util.List;

public class writings {

    private List<String> Writings;

    public writings() {
    }

    public writings(List<String> Writings) {
        this.Writings = Writings;
    }

    public List<String> getWriting() {
        return Writings;
    }

    public void setWriting(List<String> writ) {
        Writings = writ;
    }
}
