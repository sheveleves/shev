package hexlet.code;

import io.ebean.DB;
import io.ebean.Database;
import io.ebean.Transaction;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import io.javalin.Javalin;

import java.io.IOException;

public class AppTest {

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




}
