package com.solo.ujianJPA2.repository;

import org.springframework.data.repository.CrudRepository;

import com.solo.ujianJPA2.entity.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Long>{

}
