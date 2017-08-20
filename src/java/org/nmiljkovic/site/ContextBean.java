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
public class ContextBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String mUsername;
    private User mUser;
    private boolean mIsLoggedIn;
    
    public ContextBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        mUser = (User)context.getExternalContext().getSessionMap().get("user");
        
        if (mUser != null) {
            setUsername(mUser.getUsername());
            mIsLoggedIn = true;
        } else {
            setUsername(null);
            mIsLoggedIn = false;
        }
    }
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        
        mUser = null;
        setUsername(null);
        mIsLoggedIn = false;
        return "index?faces-redirect=true";
    }
    
    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }
    
    public boolean getIsLoggedIn() {
        return mIsLoggedIn;
    }
}
