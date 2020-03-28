package cz.cvut.fel.rsp.server.Model.Enums;

public enum UserRoleEnum {
    ROLE_USER, ROLE_ADMIN, ROLE_EMPLOYEE;

    @Override
    public String toString() {
        if(this == UserRoleEnum.ROLE_USER) {
            return "ROLE_USER";
        }
        
        if(this == UserRoleEnum.ROLE_ADMIN) {
            return "ROLE_ADMIN";
        }
        
        if(this == UserRoleEnum.ROLE_EMPLOYEE) {
            return "ROLE_EMPLOYEE";
        }
        return null;
    }
    
    
}
