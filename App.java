import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        int port = 8080;

        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);

        server.createContext("/", new SimpleHandler("OK"));
        server.createContext("/health", new SimpleHandler("healthy"));

        server.setExecutor(null);
        server.start();
        System.out.println("Servidor HTTP escuchando en puerto " + port);
    }

    private static class SimpleHandler implements HttpHandler {
        private final String body;

        private SimpleHandler(String body) {
            this.body = body;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            byte[] responseBytes = body.getBytes("UTF-8");
            exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=utf-8");
            exchange.sendResponseHeaders(200, responseBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }
        }
    }
}
