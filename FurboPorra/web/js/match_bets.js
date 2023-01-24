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

/* global fetch, bootstrap */

var matchBetsModal;
var matchBetsModalTitle;
var matchBetsContent;

$(document).ready(function() {
  const addBetModalSelector = $('#matchBetsModal');
  matchBetsModalTitle = $('.modal-title', addBetModalSelector);
  matchBetsModal = new bootstrap.Modal('#matchBetsModal', {});
  matchBetsContent = $('.modal-body', addBetModalSelector);
});


$('.matchBets').click(function() {
  matchBetsModalTitle.text(`Apuestas para el ${this.getAttribute('local')} - ${this.getAttribute('visitant')}`);
  const params = new URLSearchParams();
  params.append("SELECTED_MATCH", this.getAttribute('matchID'));
  fetch('/FurboPorra/MatchBets', {
    method: 'POST',
    body: params
  }).then(async res => {
    if (res.ok) {
      matchBetsContent.html(await res.text());
    }
  });
  matchBetsModal.show();
});
