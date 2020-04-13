
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Student;
import util.DBUtils;

/**
 *
 * @author Natasa
 */
public class StudentDAO {

    //Μέθοδος που φέρνει όλους τους μαθητές
    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        String sql = "SELECT * FROM STUDENTS";
        System.out.println("All of the students in the school are:");
        System.out.println("ID\t\tFirst Name\t\tLast Name\t   Date of Birth");
        System.out.println("------------------------------------------------------------------------");
        Connection con = DBUtils.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
            System.out.println(String.format("%-20s%-20s%-20s%-20s", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));

        }
             
      
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return result;
    }

    //Μέθοδος που φέρνει όλους τους μαθητές ανά μάθημα
    public List<Student> getAllStudentsPerCourse() {
        List<Student> result = new ArrayList<>();
        String sql = "SELECT * FROM STUDENTS_PER_COURSE_VIEW";
        System.out.println("Students per course:");
        System.out.println("Student ID\t   First Name\t\tLast Name\t   Course\t\t Stream \t Course ID");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        Connection con = DBUtils.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
                     while (rs.next()) {
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));

        }
            
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return result;
    }

    //Μέθοδος που φέρνει τους μαθητές που παρακολουθούν περισσότερα από ένα 
    //μαθήματα
    public List<Student> getStudentsInManyCourses() {
        List<Student> result = new ArrayList<>();
        String sql = "SELECT * FROM STUDENTS_IN_MANY_COURSES";
        System.out.println("Students in more than one course are:");
        System.out.println("Student ID\t  First Name\t       Last Name\t   Course\t\t Stream");
        System.out.println("------------------------------------------------------------------------------------------");
        Connection con = DBUtils.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

        }
            
            
       
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        return result;
    }

    //Μέθοδος που προσθέτει ένα νέο μαθητή
    public void insertStudent() throws SQLException {
        String sql = "insert into students values(null,?,?,?)";

        Connection con = DBUtils.getConnection();
        Scanner sc = new Scanner(System.in);
        Student student = new Student();
        do {
            System.out.println("Add a new student.");
            System.out.println("Enter student's name: ");
            String name = sc.nextLine();

            if (name.isEmpty() || !name.matches("[a-zA-Z_]+")) {

                System.out.println("Not valid name.");
                System.out.println("Please enter student data again.");
                continue;
            }

            System.out.println("Enter student's surname: ");
            String surname = sc.nextLine();

            if (surname.isEmpty() || !surname.matches("[a-zA-Z_]+")) {

                System.out.println("Not valid surname.");
                System.out.println("Please enter student data again.");
                continue;
            }
            System.out.println("Enter student's birth date: ");
            System.out.println("The pattern should be: yyyy-MM-dd");
            Scanner sc1 = new Scanner(System.in);
            String userInput = sc1.nextLine();

            try {
                java.sql.Date sqlDate = java.sql.Date.valueOf(userInput);
                student.setDateOfBirth(sqlDate);
                student.setFirstName(name);
                student.setLastName(surname);
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, student.getFirstName());
                ps.setString(2, student.getLastName());
                ps.setDate(3, student.getDateOfBirth());
                ps.executeUpdate();
                System.out.println("Student successfully added.");
                System.out.println("Would you like to add another student?");
                System.out.println("Press 'y' for yes or any other key to exit to main menu.");
                Scanner sc3 = new Scanner(System.in);
                String addtr = sc3.nextLine().toLowerCase();
                if (addtr.toLowerCase().equals("y")) {
                    continue;

                } else {
                    break;
                }

            } catch (Exception e) {

                System.out.println("Not valid date.");
                System.out.println("Please enter student data again.");
                continue;
            } finally {
                try {
                    con.close();
                } catch (Exception e) {
                    /* ignored */ }
            }
        } while (true);
    }

    //Μέθοδος που προσθέτει ένα μαθητή σε ένα μάθημα
    public void insertStudentToCourse() throws SQLException {
        String sql = "insert into students_per_course values(?,?,?)";

        Connection con = DBUtils.getConnection();
        try {
            Scanner sc = new Scanner(System.in);
            Student student = new Student();
            Course course = new Course();

            do {
                System.out.println("Add a student to a course.");
                StudentDAO s1 = new StudentDAO();
                s1.getAllStudents();

                int id = 0;
                boolean isValid = false;
                while (isValid == false) {
                    System.out.println("Enter student's id: ");
                    sc.nextLine();
                    if (sc.hasNextInt()) {
                        id = sc.nextInt();
                        isValid = true;
                        student.setId(id);
                    } else {
                        System.out.println("Not valid student id.");
                        System.out.println("Please enter data again.");
                        continue;
                    }

                }
                CourseDAO c1 = new CourseDAO();
                c1.getAllCourses();
                int courseId = 0;

                    System.out.println("Enter course's id: ");
                    sc.nextLine();
                    if (sc.hasNextInt()) {
                        courseId = sc.nextInt();
                        isValid = true;
                        course.setId(courseId);
                    } else {
                        System.out.println("Not valid course id.");
                        System.out.println("Please enter data again.");
                        continue;
                    }


                double fees = 0;

                    System.out.println("Enter student's tuition fees: ");
                    sc.nextLine();
                    if (sc.hasNextDouble()) {
                        fees = sc.nextDouble();
                        isValid = true;
                        student.setTuitionFees(fees);
                    } else {
                        System.out.println("Not valid number.");
                        System.out.println("Please enter data again.");
                        continue;
                    }


                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, student.getId());
                ps.setInt(2, course.getId());
                ps.setDouble(3, student.getTuitionFees());
                ps.executeUpdate();
                System.out.println("Student successfully added to course.");
                System.out.println("Would you like to add another student  to a course?");
                System.out.println("Press 'y' for yes or any other key to exit to main menu.");
                Scanner sc3 = new Scanner(System.in);
                String addtr = sc3.nextLine().toLowerCase();
                if (addtr.toLowerCase().equals("y")) {
                    continue;

                } else {
                    break;
                }
            } while (true);
        } catch (Exception e) {System.out.println("This student is already following this course!");
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                }

        }
    }
}
