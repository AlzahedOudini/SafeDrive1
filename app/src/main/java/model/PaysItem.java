package model;

public class PaysItem {
    private String mNomPays;
    private int mDrapImage;

    public PaysItem(String nomPays, int drapImage) {
        mNomPays = nomPays;
        mDrapImage = drapImage;
    }

    public String getNomPays() {
        return mNomPays;
    }

    public void setNomPays(String nomPays) {
        mNomPays = nomPays;
    }

    public int getDrapImage() {
        return mDrapImage;
    }

    public void setDrapImage(int drapImage) {
        mDrapImage = drapImage;
    }
}
