/*
 * Copyright (C) 2023 aru
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aru
 */
@Entity
@Table(name = "OCUPACION")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Ocupacion.findAll", query = "SELECT o FROM Ocupacion o")
  , @NamedQuery(name = "Ocupacion.findByNhabitacion", query = "SELECT o FROM Ocupacion o WHERE o.ocupacionPK.nhabitacion = :nhabitacion")
  , @NamedQuery(name = "Ocupacion.findByFechae", query = "SELECT o FROM Ocupacion o WHERE o.ocupacionPK.fechae = :fechae")
  , @NamedQuery(name = "Ocupacion.findByFechas", query = "SELECT o FROM Ocupacion o WHERE o.fechas = :fechas")
  , @NamedQuery(name = "Ocupacion.findByDni", query = "SELECT o FROM Ocupacion o WHERE o.dni = :dni")})
public class Ocupacion implements Serializable {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected OcupacionPK ocupacionPK;
  @Basic(optional = false)
  @Column(name = "FECHAS")
  private String fechas;
  @Basic(optional = false)
  @Column(name = "DNI")
  private String dni;
  @JoinColumn(name = "NHABITACION", referencedColumnName = "NHABITACION", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private Habitacion habitacion;

  public Ocupacion() {
  }

  public Ocupacion(OcupacionPK ocupacionPK) {
    this.ocupacionPK = ocupacionPK;
  }

  public Ocupacion(OcupacionPK ocupacionPK, String fechas, String dni) {
    this.ocupacionPK = ocupacionPK;
    this.fechas = fechas;
    this.dni = dni;
  }

  public Ocupacion(String nhabitacion, String fechae) {
    this.ocupacionPK = new OcupacionPK(nhabitacion, fechae);
  }

  public OcupacionPK getOcupacionPK() {
    return ocupacionPK;
  }

  public void setOcupacionPK(OcupacionPK ocupacionPK) {
    this.ocupacionPK = ocupacionPK;
  }

  public String getFechas() {
    return fechas;
  }

  public void setFechas(String fechas) {
    this.fechas = fechas;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public Habitacion getHabitacion() {
    return habitacion;
  }

  public void setHabitacion(Habitacion habitacion) {
    this.habitacion = habitacion;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (ocupacionPK != null ? ocupacionPK.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Ocupacion)) {
      return false;
    }
    Ocupacion other = (Ocupacion) object;
    if ((this.ocupacionPK == null && other.ocupacionPK != null) || (this.ocupacionPK != null && !this.ocupacionPK.equals(other.ocupacionPK))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Ocupacion[ ocupacionPK=" + ocupacionPK + " ]";
  }

}
