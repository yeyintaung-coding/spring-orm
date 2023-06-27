package com.jdc.location.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.location.entity.State;

public interface StateRepo extends JpaRepository<State, Integer>{

}
