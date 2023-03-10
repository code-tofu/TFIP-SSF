package ssf.revision.revdayone.model;

public class User {
    private String name;
    private String gender;
    private String note;
    private boolean married;
    private String profession;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public boolean isMarried() {
        return married;
    }
    public void setMarried(boolean married) {
        this.married = married;
    }
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    @Override
    public String toString() {
        return "User [name=" + name + ", gender=" + gender + ", note=" + note + ", married=" + married + ", profession="
                + profession + "]";
    }
    
    // 
    
}
