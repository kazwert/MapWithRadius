package com.halal.triz.trizna.model;

import java.util.ArrayList;

/**
 * Created by User on 03-Jun-17.
 */

public class MapResponse {
    private ArrayList<Maps> result;

    public MapResponse(ArrayList<Maps> result) {
        this.result = result;
    }

    public ArrayList<Maps> getResult() {
        return result;
    }

    public void setResult(ArrayList<Maps> result) {
        this.result = result;
    }
}
