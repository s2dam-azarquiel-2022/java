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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "RESERVA")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
  , @NamedQuery(name = "Reserva.findByNreserva", query = "SELECT r FROM Reserva r WHERE r.nreserva = :nreserva")
  , @NamedQuery(name = "Reserva.findByFechae", query = "SELECT r FROM Reserva r WHERE r.fechae = :fechae")
  , @NamedQuery(name = "Reserva.findByFechas", query = "SELECT r FROM Reserva r WHERE r.fechas = :fechas")
  , @NamedQuery(name = "Reserva.findByDni", query = "SELECT r FROM Reserva r WHERE r.dni = :dni")})
public class Reserva implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "NRESERVA")
  private Short nreserva;
  @Basic(optional = false)
  @Column(name = "FECHAE")
  private String fechae;
  @Basic(optional = false)
  @Column(name = "FECHAS")
  private String fechas;
  @Basic(optional = false)
  @Column(name = "DNI")
  private String dni;
  @JoinColumn(name = "NHABITACION", referencedColumnName = "NHABITACION")
  @ManyToOne(optional = false)
  private Habitacion nhabitacion;

  public Reserva() {
  }

  public Reserva(Short nreserva) {
    this.nreserva = nreserva;
  }

  public Reserva(Short nreserva, String fechae, String fechas, String dni) {
    this.nreserva = nreserva;
    this.fechae = fechae;
    this.fechas = fechas;
    this.dni = dni;
  }

  public Short getNreserva() {
    return nreserva;
  }

  public void setNreserva(Short nreserva) {
    this.nreserva = nreserva;
  }

  public String getFechae() {
    return fechae;
  }

  public void setFechae(String fechae) {
    this.fechae = fechae;
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

  public Habitacion getNhabitacion() {
    return nhabitacion;
  }

  public void setNhabitacion(Habitacion nhabitacion) {
    this.nhabitacion = nhabitacion;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (nreserva != null ? nreserva.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Reserva)) {
      return false;
    }
    Reserva other = (Reserva) object;
    if ((this.nreserva == null && other.nreserva != null) || (this.nreserva != null && !this.nreserva.equals(other.nreserva))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Reserva[ nreserva=" + nreserva + " ]";
  }

}
