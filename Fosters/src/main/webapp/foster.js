const forEachStar = (from, to, f) => {
  for (let i = from; i <= to; i++) {
    f($(".star-review-"+i), i)
  }
}
      
const starNumber = (selected) => +selected.attr('for').charAt(5);
      
var selected = 0;
var selectedProductId;

function resetRightStars() { forEachStar(
  selected+1,
  5,
  (j, i) => j.removeClass("text-warning")
)}

function resetAllStars() {
  forEachStar(
    1,
    5,
    (j) => j.removeClass("text-warning")
  );
  $("#reviewForm")[0].reset();
}

$(".star-review").hover(
  function() { forEachStar(
    1,
    starNumber($(this)),
    (j) => j.addClass("text-warning")
  )},
  resetRightStars
)
    	
$(".star-review").click(
  function() {
    selected = starNumber($(this));
    resetRightStars();
  }
)

const reviewModal = new bootstrap.Modal('#addReview');

$("#addReview").bind("show.bs.modal", function(event) {
  const data = event.relatedTarget;
  selectedProductId = +data.getAttribute("data-bs-id");
  $("#addReviewProductName").text(data.getAttribute("data-bs-title"));
});

$("#addReview").bind("hide.bs.modal", function() {resetAllStars();});

$(".openReviewModal").click(function() {
  reviewModal.show(this);
});

$("#acceptReviewModal").click(
  function() {
    const selectedStar = $('input[name="stars"]:checked').val();
    resetAllStars();
    if (selectedStar) {
      reviewModal.hide();
      fetch(
        "Controller?option=addReview"
          + `&productID=${selectedProductId}`
          + `&stars=${selectedStar}`
      );
    }
  }
);
