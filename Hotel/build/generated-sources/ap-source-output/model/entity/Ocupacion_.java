package model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entity.Habitacion;
import model.entity.OcupacionPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-14T21:29:39")
@StaticMetamodel(Ocupacion.class)
public class Ocupacion_ { 

    public static volatile SingularAttribute<Ocupacion, OcupacionPK> ocupacionPK;
    public static volatile SingularAttribute<Ocupacion, String> fechas;
    public static volatile SingularAttribute<Ocupacion, String> dni;
    public static volatile SingularAttribute<Ocupacion, Habitacion> habitacion;

}