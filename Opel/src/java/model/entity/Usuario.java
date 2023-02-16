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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
  , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
  , @NamedQuery(name = "Usuario.findByNick", query = "SELECT u FROM Usuario u WHERE u.nick = :nick")
  , @NamedQuery(name = "Usuario.findByPass", query = "SELECT u FROM Usuario u WHERE u.pass = :pass")})
public class Usuario implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "ID")
  private Short id;
  @Column(name = "NICK")
  private String nick;
  @Column(name = "PASS")
  private String pass;
  @OneToMany(mappedBy = "usuario")
  private List<Cita> citaList;

  public Usuario() {
  }

  public Usuario(Short id) {
    this.id = id;
  }

  public Short getId() {
    return id;
  }

  public void setId(Short id) {
    this.id = id;
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
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
    if (!(object instanceof Usuario)) {
      return false;
    }
    Usuario other = (Usuario) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.Usuario[ id=" + id + " ]";
  }

}
