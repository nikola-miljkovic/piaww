package org.nmiljkovic.models;
// Generated Aug 23, 2017 10:48:40 PM by Hibernate Tools 4.3.1



/**
 * EmployedAt generated by hbm2java
 */
public class EmployedAt  implements java.io.Serializable {


     private EmployedAtId id;
     private Airways airways;
     private User user;

    public EmployedAt() {
    }

	
    public EmployedAt(EmployedAtId id) {
        this.id = id;
    }
    public EmployedAt(EmployedAtId id, Airways airways, User user) {
       this.id = id;
       this.airways = airways;
       this.user = user;
    }
   
    public EmployedAtId getId() {
        return this.id;
    }
    
    public void setId(EmployedAtId id) {
        this.id = id;
    }
    public Airways getAirways() {
        return this.airways;
    }
    
    public void setAirways(Airways airways) {
        this.airways = airways;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }




}

