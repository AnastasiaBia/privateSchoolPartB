
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
import util.DBUtils;

/**
 *
 * @author Natasa
 */
public class CourseDAO {

        //Μέθοδος που φέρνει όλα τα μαθήματα
    public List<Course> getAllCourses() {
        List<Course> result = new ArrayList<>();
        String sql = "SELECT * FROM COURSES";
        System.out.println("All of the courses in the school are:");
        System.out.println("Course ID\t    Title\t\tStream\t\t    Type\t\tLessons start\t   Lessons finish");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        Connection con = DBUtils.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
                     while (rs.next()) {
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6)));

        }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        return result;
    }
    
    //Μέθοδος που προσθέτει ένα νεό μάθημα
    public void insertCourse() throws SQLException {
        String sql = "insert into courses values(null,?,?,?,?,?)";
       
        Connection con = DBUtils.getConnection();
            Scanner sc = new Scanner(System.in);
            Course course=new Course();
          do {
            System.out.println("Add a new course.");
            System.out.println("Enter course's title: ");
            String title1 = sc.nextLine();

            if (title1.isEmpty()) {

                System.out.println("Not valid title.");
                System.out.println("Please enter course data again.");
                continue;
            }

            System.out.println("Enter course's stream: ");
            String stream1 = sc.nextLine();

            if (stream1.isEmpty()) {

                System.out.println("Not valid stream.");
                System.out.println("Please enter course data again.");
                continue;
            }
            System.out.println("Enter course's type: ");
            String type1 = sc.nextLine();

            if (type1.isEmpty()) {

                System.out.println("Not valid type.");
                System.out.println("Please enter course data again.");
                continue;
            }
            System.out.println("Enter course's start date: ");
            System.out.println("The pattern should be: yyyy-MM-dd");
            Scanner sc1 = new Scanner(System.in);
            String userInput = sc1.nextLine();
            
            try {
                
            
            java.sql.Date sqlDate = java.sql.Date.valueOf(userInput);
                
               course.setStartDate(sqlDate);
               
            
            } catch (Exception e) {

                System.out.println("Not valid date.");
                System.out.println("Please enter course data again.");
                continue;
            }
            System.out.println("Enter course's end date: ");
            System.out.println("The pattern should be: yyyy-MM-dd");
            Scanner sc2 = new Scanner(System.in);
            String userInput2 = sc1.nextLine();
            
            try {
                
            java.sql.Date sqlDate2 = java.sql.Date.valueOf(userInput2);
                
               course.setEndDate(sqlDate2);
                course.setTitle(title1);
                course.setStream(stream1);
                course.setType(type1);
                PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, course.getTitle());
            ps.setString(2, course.getStream());
            ps.setString(3, course.getType());
            ps.setDate(4, course.getStartDate());
            ps.setDate(5, course.getEndDate());
            ps.executeUpdate();
            System.out.println("Course successfully added.");
            System.out.println("Would you like to add another course?");
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
                System.out.println("Please enter course data again.");
                continue;
            }
            
             
            
        } while (true);
   
    }

}
