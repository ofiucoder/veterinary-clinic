package dev.project.veterclinic.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import dev.project.veterclinic.dtos.AppointmentDto;
import dev.project.veterclinic.dtos.appointmentDtoResponse.AppointDtoResponse;
import dev.project.veterclinic.dtos.petDtoResponse.PetBreedDtoResponse;
import dev.project.veterclinic.dtos.petDtoResponse.PetDtoResponse;
import dev.project.veterclinic.dtos.petDtoResponse.PetOwnerDtoReponse;
import dev.project.veterclinic.exceptions.appointment.AppointmentNotFoundException;
import dev.project.veterclinic.exceptions.pet.PetNotFoundException;
import dev.project.veterclinic.models.Appointment;
import dev.project.veterclinic.repositories.AppointmentRepository;
import dev.project.veterclinic.repositories.OwnerRepository;
import dev.project.veterclinic.repositories.PetRepository;
import dev.project.veterclinic.models.Pet;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmetRepository;
    private final PetRepository petRepository;
    public AppointmentService(AppointmentRepository appointmetRepository, PetRepository petRepository, OwnerRepository ownerRepository) {
        this.appointmetRepository = appointmetRepository;
        this.petRepository = petRepository;
    }

    // Fetch all appointments
    public List<AppointDtoResponse> findAll() {
        
        List<AppointDtoResponse> apptDtoList = new ArrayList<>();
        for (Appointment appointment : appointmetRepository.findAll()) {
            apptDtoList.add(new AppointDtoResponse(
                appointment.getId(),
                appointment.getDate(),
                appointment.getType(),
                appointment.getReason(),
                appointment.getStatus(),
                new PetDtoResponse(
                                      appointment.getPet().getId(),
                                      appointment.getPet().getName(),
                                      appointment.getPet().getDateOfBirth(),
                                      appointment.getPet().getGender(),
                                      appointment.getPet().getPetType(),
                                      new  PetBreedDtoResponse(
                                          appointment.getPet().getBreed().getId(),
                                          appointment.getPet().getBreed().getBreedName()
                                      ),
                                      new  PetOwnerDtoReponse(
                                        appointment.getPet().getOwner().getId(),
                                        appointment.getPet().getOwner().getFirstName(),
                                        appointment.getPet().getOwner().getLastName(),
                                        appointment.getPet().getOwner().getDni(),
                                        appointment.getPet().getOwner().getPhoneNumber()
                                      )
                                  )
              ));
        }
        return apptDtoList;
    }

    // Save a new appointment (creating a new one)
    public AppointDtoResponse save(AppointmentDto appointmentDto) {
        Pet pet = petRepository.findByOwnerDniAndPetId(appointmentDto.ownerDni(), appointmentDto.petId());
        if(pet == null ){
            throw new PetNotFoundException("Pet with the id " + appointmentDto.petId() + " is not of the owner " + appointmentDto.ownerDni());
        }

        Appointment appointment = new Appointment(
                appointmentDto.date(),
                appointmentDto.type(),
                appointmentDto.reason(),
                appointmentDto.status(),
                pet,
                pet.getOwner());
        appointmetRepository.save(appointment);
        return new AppointDtoResponse(
            appointment.getId(),
            appointment.getDate(),
            appointment.getType(),
            appointment.getReason(),
            appointment.getStatus(),
            new PetDtoResponse(
                                  appointment.getPet().getId(),
                                  appointment.getPet().getName(),
                                  appointment.getPet().getDateOfBirth(),
                                  appointment.getPet().getGender(),
                                  appointment.getPet().getPetType(),
                                  new  PetBreedDtoResponse(
                                      appointment.getPet().getBreed().getId(),
                                      appointment.getPet().getBreed().getBreedName()
                                  ),
                                  new  PetOwnerDtoReponse(
                                    appointment.getPet().getOwner().getId(),
                                    appointment.getPet().getOwner().getFirstName(),
                                    appointment.getPet().getOwner().getLastName(),
                                    appointment.getPet().getOwner().getDni(),
                                    appointment.getPet().getOwner().getPhoneNumber()
                                  )
                              )
          );
    }

    public AppointDtoResponse saveAppointmentByOwnerDni(String ownerDni, AppointmentDto appointmentDto) {
        Pet pet = petRepository.findByOwnerDniAndPetId(ownerDni, appointmentDto.petId());
        if(pet == null ){
            throw new PetNotFoundException("Pet with the id " + appointmentDto.petId() + " is not of the owner " + ownerDni);
        }

        Appointment appointment = new Appointment(
                appointmentDto.date(),
                appointmentDto.type(),
                appointmentDto.reason(),
                appointmentDto.status(),
                pet,
                pet.getOwner());
        appointmetRepository.save(appointment);
        return new AppointDtoResponse(
            appointment.getId(),
            appointment.getDate(),
            appointment.getType(),
            appointment.getReason(),
            appointment.getStatus(),
            new PetDtoResponse(
                                  appointment.getPet().getId(),
                                  appointment.getPet().getName(),
                                  appointment.getPet().getDateOfBirth(),
                                  appointment.getPet().getGender(),
                                  appointment.getPet().getPetType(),
                                  new  PetBreedDtoResponse(
                                      appointment.getPet().getBreed().getId(),
                                      appointment.getPet().getBreed().getBreedName()
                                  ),
                                  new  PetOwnerDtoReponse(
                                    appointment.getPet().getOwner().getId(),
                                    appointment.getPet().getOwner().getFirstName(),
                                    appointment.getPet().getOwner().getLastName(),
                                    appointment.getPet().getOwner().getDni(),
                                    appointment.getPet().getOwner().getPhoneNumber()
                                  )
                              )
          );
    }
    // Get appointment by id
    public AppointDtoResponse getById(int id) {
        Appointment appointment = appointmetRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
        return new AppointDtoResponse(
            appointment.getId(),
            appointment.getDate(),
            appointment.getType(),
            appointment.getReason(),
            appointment.getStatus(),
            new PetDtoResponse(
                                  appointment.getPet().getId(),
                                  appointment.getPet().getName(),
                                  appointment.getPet().getDateOfBirth(),
                                  appointment.getPet().getGender(),
                                  appointment.getPet().getPetType(),
                                  new  PetBreedDtoResponse(
                                      appointment.getPet().getBreed().getId(),
                                      appointment.getPet().getBreed().getBreedName()
                                  ),
                                  new  PetOwnerDtoReponse(
                                    appointment.getPet().getOwner().getId(),
                                    appointment.getPet().getOwner().getFirstName(),
                                    appointment.getPet().getOwner().getLastName(),
                                    appointment.getPet().getOwner().getDni(),
                                    appointment.getPet().getOwner().getPhoneNumber()
                                  )
                              )
          );
    }

    // Find appointments by ownerId and order by date
    public List<AppointDtoResponse> findAppointmentsByOwnerId(String ownerDni) {
        
        List<AppointDtoResponse> apptDtoList = new ArrayList<>();
        for (Appointment appointment : appointmetRepository.findByOwnerDniOrderByDateAsc(ownerDni)) {
            apptDtoList.add(new AppointDtoResponse(
                appointment.getId(),
                appointment.getDate(),
                appointment.getType(),
                appointment.getReason(),
                appointment.getStatus(),
                new PetDtoResponse(
                                      appointment.getPet().getId(),
                                      appointment.getPet().getName(),
                                      appointment.getPet().getDateOfBirth(),
                                      appointment.getPet().getGender(),
                                      appointment.getPet().getPetType(),
                                      new  PetBreedDtoResponse(
                                          appointment.getPet().getBreed().getId(),
                                          appointment.getPet().getBreed().getBreedName()
                                      ),
                                      new  PetOwnerDtoReponse(
                                        appointment.getPet().getOwner().getId(),
                                        appointment.getPet().getOwner().getFirstName(),
                                        appointment.getPet().getOwner().getLastName(),
                                        appointment.getPet().getOwner().getDni(),
                                        appointment.getPet().getOwner().getPhoneNumber()
                                      )
                                  )
              ));
        }
        return apptDtoList;
    }

    // Find appointment by ownerId and appointmentId
    public AppointDtoResponse findAppointmentByOwnerIdAndAppId(String ownerDni, int appointmentId) {
        Appointment appointment = appointmetRepository.findByOwnerDniAndAppointmentId(ownerDni, appointmentId);
        if(appointment == null){
            throw new AppointmentNotFoundException("Appointment not found");
        }
        return new AppointDtoResponse(
            appointment.getId(),
            appointment.getDate(),
            appointment.getType(),
            appointment.getReason(),
            appointment.getStatus(),
            new PetDtoResponse(
                                  appointment.getPet().getId(),
                                  appointment.getPet().getName(),
                                  appointment.getPet().getDateOfBirth(),
                                  appointment.getPet().getGender(),
                                  appointment.getPet().getPetType(),
                                  new  PetBreedDtoResponse(
                                      appointment.getPet().getBreed().getId(),
                                      appointment.getPet().getBreed().getBreedName()
                                  ),
                                  new  PetOwnerDtoReponse(
                                    appointment.getPet().getOwner().getId(),
                                    appointment.getPet().getOwner().getFirstName(),
                                    appointment.getPet().getOwner().getLastName(),
                                    appointment.getPet().getOwner().getDni(),
                                    appointment.getPet().getOwner().getPhoneNumber()
                                  )
                              )
          );
    }


    public Appointment getAppointmentsByOwnerAndDate(String ownerDni, LocalDateTime date) {
        // Check if an appointment exists with the same owner_id and date
        return appointmetRepository.findByOwnerDniAndDate(ownerDni, date);
    }
    
    public AppointDtoResponse updateAppointmentByOwnerDniAndAppointmentId(String ownerDni, int appointmentId, AppointmentDto appointmentDto) {
        //check if the appointment exist
        Appointment existingAppointment = appointmetRepository.findByOwnerDniAndAppointmentId(ownerDni, appointmentId);
        if(existingAppointment == null){
            throw new AppointmentNotFoundException("Appointment not found");
        }
        //check if the pet that is going to change exist
        Pet pet = petRepository.findByOwnerDniAndPetId(ownerDni, appointmentDto.petId());
        if(pet == null ){
            throw new PetNotFoundException("Pet with the id " + appointmentDto.petId() + " is not of the owner " + ownerDni);
        }

        // Update the appointment fields
        existingAppointment.setDate(appointmentDto.date());
        existingAppointment.setType(appointmentDto.type());
        existingAppointment.setReason(appointmentDto.reason());
        existingAppointment.setStatus(appointmentDto.status());
        existingAppointment.setPet(pet);
        appointmetRepository.save(existingAppointment);

        return new AppointDtoResponse(
                                  existingAppointment.getId(),
                                  existingAppointment.getDate(),
                                  existingAppointment.getType(),
                                  existingAppointment.getReason(),
                                  existingAppointment.getStatus(),
                                  new PetDtoResponse(
                                                        pet.getId(),
                                                        pet.getName(),
                                                        pet.getDateOfBirth(),
                                                        pet.getGender(),
                                                        pet.getPetType(),
                                                        new  PetBreedDtoResponse(
                                                            pet.getBreed().getId(),
                                                            pet.getBreed().getBreedName()
                                                        ),
                                                        new  PetOwnerDtoReponse(
                                                            pet.getOwner().getId(),
                                                            pet.getOwner().getFirstName(),
                                                            pet.getOwner().getLastName(),
                                                            pet.getOwner().getDni(),
                                                            pet.getOwner().getPhoneNumber()
                                                        )
                                                    )
                                );
    }

    public void deleteAppointmentByOwnerDniAndappointmentId(String ownerDni, int appointmentId) {
        Appointment existingAppointment = appointmetRepository.findByOwnerDniAndAppointmentId(ownerDni, appointmentId);
        if(existingAppointment == null){
            throw new AppointmentNotFoundException("Appointment not found");
        }
        appointmetRepository.delete(existingAppointment);
    } 
}
