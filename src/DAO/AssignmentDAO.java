
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
import model.Assignment;
import model.Course;
import model.Student;
import util.DBUtils;

/**
 *
 * @author Natasa
 */
public class AssignmentDAO {

    //Μέθοδος που φέρνει όλα τα assignments
    public List<Assignment> getAllAssignments() {
        List<Assignment> result = new ArrayList<>();
        String sql = "SELECT * FROM ALL_ASSIGNMENTS";
        System.out.println("All of the assignments in the school are:");
        System.out.println("ID\t  Title\t\t      Description\t\t\t\t\tSubmission Date\t    Course\t   Type");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        Connection con = DBUtils.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(String.format("%-10s%-20s%-50s%-20s%-15s%-20s", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(6), rs.getString(7)));

            }

        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        return result;
    }

    //Μέθοδος που φέρνει τα assignments ανά μάθημα
    public List<Assignment> getAllAssignmentsPerCourse() {
        List<Assignment> result = new ArrayList<>();
        String sql = "SELECT * FROM ASSIGNMENTS_PER_COURSE_VIEW";
        System.out.println("All of the assignments per course are:");
        System.out.println("Course\t\t    Type\t    Asignm.ID\t  Title\t\t      Description\t\t\t\t      Submission Date");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        Connection con = DBUtils.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(String.format("%-20s%-20s%-10s%-20s%-50s%-20s", rs.getString(6), rs.getString(7), rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        return result;
    }

    //Μέθοδος που φέρνει τα assignments ανά μαθητή και μάθημα
    public List<Assignment> getAllAssignmentsPerCoursePerStudent() {
        List<Assignment> result = new ArrayList<>();
        String sql = "SELECT * FROM ASSIGNMENTS_PER_COURSE_PER_STUDENT_VIEW";
        System.out.println("All of the assignments per student per course are:");
        System.out.println("Last Name\t   First Name\t       Course\t       Type\t\tAsignm.ID    Title\t\t Description\t\t\t\t\t Submission Date");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        Connection con = DBUtils.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(String.format("%-20s%-20s%-15s%-20s%-10s%-20s%-50s%-20s", rs.getString(9), rs.getString(8), rs.getString(6), rs.getString(7), rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));

            }

            while (rs.next()) {
                Assignment temp = new Assignment();
                temp.setId(rs.getInt(1));
                temp.setTitle(rs.getString(2));
                temp.setDescription(rs.getString(3));
                temp.setSubDateTime(rs.getDate(4));
                temp.setCourse_id(rs.getInt(5));
                temp.setStream(rs.getString(6));
                temp.setType(rs.getString(7));
                temp.setStudentName(rs.getString(8));
                temp.setStudentLastName(rs.getString(9));
                result.add(temp);

                System.out.println(temp.getStudentName() + " " + temp.getStudentLastName() + "\t" + temp.getTitle() + "\t" + temp.getSubDateTime() + "\t" + temp.getStream() + "\t\t" + temp.getType() + "\t" + temp.getDescription());
                System.out.println("----------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        return result;
    }

    //Μέθοδος που προσθέτει ένα νέο assignment
    public void insertAssignment() throws SQLException {
        String sql = "insert into assignments values(null,?,?,?,?)";

        Connection con = DBUtils.getConnection();
        Scanner sc = new Scanner(System.in);
        Assignment assignment = new Assignment();
        Course course = new Course();
        do {
            System.out.println("Add a new assignment.");
            System.out.println("Enter assignment's title: ");
            String title1 = sc.nextLine();

            if (title1.isEmpty()) {

                System.out.println("Not valid title.");
                System.out.println("Please enter assignment data again.");
                continue;
            }

            System.out.println("Enter assignment's description: ");
            String desc1 = sc.nextLine();

            if (desc1.isEmpty() || desc1.matches("^[0-9]*$")) {
                System.out.println("Not valid description.");
                System.out.println("Please enter assignment data again.");
                continue;
            }

            CourseDAO c1 = new CourseDAO();
            c1.getAllCourses();
            int courseId = 0;
            boolean isValid = false;
            while (isValid == false) {
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
            }
            System.out.println("Enter assignment's submission date: ");
            System.out.println("The pattern should be: yyyy-MM-dd");
            Scanner sc1 = new Scanner(System.in);
            String userInput = sc1.nextLine();

            try {

                java.sql.Date sqlDate = java.sql.Date.valueOf(userInput);

                assignment.setSubDateTime(sqlDate);
                assignment.setTitle(title1);
                assignment.setDescription(desc1);
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, assignment.getTitle());
                ps.setString(2, assignment.getDescription());
                ps.setDate(3, assignment.getSubDateTime());
                ps.setInt(4, course.getId());
                ps.executeUpdate();
                System.out.println("Assignment successfully added.");
                System.out.println("Would you like to add another assignment?");
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
                System.out.println("Please enter assignment data again.");
                continue;
            }

        } while (true);

    }
    
    //Όταν κάνω εισαγωγή νέου μαθήματος σε έναν μαθητή 
    //στη μέθοδο insertStudentPerCoursePerAssignment(), αυτόματα 
    //προστίθεται και στον πίνακα students_per_course 
    //ώστε να φαίνεται ότι παρακολουθεί και αυτό το μάθημα
    
    public void trigger() {
    String sql = "delimiter # create trigger insert_student_to_course after insert on assignments_per_student for each row begin insert into students_per_course (student_id, course_id) values (new.student_id, new.course_id); end# delimiter ;";
     Connection con = DBUtils.getConnection();
    }

    // Μέθοδος για να εισάγει assignment σε κάποιον μαθητή.
    //Αν το course id που θα εισάγει ο χρήστης δεν είναι αυτό που ήδη ακολουθεί 
    // ο μαθητής, θα προστεθεί και σε εκείνο το μάθημα.
    public void insertStudentPerCoursePerAssignment() throws SQLException {
        String sql = "insert into assignments_per_student values(?,?,?,null,null)";

        Connection con = DBUtils.getConnection();

        try {
            Scanner sc = new Scanner(System.in);
            Assignment assignment = new Assignment();
            Course course = new Course();
            Student student = new Student();

 do {
     System.out.println("Add an assignment to a student.");
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
                s1.getAllStudentsPerCourse();
                int courseId = 0;
 
                System.out.println("Enter student's course id, or add him to another course by typing another course id:");
                sc.nextLine();
                if (sc.hasNextInt()) {
                    courseId = sc.nextInt();
                   
                    course.setId(courseId);
                } else {
                    System.out.println("Not valid course id.");
                    System.out.println("Please enter data again.");
                    continue;
                }

               AssignmentDAO a1 = new AssignmentDAO();
               a1.getAllAssignmentsPerCourse();
                int aid = 0;

                System.out.println("Enter assignment's id: ");
                sc.nextLine();
                if (sc.hasNextInt()) {
                    aid = sc.nextInt();
                 
                    assignment.setId(aid);
                } else {
                    System.out.println("Not valid number.");
                    System.out.println("Please enter data again.");
                    continue;
                }


                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, student.getId());
                ps.setInt(2, course.getId());
                ps.setInt(3, assignment.getId());
                ps.executeUpdate();
                a1.trigger();
                System.out.println("Student successfully added to course and assignment.");
                System.out.println("Would you like to add another student  to a course and assignment?");
                System.out.println("Press 'y' for yes or any other key to exit to main menu.");
                Scanner sc3 = new Scanner(System.in);
                String addtr = sc3.nextLine().toLowerCase();
                if (addtr.toLowerCase().equals("y")) {
                    continue;

                } else {
                    break;
                }
            } while (true);
        } catch (Exception e) { System.out.println("The data you have typed already exist!");
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }

        }
    }

}
