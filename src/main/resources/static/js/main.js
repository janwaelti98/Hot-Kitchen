
// Icons
feather.replace({'stroke-width': 1.2});

// Scroll to Top
let mybutton = document.getElementById("btn-back-to-top");

window.onscroll = function () {
    scrollFunction();
};

function scrollFunction() {
    if (
        document.body.scrollTop > 20 ||
        document.documentElement.scrollTop > 20
    ) {
        mybutton.style.display = "block";
    } else {
        mybutton.style.display = "none";
    }
}

mybutton.addEventListener("click", backToTop);

function backToTop() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
    this.blur();
}

// Ratings
function submitForm() {
    document.getElementById("ratingForm").submit();
}

$(function () {
    $(".star").tooltip({
        position: {
            my: "center bottom-20",
            at: "center top",

        }
    });
});

// Recipe edit and create
$('#btnUpload').click(function (event) {
    event.preventDefault();

    if (imgUpload.files.length > 0) {
        let formData = new FormData();
        formData.append("file", imgUpload.files[0]);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/upload",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                $('#imgHeader').attr("src", response);
                $('.invalid-feedback')
                    .text("Upload successful!")
                    .addClass("text-success")
                    .removeClass("text-danger")
                    .show();

                $('#imgField').val(response);
            },
            error: function (error) {
                console.log(error);
                $('#imgHeader').attr("src", "/uploads/default.jpg/");
                $('.invalid-feedback')
                    .text("Upload failed! Please try again!")
                    .addClass("text-danger")
                    .removeClass("text-danger")
                    .show();
            }
        });
    } else {
        $('.invalid-feedback')
            .text("Please select a file to upload.")
            .addClass("text-danger")
            .show();
    }
});

// About
$(function () {
    $(".accordion").accordion({
        collapsible: true,
        active: false
    });

});
