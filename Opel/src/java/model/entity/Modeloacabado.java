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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aru
 */
@Entity
@Table(name = "MODELOACABADO")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Modeloacabado.findAll", query = "SELECT m FROM Modeloacabado m")
  , @NamedQuery(name = "Modeloacabado.findById", query = "SELECT m FROM Modeloacabado m WHERE m.id = :id")})
public class Modeloacabado implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Short id;
  @ManyToMany(mappedBy = "modeloacabadoList")
  private List<Itemacabado> itemacabadoList;
  @JoinColumn(name = "ACABADO", referencedColumnName = "ID")
  @ManyToOne
  private Acabado acabado;
  @JoinColumn(name = "MODELO", referencedColumnName = "ID")
  @ManyToOne
  private Modelo modelo;

  public Modeloacabado() {
  }

  public Modeloacabado(Short id) {
    this.id = id;
  }

  public Short getId() {
    return id;
  }

  public void setId(Short id) {
    this.id = id;
  }

  @XmlTransient
  public List<Itemacabado> getItemacabadoList() {
    return itemacabadoList;
  }

  public void setItemacabadoList(List<Itemacabado> itemacabadoList) {
    this.itemacabadoList = itemacabadoList;
  }

  public Acabado getAcabado() {
    return acabado;
  }

  public void setAcabado(Acabado acabado) {
    this.acabado = acabado;
  }

  public Modelo getModelo() {
    return modelo;
  }

  public void setModelo(Modelo modelo) {
    this.modelo = modelo;
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
    if (!(object instanceof Modeloacabado)) {
      return false;
    }
    Modeloacabado other = (Modeloacabado) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Modeloacabado[ id=" + id + " ]";
  }

}
