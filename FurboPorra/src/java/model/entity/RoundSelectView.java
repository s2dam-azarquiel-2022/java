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

/**
 *
 * @author aru
 */
public class RoundSelectView {
  public short id;
  public String name;

  public RoundSelectView(short id, String name) {
    this.id = id;
    this.name = name;
  }

  public static RoundSelectView toSelectView(Jornada v) {
    return new RoundSelectView(
      v.getIdjornada(),
      String.format(
        "%s (%s - %s)",
        v.getNombre(),
        v.getFechainicio(),
        v.getFechafin()
      )
    );
  }
}
