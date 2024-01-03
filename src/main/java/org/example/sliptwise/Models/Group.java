package org.example.sliptwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity(name = "UserGroups")
public class Group extends BaseModel {
    private String description;

    @ManyToMany
    private List<User> members;

    @ManyToOne
    private User createdBy;
    private String name;
}