package com.hitss.AcademyPlatform.mappers;

import com.hitss.AcademyPlatform.dto.role.RoleDTO;
import com.hitss.AcademyPlatform.entities.Role;

public class RoleMapper {

    public static RoleDTO toDTO(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
}