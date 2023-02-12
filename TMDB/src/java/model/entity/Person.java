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
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
  , @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id")
  , @NamedQuery(name = "Person.findByNombre", query = "SELECT p FROM Person p WHERE p.nombre = :nombre")
  , @NamedQuery(name = "Person.findByFoto", query = "SELECT p FROM Person p WHERE p.foto = :foto")
  , @NamedQuery(name = "Person.findByPopularidad", query = "SELECT p FROM Person p WHERE p.popularidad = :popularidad")
  , @NamedQuery(name = "Person.avgPoints", query = "SELECT COALESCE(AVG(r.puntos), 0.0) FROM Rating r WHERE r.idperson.id = :id")})
public class Person implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "NOMBRE")
  private String nombre;
  @Column(name = "FOTO")
  private String foto;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "POPULARIDAD")
  private BigDecimal popularidad;
  @ManyToMany(mappedBy = "personList")
  private List<Movie> movieList;
  @OneToMany(mappedBy = "idperson")
  private List<Rating> ratingList;

  public Person() {
  }

  public Person(Integer id) {
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

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public BigDecimal getPopularidad() {
    return popularidad;
  }

  public void setPopularidad(BigDecimal popularidad) {
    this.popularidad = popularidad;
  }

  @XmlTransient
  public List<Movie> getMovieList() {
    return movieList;
  }

  public void setMovieList(List<Movie> movieList) {
    this.movieList = movieList;
  }

  @XmlTransient
  public List<Rating> getRatingList() {
    return ratingList;
  }

  public void setRatingList(List<Rating> ratingList) {
    this.ratingList = ratingList;
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
    if (!(object instanceof Person)) {
      return false;
    }
    Person other = (Person) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Person[ id=" + id + " ]";
  }

}
