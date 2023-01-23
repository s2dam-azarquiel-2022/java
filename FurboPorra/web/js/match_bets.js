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

var cache;
var cacheContainer;
var errToast;

$(document).ready(function() {
  // Cache so not to fetch the same thing twice
  cache = {};
  cacheContainer = document.getElementById("matchBetsCache");
  errToast = document.getElementById("errToast");
});

$('.matchBets').click(function() {
  const id = this.getAttribute('matchID');
  if (id in cache) {
    // If that id was already fetched, show it's modal.
    cache[id].show();
  } else {
    // Else fetch it, add the resulting HTML to `cacheContainer`,
    // create a new bootsrap modal object of it
    // and add it to the cache for later use.
    const params = new URLSearchParams();
    params.append("SELECTED_MATCH", id);
    fetch("/FurboPorra/MatchBets", {
        method: 'POST',
        body: params
    }).then(async res => {
      if (res.ok) {
        cacheContainer.innerHTML += await res.text();
        cache[id] = new bootstrap.Modal(`#matchBetsModal-${id}`, {});
        cache[id].show();
      } else {
        new bootstrap.Toast(errToast).show();
      }
    });
  }
});
