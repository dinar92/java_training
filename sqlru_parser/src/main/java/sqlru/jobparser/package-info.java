/**
 * Данный пакет содержит элементы необходимые для парсинга с sql.ru
 * вакансий на позицию указанную в ресурсе 'jobsRegex.config' в виде регулярного выражения.
 * Приложение ведет записи как в логи, так и базу данных SQLite в таблицу 'job_offer'.
 * Логгировоние административной части (ERROR, WARN, DEBUG) ведется в 'admin.log'.
 * Логгирование найденных вакансий производится в 'info.log'.
 * Если парсер запущен в первый раз, то в ДБ создается соответствующая таблица 'job_offer'
 * заполняемая найденными вакансиями с начала текущего года.
 * Если парсер запускался ранее, то обрабатываются только новые вакансии.
 * <p>
 * Класс DBAccess - реализация доступа к базе данных. Необходим для манипулирования
 * данным в БД.
 * <p>
 * Классы DBWriter и LogWriter - ведут записи в БД и логи соответственно.
 * <p>
 * Класс Offer - реализация вакансии.
 * <p>
 * Интерфейс Offers - итерируемый контейнер вакансий.
 * <p>
 * Класс YearBeginOffers - вакансии с начала текущего года.
 * <p>
 * Класс ContinuousOffers - вакансии собранные до предыдущей последней вакансии. В ДБ последняя вакансия
 * помечена полем INTEGER 'isLast' = 1.
 * <p>
 * Класс Page - представляет страницу с оглавлениями вакансий.
 * <p>
 * Класс Topic - оглавление вакансии с названием и датой публикации.
 */

package sqlru.jobparser;