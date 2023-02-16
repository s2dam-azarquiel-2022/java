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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "ITEMACABADO")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Itemacabado.findAll", query = "SELECT i FROM Itemacabado i")
  , @NamedQuery(name = "Itemacabado.findById", query = "SELECT i FROM Itemacabado i WHERE i.id = :id")
  , @NamedQuery(name = "Itemacabado.findByNombre", query = "SELECT i FROM Itemacabado i WHERE i.nombre = :nombre")})
public class Itemacabado implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Short id;
  @Column(name = "NOMBRE")
  private String nombre;
  @JoinTable(name = "MODELOACABADOITEM", joinColumns = {
    @JoinColumn(name = "ITEMACABADO", referencedColumnName = "ID")}, inverseJoinColumns = {
    @JoinColumn(name = "MODELOACABADO", referencedColumnName = "ID")})
  @ManyToMany
  private List<Modeloacabado> modeloacabadoList;

  public Itemacabado() {
  }

  public Itemacabado(Short id) {
    this.id = id;
  }

  public Short getId() {
    return id;
  }

  public void setId(Short id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @XmlTransient
  public List<Modeloacabado> getModeloacabadoList() {
    return modeloacabadoList;
  }

  public void setModeloacabadoList(List<Modeloacabado> modeloacabadoList) {
    this.modeloacabadoList = modeloacabadoList;
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
    if (!(object instanceof Itemacabado)) {
      return false;
    }
    Itemacabado other = (Itemacabado) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Itemacabado[ id=" + id + " ]";
  }

}
