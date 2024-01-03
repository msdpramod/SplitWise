package org.example.sliptwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Entity
public class User extends BaseModel{
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    @Enumerated( EnumType.ORDINAL)
    private UserStatus userStatus;
    @ManyToMany(mappedBy = "members")
    private List<Group> groups;

}
