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

/* global fetch */


$('#califications').on('show.bs.modal', function (event) {
  const button = $(event.relatedTarget);
  console.log(button);
  const modal = $(this);
  $('.beach-name', modal).text(button.data("beach-name"));
  const params = new URLSearchParams();
  params.append("BEACH_ID", button.data("beach-id"));
  params.append("OPTION", "CALIFICATIONS");
  fetch('/Playa/Fetcher', {
    method: 'POST',
    body: params
  }).then(async res => {
    if (res.ok) $('.califications', modal).html(await res.text());
  });
});
