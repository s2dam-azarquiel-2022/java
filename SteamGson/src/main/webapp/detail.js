// Note: eclipse shows some nonexistent errors idk why

// Detail cache so not to fetch the same detail twice
var details = {};

const getDetail = (appID) => {
  if (appID in details) {
    // If appID detail was already fetched, show it's modal.
    details[appID].show();
  } else {
    // Else fetch it from detail.jsp, add the resulting HTML to
    // `detail-container`, create a new bootsrap modal object of it
    // and add it to the details cache for later use.
    const params = new URLSearchParams();
    params.append("appID", appID);
    fetch("detail.jsp", {
        method: 'POST',
        body: params,
    }).then(async res => {
      document.getElementById("detail-container").innerHTML += await res.text();
      details[appID] = new bootstrap.Modal(`#detail-${appID}`, {});
      details[appID].show();
    });
  }
}