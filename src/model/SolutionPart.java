package model;

public class SolutionPart {
    private String sText;
    private boolean sBool;

    public String getSText() {
        return sText;
    }

    public void setSText(String sText) {
        this.sText = sText;
    }

    public boolean isSBool() {
        return sBool;
    }

    public void setSBool(boolean sBool) {
        this.sBool = sBool;
    }

    public SolutionPart(String sText, boolean sBool) {
        setSText(sText);
        setSBool(sBool);
    }

}
