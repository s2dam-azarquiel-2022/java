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

/* global bootstrap */

var addBetModal;
var addBetModalTitle;
var addBetSelectedMatch;

$(document).ready(function() {
  const addBetModalSelector = $('#addBetModal');
  addBetModalTitle = $('.modal-title', addBetModalSelector);
  addBetSelectedMatch = $('input[name="SELECTED_MATCH"]', addBetModalSelector);
  addBetModal = new bootstrap.Modal('#addBetModal', {});
});


$('.addBet').click(function() {
  addBetModalTitle.text(`Apuesta para el ${this.getAttribute('local')} - ${this.getAttribute('visitant')}`);
  addBetSelectedMatch.val(this.getAttribute('matchID'));
  addBetModal.show();
});