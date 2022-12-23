Для запуска: gradle run

Используя методы асинхронного программирования (например, CompletableFuture для Java) или библиотеки реактивного программирования (RxJava, например) провзаимодействовать с несколькими публично доступными API и сделать приложение с любым интерфейсом, основанное на этих API. При этом API должны использоваться так:

Все вызовы должны делаться с помощью HTTP-библиотеки с асинхронных интерфейсом;
Все независимые друг от друга вызовы API должны работать одновременно;
Вызовы API, которые зависят от данных, полученных из предыдущих API, должны оформляться в виде асинхронной цепочки вызовов;
Не допускаются блокировки на ожидании промежуточных результатов в цепочке вызовов, допустима только блокировка на ожидании конечного результата (в случае консольного приложения). Другими словами, вся логика программы должна быть оформлена как одна функция, которая возвращает CompletableFuture (или аналог в вашем ЯП) без блокировок.

Логика работы:
1) В поле ввода пользователь вводит название чего-то (например "Цветной проезд") и нажимает кнопку поиска;
2) Ищутся варианты локаций с помощью метода [1] и показываются пользователю в виде списка;
3) Пользователь выбирает одну локацию;
4) С помощью метода [2] ищется погода в локации и показывается пользователю;
5) С помощью метода [3] ищутся интересные места в локации, далее для каждого найденного места с помощью метода [4] ищутся описания, всё это показывается пользователю в виде списка.

Методы API:
[1] получение локаций с координатами и названиями: https://docs.graphhopper.com/#operation/getGeocode
[2] получение погоды по координатам https://openweathermap.org/current
[3] получение списка интересных мест по координатам: https://opentripmap.io/docs#/Objects%20list/getListOfPlacesByRadius
[4] получение описания места по его id: https://opentripmap.io/docs#/Object%20properties/getPlaceByXid