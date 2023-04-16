package com.medicalhourmanagement.medicalhourmanagement.appointment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByDoctorIdOrPatientId(Long doctorId,Long patientId);
}
