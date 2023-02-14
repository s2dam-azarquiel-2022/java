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
import javax.persistence.Embeddable;

/**
 *
 * @author aru
 */
@Embeddable
public class OcupacionPK implements Serializable {

  @Basic(optional = false)
  @Column(name = "NHABITACION")
  private String nhabitacion;
  @Basic(optional = false)
  @Column(name = "FECHAE")
  private String fechae;

  public OcupacionPK() {
  }

  public OcupacionPK(String nhabitacion, String fechae) {
    this.nhabitacion = nhabitacion;
    this.fechae = fechae;
  }

  public String getNhabitacion() {
    return nhabitacion;
  }

  public void setNhabitacion(String nhabitacion) {
    this.nhabitacion = nhabitacion;
  }

  public String getFechae() {
    return fechae;
  }

  public void setFechae(String fechae) {
    this.fechae = fechae;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (nhabitacion != null ? nhabitacion.hashCode() : 0);
    hash += (fechae != null ? fechae.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof OcupacionPK)) {
      return false;
    }
    OcupacionPK other = (OcupacionPK) object;
    if ((this.nhabitacion == null && other.nhabitacion != null) || (this.nhabitacion != null && !this.nhabitacion.equals(other.nhabitacion))) {
      return false;
    }
    if ((this.fechae == null && other.fechae != null) || (this.fechae != null && !this.fechae.equals(other.fechae))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.entity.OcupacionPK[ nhabitacion=" + nhabitacion + ", fechae=" + fechae + " ]";
  }

}
