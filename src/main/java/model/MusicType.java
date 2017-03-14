package model;

/**
 * Created by SergLion on 06.03.2017.
 */
public class MusicType extends Entity<Integer> {

    private String musicType;

    public MusicType() {
    }

    public MusicType(String musicType) {
        this.musicType = musicType;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    @Override
    public String toString() {
        return "MusicType{" +
                "musicType='" + musicType + '\'' +
                "} " + super.toString();
    }
}
