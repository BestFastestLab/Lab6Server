import java.util.*;
import java.time.LocalDate;
import java.util.stream.Stream;

class Operations {
    /**
     * @value sorted-Переменная, показывающая, отсортирована ли коллекция
     */
    static boolean sorted = false;

    /**
     * @return Проверка существования объекта с заданным id
     */
    public static boolean existence(long id) {//Проверка существования объекта с заданным id в коллекции. Нужен для команды uptade_id
        boolean k = false;
        Optional<MusicBand> stream = CommandExecution.set.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        if (stream.isPresent()) {
            k = true;
        }
        return k;
    }

    /**
     * Формирование истории введенных команд
     */
    public static void historyChange(String NewCommand) {//формирование истории запросов
        System.arraycopy(CommandExecution.history, 1, CommandExecution.history, 0, 5);
        CommandExecution.history[5] = NewCommand;
    }

    /**
     * Сортировка коллекции
     */
    public static void sortSet() {//сортировка коллекции
        if (!sorted) {
            CommandExecution.set.stream()
                    .sorted()
                    .forEach(s -> {
                        CommandExecution.set.remove(s);
                        CommandExecution.set.add(s);
                    });
            sorted = true;
        }
    }

    /**
     * @return Выявление наибольшего объекта
     */
    public static MusicBand getMax() {
        Operations.sortSet();
        MusicBand[] bands = CommandExecution.set.toArray(new MusicBand[CommandExecution.set.size()]);
        return bands[bands.length - 1];
    }

    /**
     * @return Выявление объекта с наименьшим полем AlbumCount
     */
    public static MusicBand getMinAlbumCount() {
        MusicBand[] bands = CommandExecution.set.toArray(new MusicBand[CommandExecution.set.size()]);
        MusicBand p = bands[0];
        for (int i = 1; i < bands.length; i++) {
            if (bands[i].getAlbumsCount() < bands[i - 1].getAlbumsCount()) {
                p = bands[i];
            }
        }
        return p;
    }

    /**
     * @return Определение количества жанров, больших заданного с помощью кода
     */
    public static Long getQuantityGenres(int code) {
        long Quantity = 0;
        if (code < 2) {
            Quantity = CommandExecution.set.stream()
                    .filter(s -> s.getGenre().ordinal() > code)
                    .count();
        }
        return Quantity;
    }

    /**
     * @return Создание нового объекта
     */
    public static Commands creatingNewBandStep2(Commands command) {
        do {
            command.getBand().setId((int) (Math.random() * Integer.MAX_VALUE));
        } while (existence(command.getBand().getId()));
        command.getBand().setCreationDate(LocalDate.of((int) (Math.random() * 2021), (int) (Math.random() * 12 + 1), (int) (Math.random() * 28 + 1)));
        return command;
    }

    public static int removingGreater(MusicBand targetBand) {
        int k = CommandExecution.set.size();
        CommandExecution.set.removeIf(band -> band.compareTo(targetBand) > 0);
        return k - CommandExecution.set.size();
    }
}