
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
import model.Trainer;
import util.DBUtils;


/**
 *
 * @author bia_a
 */
public class TrainerDAO {
    
    //Μέθοδος που φέρνει όλους τους καθηγητές 
    
    public List<Trainer> getAllTrainers() {
        List<Trainer> result = new ArrayList<>();
        String sql = "SELECT * FROM TRAINERS";
        System.out.println("All of the trainers in the school are:");
        System.out.println("ID\t\t   First Name\t\tLast Name");
        System.out.println("---------------------------------------------------");
        Connection con = DBUtils.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
                  while (rs.next()) {
            System.out.println(String.format("%-20s%-20s%-20s", rs.getInt(1), rs.getString(2), rs.getString(3)));

        }
            
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        return result;
    }
    
      //Μέθοδος που φέρνει όλους τους καθηγητές ανά μάθημα
    public List<Trainer> getAllTrainersPerCourse() {
        List<Trainer> result = new ArrayList<>();
        String sql = "SELECT * FROM TRAINERS_PER_COURSE_VIEW";
        System.out.println("Trainers per course:");
        System.out.println("Trainer ID\t   First Name\t       Last Name\t   Subject\t\t Type \t\t Course ID");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
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
        }
        return result;
    }
    
    
      //Μέθοδος που εισάγει ένα νέο καθηγητή 
    public void insertTrainer() throws SQLException {
        String sql = "insert into trainers values(null,?,?)";
       
        Connection con = DBUtils.getConnection();
            Scanner sc = new Scanner(System.in);
            Trainer trainer=new Trainer();
          do {
            System.out.println("Add a new trainer.");
            System.out.println("Enter trainer's name: ");
            String name = sc.nextLine();
            

            if (name.isEmpty() || !name.matches("[a-zA-Z_]+")) {

                System.out.println("Not valid name.");
                System.out.println("Please enter trainer data again.");
                continue;
            } 

            System.out.println("Enter trainer's surname: ");
            String surname = sc.nextLine();

            if (surname.isEmpty() || !surname.matches("[a-zA-Z_]+")) {

                System.out.println("Not valid surname.");
                System.out.println("Please enter trainer data again.");
                continue;
            } else 
                trainer.setFirstName(name);
                    trainer.setLastName(surname);
                    
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, trainer.getFirstName());
            ps.setString(2, trainer.getLastName());
            ps.executeUpdate();
              System.out.println("Trainer successfully added.");
               System.out.println("Would you like to add another trainer?");
                    System.out.println("Press 'y' for yes or any other key to exit to main menu.");
                    Scanner sc3 = new Scanner(System.in);
                    String addtr = sc3.nextLine().toLowerCase();
                    if (addtr.toLowerCase().equals("y")) {
                        continue;

                    } else {
                        break;
                    }
         
        
          }while (true);
   
    }
    
    //Μέθοδος που εισάγει ένα καθηγητή σε μάθημα
       public void insertTrainerToCourse() throws SQLException {
        String sql = "insert into trainers_per_course values(?,?)";

        Connection con = DBUtils.getConnection();
        try {
            Scanner sc = new Scanner(System.in);
            Trainer trainer = new Trainer();
            Course course = new Course();

            do {
                System.out.println("Add a trainer to a course.");
                TrainerDAO t1 = new TrainerDAO();
                t1.getAllTrainers();

                int id = 0;
                boolean isValid = false;
                while (isValid == false) {
                    System.out.println("Enter trainer's id: ");
                    sc.nextLine();
                    if (sc.hasNextInt()) {
                        id = sc.nextInt();
                        isValid = true;
                        trainer.setId(id);
                    } else {
                        System.out.println("Not valid trainer id.");
                        System.out.println("Please enter data again.");
                        continue;
                    }

                }
                CourseDAO c1 = new CourseDAO();
                c1.getAllCourses();

                    System.out.println("Enter course's id: ");
                    sc.nextLine();
                    if (sc.hasNextInt()) {
                       int courseId = sc.nextInt();
                        isValid = true;
                        course.setId(courseId);
                    } else {
                        System.out.println("Not valid course id.");
                        System.out.println("Please enter data again.");
                        continue;
                    }
         
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, trainer.getId());
                ps.setInt(2, course.getId()); 
                ps.executeUpdate();
                System.out.println("Trainer successfully added to course.");
                System.out.println("Would you like to add another trainer to a course?");
                System.out.println("Press 'y' for yes or any other key to exit to main menu.");
                Scanner sc3 = new Scanner(System.in);
                String addtr = sc3.nextLine().toLowerCase();
                if (addtr.toLowerCase().equals("y")) {
                    continue;

                } else {
                    break;
                }
            } while (true);
        } catch (Exception e) { System.out.println("This trainer is already teaching this course!");
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                /* ignored */ }

        }
    } 
       
    }
    
   
        
    
    
   
    

