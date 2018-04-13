package com.nguyenthanhchung.carop2p.model;

/**
 * Created by Nguyen Thanh Chung on 2018-04-13.
 */

public class CellBoard {
    private int idImage;
    private boolean isFilled;

    public CellBoard(){

    }

    public CellBoard(int id, boolean isFilled){
        this.idImage = id;
        this.isFilled = isFilled;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getIdImage() {

        return idImage;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }
}
