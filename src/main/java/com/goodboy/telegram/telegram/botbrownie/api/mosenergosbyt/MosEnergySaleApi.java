package com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt;

import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.cabinet.IndicationCounterApi;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.cabinet.PersonalCabinetApi;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.core.MosEnergySaleCoreResponse;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.login.LoginApi;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.payment.PaymentRedirectApi;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.statistics.StatisticApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public interface MosEnergySaleApi {

    /**
     * Логин клиента на портале "Мосэнергосбыт"
     *
     * @param login    логин клиента
     * @param password пароль личного кабинета
     * @return объект с сессионными данными, необходимыми для дальнейших запросов
     */
    @NotNull MosEnergySaleCoreResponse<LoginApi> login(
            @NotNull String login,
            @NotNull String password
    );

    /**
     * Данные от линчого кабинета, необходимые для дальнейших запросов
     *
     * @param session уникальный идентификатор клиентской сессии
     * @return данные ЛК
     */
    @NotNull MosEnergySaleCoreResponse<PersonalCabinetApi> personalCabinetInfo(@NotNull String session);

    /**
     * Статистика по счетчикам за указанный период
     *
     * @param session    уникальный идентификатор клиентской сессии
     * @param vlProvider провайдер услуги
     * @param starts     начальный интервал
     * @param ends       конечный интервал (опционально), если не указано берется текущее число
     * @return статистика
     */
    @NotNull MosEnergySaleCoreResponse<StatisticApi> statisticsByPeriod(
            @NotNull String session,
            @NotNull String vlProvider,
            @NotNull ZonedDateTime starts,
            @Nullable ZonedDateTime ends
    );

    /**
     * Получить платежную ссылку
     *
     * @param session    уникальный идентификатор клиентской сессии
     * @param serviceId  идентификатор сервиса
     * @param kdProvider ключ провайдера
     * @return ссылка
     */
    @NotNull MosEnergySaleCoreResponse<PaymentRedirectApi> getPaymentLink(
            @NotNull String session,
            @NotNull Long serviceId,
            @NotNull Long kdProvider,
            @NotNull Double  value
    );

    /**
     * Количество дней до передачи счетчиков
     *
     * @param session    уникальный идентификатор клиентской сессии
     * @param vlProvider провайдер услуги
     * @return дней до передачи счетчиков
     */
    @NotNull MosEnergySaleCoreResponse<IndicationCounterApi> indicationCounter(
            @NotNull String session,
            @NotNull String vlProvider
    );

    /**
     * Текущий баланс ЛК
     *
     * @param session    уникальный идентификатор клиентской сессии
     * @param vlProvider провайдер услуги
     * @return баланс
     */
    @NotNull MosEnergySaleCoreResponse<IndicationCounterApi> currentBalance(
            @NotNull String session,
            @NotNull String vlProvider
    );

}
