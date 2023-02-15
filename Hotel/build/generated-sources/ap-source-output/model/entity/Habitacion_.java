package model.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entity.Ocupacion;
import model.entity.Reserva;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-14T21:29:39")
@StaticMetamodel(Habitacion.class)
public class Habitacion_ { 

    public static volatile SingularAttribute<Habitacion, String> nhabitacion;
    public static volatile SingularAttribute<Habitacion, BigDecimal> precio;
    public static volatile ListAttribute<Habitacion, Ocupacion> ocupacionList;
    public static volatile SingularAttribute<Habitacion, Short> npersonas;
    public static volatile ListAttribute<Habitacion, Reserva> reservaList;
    public static volatile SingularAttribute<Habitacion, Short> ocupada;

}