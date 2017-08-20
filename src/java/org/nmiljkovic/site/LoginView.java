/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.site;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.nmiljkovic.dao.UserRepository;
import org.nmiljkovic.models.User;

@ManagedBean
@RequestScoped
public class LoginView implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String mUsername;
    private String mPassword;
    
    public String submit() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserRepository userRepo = new UserRepository();
        User user = userRepo.checkUser(mUsername, mPassword);
        
        if (user == null) {
            context.addMessage(null, new FacesMessage("Bad username/password."));
            mUsername = null;
            mPassword = null;
            return null;
        } else {
            context.getExternalContext().getSessionMap().put("user", user);
            return "index?faces-redirect=true";
        }
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
    
    
}
