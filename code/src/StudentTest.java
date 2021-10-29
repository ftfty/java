public class StudentTest {
    public static void main(String[] args){
      Student[] students = new Student[20];
      for(int i = 0; i < students.length; i++){
          students[i] = new Student();
          students[i].number = i + 1;
          students[i].state = (int)(Math.random() * (6 - 1 + 1) + 1);
          students[i].score = (int)(Math.random()*(100 - 0) + 1);
      }
      for(int i = 0; i < students.length; i++){
          System.out.println(students[i].toString());
      }
      System.out.println("********************");
        for(int i = 0; i < students.length; i++){
            if(students[i].state == 3){
                System.out.println(students[i].toString());
            }
        }
        //冒泡排序成绩
        for(int i = 0; i < students.length; i++){
            for(int j = 0; j < students.length - 1 - i; j++){
                if(students[j].score > students[j + 1].score){
                    Student student = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = student;
                }
            }
        }
    }
}
class Student{
    int number;
    int state;
    int score;

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                ", state=" + state +
                ", score=" + score +
                '}';
    }
}
