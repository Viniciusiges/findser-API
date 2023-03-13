package br.com.findserv.api.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository <Cliente, UUID> {
    Page<Cliente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select c.ativo
            from Cliente c
            where
            c.id = :id
            """)
    Boolean findAtivoById(UUID id);
}
