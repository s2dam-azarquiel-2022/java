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
@Table(name = "PROVINCIA")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p")
  , @NamedQuery(name = "Provincia.findById", query = "SELECT p FROM Provincia p WHERE p.id = :id")
  , @NamedQuery(name = "Provincia.findByNombre", query = "SELECT p FROM Provincia p WHERE p.nombre = :nombre")
  , @NamedQuery(name = "Provincia.findByCcaa", query = "SELECT p FROM Provincia p WHERE p.ccaa.id = :ccaaId")})
public class Provincia implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Short id;
  @Column(name = "NOMBRE")
  private String nombre;
  @JoinColumn(name = "CCAA", referencedColumnName = "ID")
  @ManyToOne
  private Ccaa ccaa;
  @OneToMany(mappedBy = "provincia")
  private List<Municipio> municipioList;

  public Provincia() {
  }

  public Provincia(Short id) {
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

  public Ccaa getCcaa() {
    return ccaa;
  }

  public void setCcaa(Ccaa ccaa) {
    this.ccaa = ccaa;
  }

  @XmlTransient
  public List<Municipio> getMunicipioList() {
    return municipioList;
  }

  public void setMunicipioList(List<Municipio> municipioList) {
    this.municipioList = municipioList;
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
    if (!(object instanceof Provincia)) {
      return false;
    }
    Provincia other = (Provincia) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Provincia[ id=" + id + " ]";
  }

}
