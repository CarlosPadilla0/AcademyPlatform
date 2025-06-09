package com.hitss.AcademyPlatform.mappers;

import com.hitss.AcademyPlatform.dto.usuario.UsuarioResponseDTO;
import com.hitss.AcademyPlatform.entities.Usuario;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        
        dto.setRole(RoleMapper.toDTO(usuario.getRole())); 
        
        return dto;
    }
    

    public static List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}