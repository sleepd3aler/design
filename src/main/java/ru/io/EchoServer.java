package ru.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    private static String cutMsg(Predicate<String> condition, String question) {
        if (condition.test(question)) {
            String[] parts = question.split("/");
            return parts[1].replace(" HTTP", "").substring(parts[1].indexOf('=') + 1);
        }
        return null;
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream())
                     )
                ) {
                    output.write("HTTP/1.1 200 OK \r\n\r\n".getBytes());
                    for (String string = input.readLine(); string != null
                            && !string.isEmpty(); string = input.readLine()) {
                        String msg = cutMsg(line -> line.startsWith("GET"), string);
                        if (msg != null) {
                            if (msg.equals("Exit")) {
                                server.close();
                            } else {
                                output.write(msg.getBytes());
                            }
                        }
                    }
                    output.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Input Exception : ", e);
        }
    }
}
