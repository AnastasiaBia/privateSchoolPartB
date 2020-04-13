package util;

import DAO.AssignmentDAO;
import DAO.CourseDAO;
import DAO.StudentDAO;
import DAO.TrainerDAO;
import java.sql.SQLException;
import java.util.Scanner;


/**
 *
 * @author Natasa
 */
public class MenuMethods {

    AssignmentDAO a1 = new AssignmentDAO();
    CourseDAO c1 = new CourseDAO();
    StudentDAO s1 = new StudentDAO();
    TrainerDAO t1 = new TrainerDAO();
    
    public void startMenu() throws SQLException {
        //Αρχικό μενού

        Scanner sc = new Scanner(System.in);
       
        String menuChoice;
        System.out.println("Welcome to Private School Management.");

        do {

            System.out.println("Press 1 to view all of the students in the school.");
            System.out.println("Press 2 to view all of the trainers in the school.");
            System.out.println("Press 3 to view all of the assignments in the school.");
            System.out.println("Press 4 to view all of the courses in the school.");
            System.out.println("Press 5 to view the students per course.");
            System.out.println("Press 6 to view the trainers per course.");
            System.out.println("Press 7 to view the assignments per course.");
            System.out.println("Press 8 to view the assignments per course per student.");
            System.out.println("Press 9 to view the students following more than one courses.");
            System.out.println("Press 10 to add a new student.");
            System.out.println("Press 11 to add a new trainer.");
            System.out.println("Press 12 to add a new assignment.");
            System.out.println("Press 13 to add a new course.");
            System.out.println("Press 14 to add a student to a course.");
            System.out.println("Press 15 to add a trainer to a course.");
            System.out.println("Press 16 to add an assignment to a student.");
            

            System.out.println("Press 0 to quit.");

            menuChoice = sc.nextLine();

            if (menuChoice.isEmpty()) {

                System.out.println("Please try again.");
                continue;
            }

            switch (menuChoice) {

                case "1":

                    s1.getAllStudents();
                    System.out.println();
                    break;

                case "2":

                    t1.getAllTrainers();
                    System.out.println();
                    break;

                case "3":
                   a1.getAllAssignments();
                    System.out.println();
                   break;
                case "4":

                    c1.getAllCourses();
                    System.out.println();
                    break;
                case "5":

                    s1.getAllStudentsPerCourse();
                    System.out.println();
                    break;
                case "6":

                    t1.getAllTrainersPerCourse();
                    System.out.println();
                    break;

                case "7":
                    a1.getAllAssignmentsPerCourse();
                    break;

                case "8":

                    a1.getAllAssignmentsPerCoursePerStudent();
                    System.out.println();
                    break;

                case "9":

                    s1.getStudentsInManyCourses();
                    System.out.println();
                    break;
                case "10":

                    s1.insertStudent();
                    System.out.println();
                    break;

                case "11":
                     t1.insertTrainer();
                    System.out.println();
                    break;

                case "12":

                    a1.insertAssignment();
                    System.out.println();
                    break;

                case "13":

                    c1.insertCourse();
                    System.out.println();
                    break;
                case "14":

                    s1.insertStudentToCourse();
                    System.out.println();
                    break;
                case "15":

                    t1.insertTrainerToCourse();
                    System.out.println();
                    break;
                case "16":

                    a1.insertStudentPerCoursePerAssignment();
                    System.out.println();
                    break;

            }
        } while (!menuChoice.equals("0"));

    }

}
