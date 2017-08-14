package org.nmiljkovic.site;

import org.nmiljkovic.models.Airways;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.nmiljkovic.dao.AirwaysRepository;
import org.nmiljkovic.dao.UserRepository;
import org.nmiljkovic.models.User;

@ManagedBean
@RequestScoped
public class RegisterView {

    private static AirwaysRepository airwaysRepo;
    private static UserRepository userRepository;
    
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mUsername;
    private String mPassword;
    private String mSex;
    private Date mDateOfBirth;
    private Airways mAirways;
    private String mAirwaysId;
    private List<Airways> mAirwaysList;
    
    private String mMessage;
    
    static {
        airwaysRepo = new AirwaysRepository();
        userRepository = new UserRepository();
    }
    
    public RegisterView() {
        mAirwaysList = airwaysRepo.getAirways();
    }
    
    public String submit() {
        for (Airways airways : mAirwaysList) {
            if (airways.getId() == Integer.parseInt(mAirwaysId)) {
                setAirways(airways);
                break;
            }
        }
        
        User user = null;
        try {
            user = userRepository.createUser(new User(mAirways, 0, mFirstName, mLastName, mDateOfBirth, mEmail, mUsername, mPassword));
        } catch (Exception exc) {
            
        }
        // success
        if (user != null && user.getId() != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registration completed."));
            
            return "/faces/index.xhtml?faces-redirect=true";
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error creating user."));
        return null;
    }
    
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String sex) {
        this.mSex = sex;
    }

    public Date getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.mDateOfBirth = dateOfBirth;
    }       

    public List<Airways> getAirwaysList() {
        return mAirwaysList;
    }

    public String getAirwaysId() {
        return mAirwaysId;
    }

    public void setAirwaysId(String airwaysId) {
        this.mAirwaysId = airwaysId;
    }

    public Airways getAirways() {
        return mAirways;
    }

    public void setAirways(Airways airways) {
        this.mAirways = airways;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }
}