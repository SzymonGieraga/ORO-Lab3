package com.example.symposium.service;

import com.example.symposium.model.Role;
import com.example.symposium.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role save(Role role) {
        return roleRepo.save(role);
    }

    public List<Role> saveAll(List<Role> roles) {
        return roleRepo.saveAll(roles);
    }
}