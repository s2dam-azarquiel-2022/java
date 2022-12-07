$("#logo").click(() => { $("#logo-form").submit(); });

$(".star").click(function () {
  fetch(
    "AddReview?" +
      `ROUTE_ID=${$(this).parent().get(0).getAttribute('routeID')}`+
      `&STARS=${this.getAttribute('val')}`
  ).then(() => window.location.reload());
})
