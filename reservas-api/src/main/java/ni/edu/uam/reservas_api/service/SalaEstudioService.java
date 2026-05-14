package ni.edu.uam.reservas_api.service;

import ni.edu.uam.reservas_api.model.SalaEstudio;
import ni.edu.uam.reservas_api.repository.SalaEstudioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaEstudioService {

    private final SalaEstudioRepository salaEstudioRepository;

    public SalaEstudioService(SalaEstudioRepository salaEstudioRepository) {
        this.salaEstudioRepository = salaEstudioRepository;
    }

    public List<SalaEstudio> listarSalas() {
        return salaEstudioRepository.findAll();
    }

    public Optional<SalaEstudio> buscarSalaPorId(Long id) {
        return salaEstudioRepository.findById(id);
    }

    public SalaEstudio guardarSala(SalaEstudio salaEstudio) {
        return salaEstudioRepository.save(salaEstudio);
    }

    public Optional<SalaEstudio> actualizarSala(Long id, SalaEstudio salaActualizada) {
        return salaEstudioRepository.findById(id).map(salaExistente -> {
            salaExistente.setNombre(salaActualizada.getNombre());
            salaExistente.setUbicacion(salaActualizada.getUbicacion());
            salaExistente.setCapacidad(salaActualizada.getCapacidad());
            salaExistente.setDisponible(salaActualizada.getDisponible());

            return salaEstudioRepository.save(salaExistente);
        });
    }

    public boolean eliminarSala(Long id) {
        if (salaEstudioRepository.existsById(id)) {
            salaEstudioRepository.deleteById(id);
            return true;
        }

        return false;
    }
}