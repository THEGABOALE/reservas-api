package ni.edu.uam.reservas_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del solicitante es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombreSolicitante;

    @NotBlank(message = "El correo del solicitante es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    @Column(nullable = false, length = 120)
    private String correoSolicitante;

    @NotNull(message = "La fecha de reserva es obligatoria")
    @Column(nullable = false)
    private LocalDate fecha;

    @NotNull(message = "La hora de inicio es obligatoria")
    @Column(nullable = false)
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    @Column(nullable = false)
    private LocalTime horaFin;

    @NotBlank(message = "El motivo de la reserva es obligatorio")
    @Column(nullable = false, length = 200)
    private String motivo;

    @NotNull(message = "Debe asignarse una sala de estudio")
    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private SalaEstudio salaEstudio;
}