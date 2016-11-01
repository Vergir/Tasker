/**
 *
 * @author AlexandraHP
 */
package com.nc.planner.database.domain;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "DomainUser")
public class DomainUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    
    @Column(name = "password", unique = false, nullable = false)
    private String password;
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
