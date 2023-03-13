package br.com.findserv.api.domain.prestador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PrestadorRepository extends JpaRepository<Prestador, UUID> {
    Page<Prestador> findAllByAtivoTrue(Pageable paginacao);


    @Query("""
            select p from Prestador p
            where
            p.ativo = true
            and
            p.servicoPrestado = :servicoPrestado
            and
            p.id not in(
                select a.prestador.id from Agendamento a
                where
                a.data = :data
                )
            order by random()
            limit 1
            """)
    Prestador escolherPrestadorAleatorio(ServicoPrestado servicoPrestado, LocalDateTime data);

    @Query("""
            select p.ativo
            from Prestador p
            where
            p.id = :id
            """)
    Boolean findAtivoById(UUID id);



}
















