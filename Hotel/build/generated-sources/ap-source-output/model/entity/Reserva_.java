package model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entity.Habitacion;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-14T21:29:39")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Habitacion> nhabitacion;
    public static volatile SingularAttribute<Reserva, String> fechae;
    public static volatile SingularAttribute<Reserva, String> fechas;
    public static volatile SingularAttribute<Reserva, String> dni;
    public static volatile SingularAttribute<Reserva, Short> nreserva;

}