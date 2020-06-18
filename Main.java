import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.nio.*;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    final static int port = 2345;
    private static String jsonFilePath;
    final static int bufferSize = 32768;
    static byte[] c = new byte[bufferSize];

    public static void main(String[] args) {
        try {
            jsonFilePath = args[0];
        } catch (Exception e) {
            System.out.println("Вы забыли указать файл, попробуйте заново запустить программу");
            System.exit(0);
        }
        jsonFilePath = args[0];
        Thread stopServer = new Thread(Main::stopServer);
        stopServer.setDaemon(false);
        Thread serverWork = new Thread(() -> {
            try {
                serverWork();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverWork.setDaemon(true);
        stopServer.start();
        serverWork.start();
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream b = new ByteArrayInputStream(bytes)) {
            try (ObjectInputStream o = new ObjectInputStream(b)) {
                return o.readObject();
            }
        }
    }

    public static String getJsonFilePath() {
        return jsonFilePath;
    }

    public static void stopServer() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            if (scanner.nextLine().equals("save")) {
                CommandExecution.save();
                System.out.println("Коллекция сохранена");
            }
            if (scanner.nextLine().equals("exit")) {
                System.exit(0);
            }
        }
    }

    public static void serverWork() throws Exception {
        CommandExecution commandExecution = new CommandExecution();
        SocketAddress socketAddress = new InetSocketAddress(port);//комбинация IP-адрес+порт
        DatagramChannel datagramChannel = DatagramChannel.open(); //Запускаем сервер
        datagramChannel.bind(socketAddress); //Задаем его адрес
        while (true) {
            byte[] b = new byte[bufferSize];
            ByteBuffer byteBuffer = ByteBuffer.wrap(b);//Преварщаем массив в буфер
            byteBuffer.clear();
            socketAddress = datagramChannel.receive(byteBuffer);//получаем запрос от клиента
            Commands command = (Commands) deserialize(b);
            command = Identifier.Identify(command);

            send(command, datagramChannel, socketAddress);
        }
    }

    public static void send(Commands command, DatagramChannel datagramChannel, SocketAddress socketAddress) throws Exception {
        System.out.println(command);
        byte[] b = serialize(command);
        Arrays.fill(c, (byte) 0);
        System.arraycopy(b, 0, c, 0, b.length);
        ByteBuffer byteBuffer = ByteBuffer.wrap(c);
        byteBuffer.flip();
        byteBuffer = ByteBuffer.wrap(c);
        int i = datagramChannel.send(byteBuffer, socketAddress);//отправляем обработанный резуьтат
        System.out.println(i + " байтов информации отправлено");
    }

    public static byte[] serialize(Commands command) throws IOException {
        try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            try (ObjectOutputStream o = new ObjectOutputStream(b)) {
                o.writeObject(command);
            }
            return b.toByteArray();
        }
    }
}
