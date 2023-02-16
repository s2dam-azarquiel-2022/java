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
@Table(name = "CITA")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c")
  , @NamedQuery(name = "Cita.findById", query = "SELECT c FROM Cita c WHERE c.id = :id")
  , @NamedQuery(name = "Cita.findByFecha", query = "SELECT c FROM Cita c WHERE c.fecha = :fecha")})
public class Cita implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Short id;
  @Column(name = "FECHA")
  private String fecha;
  @JoinColumn(name = "MODELO", referencedColumnName = "ID")
  @ManyToOne
  private Modelo modelo;
  @JoinColumn(name = "USUARIO", referencedColumnName = "ID")
  @ManyToOne
  private Usuario usuario;

  public Cita() {
  }

  public Cita(Short id) {
    this.id = id;
  }

  public Short getId() {
    return id;
  }

  public void setId(Short id) {
    this.id = id;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public Modelo getModelo() {
    return modelo;
  }

  public void setModelo(Modelo modelo) {
    this.modelo = modelo;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Cita)) {
      return false;
    }
    Cita other = (Cita) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Cita[ id=" + id + " ]";
  }

}
