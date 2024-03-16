package by.hembar.beans;

import by.hembar.models.enums.Role;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserRoles {
    private final Map<String, Role> roles = new HashMap<>();
    public void put(String user, Role role){
        if(roles.containsKey(user)){
            roles.remove(user);
        }else
            roles.put(user, role);
    }
    public Role getRole(String user){
        return roles.get(user);
    }
    private String getAddresUser(){
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        return ip==null?null:ip.getHostAddress();
    }
    public boolean isDoctor(String userAddress){
        return roles.get(userAddress)==Role.DOCTOR;
    }
    public boolean isPatient(String userAddress){
        return roles.get(userAddress)==Role.PATIENT;
    }
}
