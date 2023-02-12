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

$('#movies').on('show.bs.modal', function (event) {
  const button = $(event.relatedTarget);
  console.log(button);
  const modal = $(this);
  $('.person-name', modal).text(button.data("person-name"));
  const params = new URLSearchParams();
  params.append("PERSON_ID", button.data("person-id"));
  params.append("OPTION", "FETCH_MOVIES");
  fetch('/TMDB/', {
    method: 'POST',
    body: params
  }).then(async res => {
    if (res.ok) $('.movies', modal).html(await res.text());
  });
});