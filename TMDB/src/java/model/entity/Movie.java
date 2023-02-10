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
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aru
 */
@Entity
@Table(name = "MOVIE")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m")
  , @NamedQuery(name = "Movie.findById", query = "SELECT m FROM Movie m WHERE m.id = :id")
  , @NamedQuery(name = "Movie.findByTitulo", query = "SELECT m FROM Movie m WHERE m.titulo = :titulo")
  , @NamedQuery(name = "Movie.findByFecha", query = "SELECT m FROM Movie m WHERE m.fecha = :fecha")
  , @NamedQuery(name = "Movie.findByPoster", query = "SELECT m FROM Movie m WHERE m.poster = :poster")
  , @NamedQuery(name = "Movie.findByTrama", query = "SELECT m FROM Movie m WHERE m.trama = :trama")})
public class Movie implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "TITULO")
  private String titulo;
  @Column(name = "FECHA")
  @Temporal(TemporalType.DATE)
  private Date fecha;
  @Column(name = "POSTER")
  private String poster;
  @Column(name = "TRAMA")
  private String trama;
  @JoinTable(name = "PERSONMOVIE", joinColumns = {
    @JoinColumn(name = "IDMOVIE", referencedColumnName = "ID")}, inverseJoinColumns = {
    @JoinColumn(name = "IDPERSON", referencedColumnName = "ID")})
  @ManyToMany
  private List<Person> personList;

  public Movie() {
  }

  public Movie(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public String getTrama() {
    return trama;
  }

  public void setTrama(String trama) {
    this.trama = trama;
  }

  @XmlTransient
  public List<Person> getPersonList() {
    return personList;
  }

  public void setPersonList(List<Person> personList) {
    this.personList = personList;
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
    if (!(object instanceof Movie)) {
      return false;
    }
    Movie other = (Movie) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Movie[ id=" + id + " ]";
  }

}
