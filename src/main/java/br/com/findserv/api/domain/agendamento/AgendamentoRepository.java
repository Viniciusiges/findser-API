package br.com.findserv.api.domain.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
    boolean existsByClienteIdAndDataBetween(UUID idCliente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);


    boolean existsByPrestadorIdAndData(UUID idPrestador, LocalDateTime data);

}
