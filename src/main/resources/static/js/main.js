//remain scroll position after redirect
$(window).scroll(function () {
    sessionStorage.scrollTop = $(this).scrollTop();
});

$(document).ready(function () {
    if (sessionStorage.scrollTop !== "undefined") {
        $(window).scrollTop(sessionStorage.scrollTop);
    }
});

//DataTable plug-in
$(document).ready(function () {
    $('#sortableTable').DataTable(
        {
            columnDefs: [
                {
                    ordering: false,
                    targets: [6, 7]
                }
            ],
            pageLength: 15
        });
    $('.dataTables_length').addClass('bs-select');
});


$(document).ready(function () {
    $("#manager-btn").click(function () {
        $("#email").val("manager@mail.com");
        $("#password").val("112233");
    });
});
$(document).ready(function () {
    $("#anna-btn").click(function () {
        $("#email").val("ann@mail.com");
        $("#password").val("112233");
    });
});
$(document).ready(function () {
    $("#mark-btn").click(function () {
        $("#email").val("mark@mail.com");
        $("#password").val("112233");
    });
});
