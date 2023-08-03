package hexlet.code;

import io.ebean.DB;
import io.ebean.Database;
import io.ebean.Transaction;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import static org.assertj.core.api.Assertions.assertThat;
import io.javalin.Javalin;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class AppTest {
    private static final String TITLE_PAGE = "Анализатор страниц";

    private static Javalin app;
    private static String baseUrl;
    private static Database database;
    private static Transaction transaction;

    @Test
    void testInit() {
        assertThat(true).isEqualTo(true);
    }


    @BeforeAll
    public static void beforeAll() throws IOException {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
        database = DB.getDefault();
    }

    @AfterAll
    public static void afterAll() throws IOException {
        app.stop();
    }

    @BeforeEach
    void beforeEach() {
        database.script().run("/truncate.sql");
        database.script().run("/seed.sql");
    }

    @Test
    void testMainPage() {
        HttpResponse<String> response = Unirest.get(baseUrl).asString();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody()).contains(TITLE_PAGE);
    }

    @Test
    void testShowUrls() {
        HttpResponse<String> response = Unirest.get(baseUrl + "/urls").asString();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody()).contains("ID", "Имя", "Последняя проверка", "Код ответа");
        assertThat(response.getBody()).contains("https://github.com", "https://ru.hexlet.io");
    }

//    @Test
//    void testAddCorrectUrl() {
//
//    }



}
