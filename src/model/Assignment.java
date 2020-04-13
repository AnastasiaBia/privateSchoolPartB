
package model;

import java.sql.Date;

/**
 *
 * @author Natasa
 */
public class Assignment {
    
    private int id;
    private String title;
    private String description;
    private Date subDateTime;
    private int course_id;
    private String stream;
    private String type;
    private String studentName;
    private String studentLastName;

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(Date subDateTime) {
        this.subDateTime = subDateTime;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Assignment(String title, String description, Date subDateTime, int course_id) {
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.course_id = course_id;
    }

 
    public Assignment() {
       
    }
      
    
    
    
}
