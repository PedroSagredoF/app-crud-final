package com.medicalhourmanagement.medicalhourmanagement.appointment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.medicalhourmanagement.medicalhourmanagement.doctor.Doctor;
import com.medicalhourmanagement.medicalhourmanagement.patient.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Esta entidad representa la cita de un paciente con un médico
 * La cantidad de atributos es la menor posible para no sobreextender el código, ya que el principal
 * objetivo de este proyecto es mostrar un buen manejo en la estructura de la aplicación y las buenas prácticas.
 */
@Entity
@Table(name="APPOINTMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Se hacen validaciones a los atributos en su declaración para mejorar la robustez del código
     * La validación depende del tipo de atributo.
     * Future maneja que la fecha debe ser futura.
     */
    @Future(message = "DATE MUST BE FUTURE")
    @NotNull(message = "DATE CANNOT BE NULL")
    @Column(name = "DATE")
    private LocalDateTime date;

    /**
     * La notación @JsonBackReference se usa en conjunto con @JsonManagedReference(En la otra entidad) para evitar referencias circulares
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @JsonBackReference("doctor-appointments")
    @NotNull(message = "DOCTOR CANNOT BE NULL")
    private Doctor doctor;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @JsonBackReference("patient-appointments")
    @NotNull(message = "PATIENT CANNOT BE NULL")
    private Patient patient;

}
