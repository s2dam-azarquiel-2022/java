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
import javax.persistence.ManyToOne;
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
@Table(name = "PLAYA")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Playa.findAll", query = "SELECT p FROM Playa p")
  , @NamedQuery(name = "Playa.findById", query = "SELECT p FROM Playa p WHERE p.id = :id")
  , @NamedQuery(name = "Playa.findByNombre", query = "SELECT p FROM Playa p WHERE p.nombre = :nombre")
  , @NamedQuery(name = "Playa.findByDescripcion", query = "SELECT p FROM Playa p WHERE p.descripcion = :descripcion")
  , @NamedQuery(name = "Playa.findByMunicipio", query = "SELECT p FROM Playa p WHERE p.municipio.id = :municipioId")})
public class Playa implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "NOMBRE")
  private String nombre;
  @Column(name = "DESCRIPCION")
  private String descripcion;
  @OneToMany(mappedBy = "playa")
  private List<Images> imagesList;
  @JoinColumn(name = "MUNICIPIO", referencedColumnName = "ID")
  @ManyToOne
  private Municipio municipio;
  @OneToMany(mappedBy = "playa")
  private List<Punto> puntoList;

  public Playa() {
  }

  public Playa(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  @XmlTransient
  public List<Images> getImagesList() {
    return imagesList;
  }

  public void setImagesList(List<Images> imagesList) {
    this.imagesList = imagesList;
  }

  public Municipio getMunicipio() {
    return municipio;
  }

  public void setMunicipio(Municipio municipio) {
    this.municipio = municipio;
  }

  @XmlTransient
  public List<Punto> getPuntoList() {
    return puntoList;
  }

  public void setPuntoList(List<Punto> puntoList) {
    this.puntoList = puntoList;
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
    if (!(object instanceof Playa)) {
      return false;
    }
    Playa other = (Playa) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Playa[ id=" + id + " ]";
  }

}
