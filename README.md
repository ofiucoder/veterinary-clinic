# BREMEN's Codeeers
    Clínica Veterinaria 


# Descripción con sinopsis del proyecto

# Pre-requisitos (Que se necesita instalar para poder ejecutar el proyecto)

# Pasos para la instalación

# Ejecución de los tests (Captura de la cobertura)

# Diagramas realizados

```mermaid
classDiagram
    class App{
    }
    namespace Model{
    class Patient{
        - name
        - age
        - breed
        - gender
        - idNumber
        - tutorName
        - tutorPhoneNumber
        - collection of treatments.
        + All Getters and Setters ()
    }
    class Appointment{
        - dateTime
        - patient
        - consultationType
        - reason
        - status
    }
    class Treatment{
        - date
        - type
        - notes
    }}
    namespace Service{
    class PatientController{
    }
    class AppointmentController{
    }
    class TreatmentController{
    }}
    namespace View{
    class HomeView{
        - manager
        - patient
    }
    class ManagerView{
        - listPatient()
        - addPatient()
        - editPatient()
        - deletePatient()
    }
    class PatientView{
        - requestAppointment()
        - viewAppointmentList()
        - modifyAppointment()
        - cancelAppointment()
        - saveOrderedAppointmentList()
    }}

``` 


# Autores

@abdiaslabrador @Andreina2 @KARELIZ01 @NelliYanchuk @ofiucoder

# Codeeers

## …or create a new repository on the command line
> echo "# ClinicaVeterinaria" >> README.md
> git init
> git add README.md
> git commit -m "first commit"
> git branch -M main
> git remote add origin https://github.com/coder0fiu/ClinicaVeterinaria.git
> git push -u origin main

## …or push an existing repository from the command line
> git remote add origin https://github.com/coder0fiu/ClinicaVeterinaria.git
> git branch -M main
> git push -u origin main