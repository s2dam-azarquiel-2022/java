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


/* global bootstrap, fetch */

var detail = $('#detail')
var detailModal = new bootstrap.Modal(detail, {});
var modelName = $('.model-name', detail);
var modelImg = $('.model-img', detail);
var modelAcabados = $('.model-acabados', detail);

$('.car-card').click(function() {
  const card = $(this);
  modelName.text(card.data('name'));
  modelImg.attr('src', `img/coches/${card.data("img")}`);
  detailModal.show();
  const params = new URLSearchParams();
  params.append("OPTION", "FETCH_ACABADOS");
  params.append("MODEL_ID", card.data("id"));
  fetch('/Opel/', {
    method: 'POST',
    body: params
  }).then(async res => {
    if (res.ok) modelAcabados.html(await res.text());
  });
});