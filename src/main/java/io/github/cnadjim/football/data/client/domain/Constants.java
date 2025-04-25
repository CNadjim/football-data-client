package io.github.cnadjim.football.data.client.domain;

import java.time.format.DateTimeFormatter;

public interface Constants {
    String UTC_FORMAT = "yyyy-MM-d'T'H:mm:ss'Z'";
    String DAY_FORMAT = "yyyy-MM-d";

    DateTimeFormatter UTC_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(UTC_FORMAT);
    DateTimeFormatter DAY_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DAY_FORMAT);

    String ACCEPT_HEADER = "Accept";
    String APPLICATION_JSON = "application/json";
    String AUTHENTICATION_HEADER = "X-Auth-Token";

    String SCHEME = "https";
    String HOST = "api.football-data.org";
    int PORT = 443;
    String API_VERSION_4 = "v4";
    String BASE_URL = "https://api.football-data.org/v4/";
}
