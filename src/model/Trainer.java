
package model;

/**
 *
 * @author bia_a
 */
public class Trainer {
    
    private int id;
    private String firstName;
    private String lastName;
    private String subject;
    private String stream;

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

 
    

    public Trainer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    
    
    public Trainer (){};
}
