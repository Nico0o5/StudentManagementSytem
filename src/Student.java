class Student{

    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private char gender;
    private float grade;
    private String section;
    private String email;
    private String website;

    Student(long id, String firstName, String lastName, int age, char gender, float grade, String section, String email, String website){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
        this.section = section;
        this.email = email;
        this.website = website;
    }

    @Override
    public String toString(){
        return "[ Firstname: " + firstName + ", Lastname: " + lastName + ", Age: " + age + ", Gender: " + gender + ", Float: " + grade + ", Section: " + section + ", Email: " + email + ", Website: " + website + " ]"; 
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public long getId() {
        return id;
    }

    public float getGrade(){
        return this.grade;
    }

    public String getSection() {
        return section;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


}