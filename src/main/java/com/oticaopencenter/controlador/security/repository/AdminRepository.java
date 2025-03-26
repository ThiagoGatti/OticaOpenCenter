package com.oticaopencenter.controlador.security.repository;

import com.oticaopencenter.controlador.security.domain.AdminModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminModel, Long> {
    Optional<AdminModel> findByEmail(String email);
    Page<AdminModel> findByNomeContainingOrEmailContaining(String nome, String email, Pageable pageable);
}