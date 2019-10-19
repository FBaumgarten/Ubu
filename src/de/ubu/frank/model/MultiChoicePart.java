package de.ubu.frank.model;

public class MultiChoicePart {
    private String mcText;
    private boolean mcValue;
    private boolean mcInput;

    public String getMcText() {
        return mcText;
    }

    public void setMcText(String mcText) {
        this.mcText = mcText;
    }

    public boolean isMcValue() {
        return mcValue;
    }

    public void setMcValue(boolean mcValue) {
        this.mcValue = mcValue;
    }

    public boolean isMcInput() {
        return mcInput;
    }

    public void setMcInput(boolean mcInput) {
        this.mcInput = mcInput;
    }

    public MultiChoicePart(String mcText, boolean mcValue) {
        setMcText(mcText);
        setMcValue(mcValue);
        setMcInput(false);
    }

    public MultiChoicePart(String csv) {
        String split[] = csv.split(";");
        setMcText(split[0]);
        setMcValue(Boolean.parseBoolean(split[1]));
        setMcInput(false);
    }

    public String toCSV(){
        return mcText + ";" + mcValue;
    }
}
