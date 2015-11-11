package com.aturhelp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class Person {
 
	 @XmlElement(name ="firstName")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @XmlElement(name ="lastName")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @XmlElement(name ="email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
     
    @XmlElement(name ="id")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
     
    public Person() {
         
        id = -1;
        firstName = "";
        lastName = "";
        email = "";
         
    }
 
    public Person(long id, String firstName, String lastName, String email) {
 
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
 
     
    private long id;
   
    private String firstName;
   
    private String lastName;
    
    private String email;
         
}