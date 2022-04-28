# ParkingLite

Система для автоматизации процесса управления парковкой в офисе.

Функциональность:
- регистрация/авторизация пользователей
- любой зарегистрированный пользователь может:
  * получить список свободных парковочных мест,
  * отправить запрос на добавление автомобиля,
  * отправить заявку на бронирование парковочного места
  * продлить время брони
  * удалить бронирование
  * бронирование автоматически удаляется по истечении времени бронирования
- администратор может:
  * добавлять/удалять парковочные места
  * добавлять/удалять машины пользователей
  * менять уровень доступа для пользователя
  * смотреть список забронированных парковочных мест(пользователь, время, парковочное место)

  # API

POST /authenticate - get access token

POST /registration/ - register new user
POST /registration/admin - register admin

PUT /users/edit/{userId} - change user role(role_user or role_admin)

POST /bookings - create booking
PUT /bookings/{bookingId} - extend booking duration
DELETE /bookings/{id} - cancel booking

POST /cars/{userId} - add user's car
DELETE /cars/{id} - delete car from db
PUT /cars/status/{id} - update car status(requested -> approved)

POST /spots - add spot
GET /spots/free-spots - get all free spots for requested time period
GET /spots/booked-spots - get all booked spots for requested time period
DELETE /spots/{id} - delete spot