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
package model.utils;

/**
 *
 * @author aru
 */
public final class ZResponse {
  public final String resource;
  public final Type type;

  public static enum Type {
    FORWARD,
    REDIRECT,
  }

  public ZResponse(String resource, Type type) {
    this.resource = resource;
    this.type = type;
  }
}
