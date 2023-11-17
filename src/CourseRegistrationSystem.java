
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    int studentID;
    String name;
    List<String> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void displayCourseListing() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            int availableSlots = course.capacity - getRegisteredStudents(course.courseCode).size();
            System.out.println("Course Code: " + course.courseCode);
            System.out.println("Title: " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Capacity: " + course.capacity);
            System.out.println("Schedule: " + course.schedule);
            System.out.println("Available Slots: " + availableSlots);
            System.out.println("--------------------");
        }
    }

    public void displayStudentCourses(int studentID) {
        Student student = getStudent(studentID);
        if (student != null) {
            System.out.println("Registered Courses for Student " + studentID + " - " + student.name + ":");
            for (String courseCode : student.registeredCourses) {
                Course course = getCourse(courseCode);
                if (course != null) {
                    System.out.println("Course Code: " + course.courseCode);
                    System.out.println("Title: " + course.title);
                    System.out.println("Description: " + course.description);
                    System.out.println("Schedule: " + course.schedule);
                    System.out.println("--------------------");
                }
            }
        } else {
            System.out.println("Student with ID " + studentID + " not found.");
        }
    }

    public void registerStudentForCourse(int studentID, String courseCode) {
        Student student = getStudent(studentID);
        Course course = getCourse(courseCode);

        if (student != null && course != null) {
            List<Student> registeredStudents = getRegisteredStudents(courseCode);
            if (registeredStudents.size() < course.capacity) {
                student.registeredCourses.add(courseCode);
                System.out.println("Student " + studentID + " successfully registered for course " + courseCode + ".");
            } else {
                System.out.println("Course " + courseCode + " is already full. Cannot register.");
            }
        } else {
            System.out.println("Student or course not found.");
        }
    }

    public void dropStudentFromCourse(int studentID, String courseCode) {
        Student student = getStudent(studentID);
        Course course = getCourse(courseCode);

        if (student != null && course != null) {
            if (student.registeredCourses.contains(courseCode)) {
                student.registeredCourses.remove(courseCode);
                System.out.println("Student " + studentID + " successfully dropped from course " + courseCode + ".");
            } else {
                System.out.println("Student " + studentID + " is not registered for course " + courseCode + ".");
            }
        } else {
            System.out.println("Student or course not found.");
        }
    }

    private Student getStudent(int studentID) {
        for (Student student : students) {
            if (student.studentID == studentID) {
                return student;
            }
        }
        return null;
    }

    private Course getCourse(String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private List<Student> getRegisteredStudents(String courseCode) {
        List<Student> registeredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.registeredCourses.contains(courseCode)) {
                registeredStudents.add(student);
            }
        }
        return registeredStudents;
    }

    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();

        // Adding courses to the system
        registrationSystem.addCourse(new Course("CSE101", "Introduction to Computer Science", "Basic concepts of programming", 30, "Mon/Wed 9:00 AM"));
        registrationSystem.addCourse(new Course("MAT201", "Calculus I", "Fundamental principles of calculus", 25, "Tue/Thu 10:30 AM"));

        // Display available courses
        registrationSystem.displayCourseListing();

        // Register students for courses
        registrationSystem.registerStudentForCourse(1001, "CSE101");
        registrationSystem.registerStudentForCourse(1002, "CSE101");
        registrationSystem.registerStudentForCourse(1003, "MAT201");

        // Display student courses
        registrationSystem.displayStudentCourses(1001);

        // Drop a student from a course
        registrationSystem.dropStudentFromCourse(1001, "CSE101");

        // Display updated student courses
        registrationSystem.displayStudentCourses(1001);
    }
}
