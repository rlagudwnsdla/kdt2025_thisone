package one;

public class Student {
    private String studentNum;

    public Student(String studentNum) {
        this.studentNum = studentNum;
    }
    public String getStudentNum() {
        return studentNum;
    }

    @Override
    public boolean equals(Object obb) {
        if(obb instanceof Student) {
            Student cc = (Student) obb;
            if(this.studentNum.equals(cc.studentNum)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return studentNum.hashCode();
    }
}
