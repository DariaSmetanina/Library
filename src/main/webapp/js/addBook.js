function sendNewBookForm(result_form, ajax_form, url) {
    $.ajax({
        url:     url, //url страницы (action_ajax_form.php)
        type:     "POST", //метод отправки
        dataType: "html", //формат данных
        data: { title: $('#title').val(),
            autor: $('#autor').val(),
            genre: $('#genre').val() },
        success: function(response) { //Данные отправлены успешно
            btntxt="<br>" +
            "Книга "+response+" успешно добавлена"+
            "<br>" +
            "<form action=\"../MainPage\" method=\"post\">\n" +
            "                <input type=\"submit\" value=\"На Главную страницу\" />\n" +
            "            </form>";
            $('#result_form').html(btntxt)
        },
        error: function(response) { // Данные не отправлены
            $('#result_form').html('Ошибка. Данные не отправлены.');
        }
    });
}