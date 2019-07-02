function sendAjaxForm(result_form, ajax_form, url) {
    $.ajax({
        url:     url, //url страницы (action_ajax_form.php)
        type:     "POST", //метод отправки
        dataType: "html", //формат данных
        data: { login: $('#login').val(),
            password: $('#password').val() },
        success: function(response) { //Данные отправлены успешно
            if(response==""){
                $('#result_form').html("Неверный логин или пароль")
            }
            else{
                document.location.href = response;
            }
        },
        error: function(response) { // Данные не отправлены
            $('#result_form').html('Ошибка. Данные не отправлены.');
        }
    });
}