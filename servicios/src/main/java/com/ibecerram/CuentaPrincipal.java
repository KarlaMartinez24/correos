package com.ibecerram;

public class CuentaPrincipal
{
    private String nombre;
    private String correo;
    private String password;

    public CuentaPrincipal(String nombre, String correo, String password)
    {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getCorreo()
    {
        return correo;
    }

    public String getPassword()
    {
        return password;
    }
}
