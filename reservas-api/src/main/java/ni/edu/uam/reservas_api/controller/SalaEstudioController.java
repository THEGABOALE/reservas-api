package ni.edu.uam.reservas_api.controller;

import jakarta.validation.Valid;
import ni.edu.uam.reservas_api.model.Reserva;
import ni.edu.uam.reservas_api.model.SalaEstudio;
import ni.edu.uam.reservas_api.service.ReservaService;
import ni.edu.uam.reservas_api.service.SalaEstudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaEstudioController {

    private final SalaEstudioService salaEstudioService;
    private final ReservaService reservaService;

    public SalaEstudioController(SalaEstudioService salaEstudioService, ReservaService reservaService) {
        this.salaEstudioService = salaEstudioService;
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<SalaEstudio> listarSalas() {
        return salaEstudioService.listarSalas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaEstudio> buscarSalaPorId(@PathVariable Long id) {
        return salaEstudioService.buscarSalaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SalaEstudio> crearSala(@Valid @RequestBody SalaEstudio salaEstudio) {
        SalaEstudio nuevaSala = salaEstudioService.guardarSala(salaEstudio);
        return ResponseEntity.ok(nuevaSala);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaEstudio> actualizarSala(
            @PathVariable Long id,
            @Valid @RequestBody SalaEstudio salaEstudio
    ) {
        return salaEstudioService.actualizarSala(id, salaEstudio)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSala(@PathVariable Long id) {
        boolean eliminado = salaEstudioService.eliminarSala(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/reservas")
    public ResponseEntity<List<Reserva>> listarReservasPorSala(@PathVariable Long id) {
        if (salaEstudioService.buscarSalaPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reservaService.listarReservasPorSala(id));
    }
}