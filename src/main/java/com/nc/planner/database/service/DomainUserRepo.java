/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.planner.database.service;

import com.nc.planner.database.domain.DomainUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AlexandraHP
 */
@Repository
public interface DomainUserRepo extends CrudRepository<DomainUser, Long>{
    DomainUser findByName(String name);
}