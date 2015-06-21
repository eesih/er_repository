/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.er.business.user.entity;

/**
 *
 * @author eerosihvonen
 */

public enum UserRoles {
    
    ARCHITECT("architect"), DEVELOPER("developer"), PROJECT_MANAGER("manager"), CONSULTANT("consultant");
    
    private String role;
    
    private UserRoles(String role)  {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    
    
}
