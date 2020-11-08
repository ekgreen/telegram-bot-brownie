package com.goodboy.telegram.telegram.botbrownie.impl.mosenergysbyt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.MosEnergySaleApi;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.cabinet.IndicationCounterApi;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.cabinet.PersonalCabinetApi;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.core.MosEnergySaleCoreResponse;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.exception.MosEnergySaleTechnicalException;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.login.LoginApi;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.payment.PaymentRedirectApi;
import com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.statistics.StatisticApi;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class MosEnergySaleImpl implements MosEnergySaleApi {

    private final static String URL = "https://my.mosenergosbyt.ru/gate_lkcomu";
    private final static MediaType TYPE = MediaType.get("application/x-www-form-urlencoded");

    private final static DateTimeFormatter ZONE_FORMATTER = DateTimeFormatter.BASIC_ISO_DATE;

    private final OkHttpClient client;
    private final ObjectMapper mapper;

    @SneakyThrows
    public @NotNull MosEnergySaleCoreResponse<LoginApi> login(@NotNull String login, @NotNull String password) {
        final String serviceId = "login";

        final Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                        .query(URL)
                        .addQueryParameter("action", "auth")
                        .addQueryParameter("query", "login")
                        .build()
                )
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(new MultipartBody.Builder()
                        .setType(TYPE)
                        .addFormDataPart("login", login)
                        .addFormDataPart("psw", password)
                        .build())
                .build();

        if (log.isDebugEnabled())
            log.debug("[mosenergysale] request to service { id = {}, url = {} } with params { login = {}, password = *masked* }", serviceId, request.url(), login);

        return handleCall(serviceId, request, new TypeReference<MosEnergySaleCoreResponse<LoginApi>>() {});
    }

    public @NotNull MosEnergySaleCoreResponse<PersonalCabinetApi> personalCabinetInfo(@NotNull String session) {
        final String serviceId = "LSList";

        final Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                        .query(URL)
                        .addQueryParameter("action", "sql")
                        .addQueryParameter("query", "LSList")
                        .addQueryParameter("session", session)
                        .build()
                )
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(RequestBody.create(null, new byte[0]))
                .build();

        if (log.isDebugEnabled())
            log.debug("[mosenergysale] request to service { id = {}, url = {} } with params { session = {} }", serviceId, request.url(), session);

        return handleCall(serviceId, request, new TypeReference<MosEnergySaleCoreResponse<PersonalCabinetApi>>() {});
    }

    public @NotNull MosEnergySaleCoreResponse<StatisticApi> statisticsByPeriod(@NotNull String session, @NotNull String vlProvider, @NotNull ZonedDateTime starts, @Nullable ZonedDateTime ends) {
        final String serviceId = "Statistics";

        final Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                        .query(URL)
                        .addQueryParameter("action", "sql")
                        .addQueryParameter("query", "bytProxy")
                        .addQueryParameter("session", session)
                        .build()
                )
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(new MultipartBody.Builder()
                        .setType(TYPE)
                        .addFormDataPart("dt_en", ZONE_FORMATTER.format(starts))
                        .addFormDataPart("dt_st", ZONE_FORMATTER.format(ends = Optional.ofNullable(ends).orElse(ZonedDateTime.now(starts.getZone()))))
                        .addFormDataPart("plugin", "bytProxy")
                        .addFormDataPart("proxyquery", "Statistics")
                        .addFormDataPart("vl_provider", vlProvider)
                        .build())
                .build();

        if (log.isDebugEnabled())
            log.debug("[mosenergysale] request to service { id = {}, url = {} } with params { session = {}, vl_provider = {}, p_starts = {}, p_ends = {} }", serviceId, request.url(), session, vlProvider, starts, ends);

        return handleCall(serviceId, request, new TypeReference<MosEnergySaleCoreResponse<StatisticApi>>() {});
    }

    public @NotNull MosEnergySaleCoreResponse<PaymentRedirectApi> getPaymentLink(@NotNull String session, @NotNull Long _serviceId, @NotNull Long kdProvider, @NotNull Double  value) {
        final String serviceId = "MesPayRef";

        final Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                        .query(URL)
                        .addQueryParameter("action", "sql")
                        .addQueryParameter("query", "MesPayRef")
                        .addQueryParameter("session", session)
                        .build()
                )
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(new MultipartBody.Builder()
                        .addFormDataPart("id_service", _serviceId.toString())
                        .addFormDataPart("kd_provider", kdProvider.toString())
                        .addFormDataPart("vl_pay", value.toString())
                        .build())
                .build();

        if (log.isDebugEnabled())
            log.debug("[mosenergysale] request to service { id = {}, url = {} } with params { session = {}, service_id = {}, kd_provider = {}, value = {} }", serviceId, request.url(), session, _serviceId, kdProvider, value);

        return handleCall(serviceId, request, new TypeReference<MosEnergySaleCoreResponse<PaymentRedirectApi>>() {});
    }

    public @NotNull MosEnergySaleCoreResponse<IndicationCounterApi> indicationCounter(@NotNull String session, @NotNull String vlProvider) {
        final String serviceId = "IndicationCounter";

        final Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                        .query(URL)
                        .addQueryParameter("action", "sql")
                        .addQueryParameter("query", "bytProxy")
                        .addQueryParameter("session", session)
                        .build()
                )
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(new MultipartBody.Builder()
                        .addFormDataPart("plugin", "bytProxy")
                        .addFormDataPart("proxyquery", "IndicationCounter")
                        .addFormDataPart("vl_provider", vlProvider)
                        .build())
                .build();

        if (log.isDebugEnabled())
            log.debug("[mosenergysale] request to service { id = {}, url = {} } with params { session = {}, vl_provider = {} }", serviceId, request.url(), session, vlProvider);

        return handleCall(serviceId, request, new TypeReference<MosEnergySaleCoreResponse<IndicationCounterApi>>() {});
    }

    public @NotNull MosEnergySaleCoreResponse<IndicationCounterApi> currentBalance(@NotNull String session, @NotNull String vlProvider) {
        final String serviceId = "CurrentBalance";

        final Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                        .query(URL)
                        .addQueryParameter("action", "sql")
                        .addQueryParameter("query", "bytProxy")
                        .addQueryParameter("session", session)
                        .build()
                )
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(new MultipartBody.Builder()
                        .addFormDataPart("plugin", "bytProxy")
                        .addFormDataPart("proxyquery", "CurrentBalance")
                        .addFormDataPart("vl_provider", vlProvider)
                        .build())
                .build();

        if (log.isDebugEnabled())
            log.debug("[mosenergysale] request to service { id = {}, url = {} } with params { session = {}, vlProvider = {} }", serviceId, request.url(), session, vlProvider);

        return handleCall(serviceId, request, new TypeReference<MosEnergySaleCoreResponse<IndicationCounterApi>>() {});
    }

    @SneakyThrows
    private <T> MosEnergySaleCoreResponse<T> handleCall(@NotNull String serviceId, @NotNull Request request, @NotNull TypeReference<MosEnergySaleCoreResponse<T>> type){
        final Response response = client.newCall(request).execute();

        if(log.isDebugEnabled())
            log.debug("[mosenergysale] response from service { id = {}, url = {} } had response { code = {} }", serviceId, request.url(), response.code());


        if (response.code() != 200)
            throw new MosEnergySaleTechnicalException("response code not 200");

        return mapper.readValue(Objects.requireNonNull(response.body()).byteStream(), type);
    }
}
