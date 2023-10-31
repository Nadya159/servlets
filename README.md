1) Сделать index.html с формой аутентификации
login/password и ссылкой на регистрацию нового пользователя
2) Сделать registration.html с формой регистрации
name, age, email, login, password
3) Сделать menu.html с ссылками на
Личный кабинет (UserServlet)
(Передавать данные между запросами можно сейчас с помощью метода session.setAttribute())
4) Создать 3 сервлета
AuthenticationServletRegistrationServlet
UserServlet
AuthenticationServlet:
Проверяет или есть в базе пользователь с такими login/password
Если есть, то делает редирект на menu.html
Если нет - возвращает ошибку
RegistrationServlet:
Принимает запрос от registration.html и проверяет или есть пользователь с таким логином
Если есть - возвращает ошибку
Если нет, то добавляет пользователя в базу
UserServlet
Отображает информацию о пользователе
name, age, email, login, password
и предоставляет возможность изменить хотя бы 1 поле
* Плюсом будет валидация полей форм
*** Плюсом будет создание функционала - это может быть любая тема, к примеру ProductServlet с каталогом продуктов. Надо будет добавить другие классы dto dao.