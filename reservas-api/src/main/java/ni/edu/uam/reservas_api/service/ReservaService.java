package ni.edu.uam.reservas_api.service;

import ni.edu.uam.reservas_api.model.Reserva;
import ni.edu.uam.reservas_api.model.SalaEstudio;
import ni.edu.uam.reservas_api.repository.ReservaRepository;
import ni.edu.uam.reservas_api.repository.SalaEstudioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaEstudioRepository salaEstudioRepository;

    public ReservaService(ReservaRepository reservaRepository, SalaEstudioRepository salaEstudioRepository) {
        this.reservaRepository = reservaRepository;
        this.salaEstudioRepository = salaEstudioRepository;
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> listarReservasPorSala(Long salaId) {
        return reservaRepository.findBySalaEstudio_Id(salaId);
    }

    public Optional<Reserva> guardarReserva(Reserva reserva, Long salaId) {
        Optional<SalaEstudio> salaEncontrada = salaEstudioRepository.findById(salaId);

        if (salaEncontrada.isEmpty()) {
            return Optional.empty();
        }

        reserva.setSalaEstudio(salaEncontrada.get());
        return Optional.of(reservaRepository.save(reserva));
    }

    public Optional<Reserva> actualizarReserva(Long id, Reserva reservaActualizada, Long salaId) {
        Optional<Reserva> reservaEncontrada = reservaRepository.findById(id);
        Optional<SalaEstudio> salaEncontrada = salaEstudioRepository.findById(salaId);

        if (reservaEncontrada.isEmpty() || salaEncontrada.isEmpty()) {
            return Optional.empty();
        }

        Reserva reservaExistente = reservaEncontrada.get();

        reservaExistente.setNombreSolicitante(reservaActualizada.getNombreSolicitante());
        reservaExistente.setCorreoSolicitante(reservaActualizada.getCorreoSolicitante());
        reservaExistente.setFecha(reservaActualizada.getFecha());
        reservaExistente.setHoraInicio(reservaActualizada.getHoraInicio());
        reservaExistente.setHoraFin(reservaActualizada.getHoraFin());
        reservaExistente.setMotivo(reservaActualizada.getMotivo());
        reservaExistente.setSalaEstudio(salaEncontrada.get());

        return Optional.of(reservaRepository.save(reservaExistente));
    }

    public boolean eliminarReserva(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }

        return false;
    }
}