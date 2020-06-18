import java.time.LocalDate;

public class MusicBand implements Comparable<MusicBand> {
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long numberOfParticipants; //Поле не может быть null, Значение поля должно быть больше 0
    private Long singlesCount; //Поле может быть null, Значение поля должно быть больше 0
    private Long albumsCount; //Поле не может быть null, Значение поля должно быть больше 0
    private MusicGenre genre; //Поле не может быть null
    private Album bestAlbum; //Поле не может быть null

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public Long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setAlbumsCount(Long albumsCount) {
        this.albumsCount = albumsCount;
    }

    public Long getAlbumsCount() {
        return albumsCount;
    }

    public void setSinglesCount(Long singlesCount) {
        this.singlesCount = singlesCount;
    }

    public Long getSinglesCount() {
        return singlesCount;
    }

    public void setBestAlbum(Album bestAlbum) {
        this.bestAlbum = bestAlbum;
    }

    public Album getBestAlbum() {
        return this.bestAlbum;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    @Override
    public int compareTo(MusicBand o) {
        return this.numberOfParticipants.compareTo(o.numberOfParticipants);
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", singlesCount=" + singlesCount +
                ", albumsCount=" + albumsCount +
                ", genre=" + genre +
                ", bestAlbum=" + bestAlbum +
                '}';
    }
}