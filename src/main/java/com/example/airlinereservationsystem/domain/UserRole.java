package com.example.airlinereservationsystem.domain;

public enum UserRole {
    ROLE_ADMIN, ROLE_USER, ROLE_AGENT
import com.example.airlinereservationsystem.util.constant.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(value = EnumType.STRING)
    Roles roleName;

    @Override
    public String getAuthority() {
        return roleName.toString();
    }
}
