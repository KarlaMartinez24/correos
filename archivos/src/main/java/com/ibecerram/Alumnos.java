package com.ibecerram;

public class Alumnos
{
    private String nombre;
    private String matricula;
    private String email;
    private String carrera;
    private String tutor;
    private String emailTutor;

    public Alumnos(String nombre, String matricula, String email, String carrera, String tutor, String emailTutor)
    {
        this.nombre = nombre;
        this.matricula = matricula;
        this.email = email;
        this.carrera = carrera;
        this.tutor = tutor;
        this.emailTutor = emailTutor;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula)
    {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera)
    {
        this.carrera = carrera;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor)
    {
        this.tutor = tutor;
    }

    public String getEmailTutor() {
        return emailTutor;
    }

    public void setEmailTutor(String emailTutor)
    {
        this.emailTutor = emailTutor;
    }
}
