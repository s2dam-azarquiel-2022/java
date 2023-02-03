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
@Table(name = "PUNTO")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Punto.findAll", query = "SELECT p FROM Punto p")
  , @NamedQuery(name = "Punto.findById", query = "SELECT p FROM Punto p WHERE p.id = :id")
  , @NamedQuery(name = "Punto.findByPuntos", query = "SELECT p FROM Punto p WHERE p.puntos = :puntos")})
public class Punto implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "PUNTOS")
  private Short puntos;
  @JoinColumn(name = "PLAYA", referencedColumnName = "ID")
  @ManyToOne
  private Playa playa;
  @JoinColumn(name = "USUARIO", referencedColumnName = "ID")
  @ManyToOne
  private Usuario usuario;

  public Punto() {
  }

  public Punto(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Short getPuntos() {
    return puntos;
  }

  public void setPuntos(Short puntos) {
    this.puntos = puntos;
  }

  public Playa getPlaya() {
    return playa;
  }

  public void setPlaya(Playa playa) {
    this.playa = playa;
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
    if (!(object instanceof Punto)) {
      return false;
    }
    Punto other = (Punto) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Punto[ id=" + id + " ]";
  }

}
