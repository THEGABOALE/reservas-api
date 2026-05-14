package ni.edu.uam.reservas_api.controller;

import jakarta.validation.Valid;
import ni.edu.uam.reservas_api.model.Reserva;
import ni.edu.uam.reservas_api.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.listarReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReservaPorId(@PathVariable Long id) {
        return reservaService.buscarReservaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/sala/{salaId}")
    public ResponseEntity<Reserva> crearReserva(
            @PathVariable Long salaId,
            @Valid @RequestBody Reserva reserva
    ) {
        return reservaService.guardarReserva(reserva, salaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/sala/{salaId}")
    public ResponseEntity<Reserva> actualizarReserva(
            @PathVariable Long id,
            @PathVariable Long salaId,
            @Valid @RequestBody Reserva reserva
    ) {
        return reservaService.actualizarReserva(id, reserva, salaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        boolean eliminado = reservaService.eliminarReserva(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}