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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aru
 */
@Entity
@Table(name = "MODELO")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m")
  , @NamedQuery(name = "Modelo.findById", query = "SELECT m FROM Modelo m WHERE m.id = :id")
  , @NamedQuery(name = "Modelo.findByNombre", query = "SELECT m FROM Modelo m WHERE m.nombre = :nombre")
  , @NamedQuery(name = "Modelo.findByImagen", query = "SELECT m FROM Modelo m WHERE m.imagen = :imagen")})
public class Modelo implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Short id;
  @Column(name = "NOMBRE")
  private String nombre;
  @Column(name = "IMAGEN")
  private String imagen;
  @JoinTable(name = "TIPOMODELO", joinColumns = {
    @JoinColumn(name = "MODELO", referencedColumnName = "ID")}, inverseJoinColumns = {
    @JoinColumn(name = "TIPO", referencedColumnName = "ID")})
  @ManyToMany
  private List<Tipo> tipoList;
  @OneToMany(mappedBy = "modelo")
  private List<Modeloacabado> modeloacabadoList;
  @OneToMany(mappedBy = "modelo")
  private List<Cita> citaList;

  public Modelo() {
  }

  public Modelo(Short id) {
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

  public String getImagen() {
    return imagen;
  }

  public void setImagen(String imagen) {
    this.imagen = imagen;
  }

  @XmlTransient
  public List<Tipo> getTipoList() {
    return tipoList;
  }

  public void setTipoList(List<Tipo> tipoList) {
    this.tipoList = tipoList;
  }

  @XmlTransient
  public List<Modeloacabado> getModeloacabadoList() {
    return modeloacabadoList;
  }

  public void setModeloacabadoList(List<Modeloacabado> modeloacabadoList) {
    this.modeloacabadoList = modeloacabadoList;
  }

  @XmlTransient
  public List<Cita> getCitaList() {
    return citaList;
  }

  public void setCitaList(List<Cita> citaList) {
    this.citaList = citaList;
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
    if (!(object instanceof Modelo)) {
      return false;
    }
    Modelo other = (Modelo) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Modelo[ id=" + id + " ]";
  }

}
