$(".setFavoriteBtn").click(
  function() {
    fetch(
      "Controller?option=setFavorite"
        + `&carID=${this.attributes["value"].value}`
    );
    const pressedStar = $(this);
    if (pressedStar.hasClass("text-muted")) {
      pressedStar.removeClass("text-muted");
      pressedStar.addClass("text-warning");
    } else {
      pressedStar.removeClass("text-warning");
      pressedStar.addClass("text-muted");
    }
  }
);
