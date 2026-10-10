package Week7;

import java.util.Arrays;

/**
 * Problem 2: The Playlist
 * Demonstrates defensive copying to protect internal collection state.
 */
public class Playlist {
    private String[] songs;
    private int songCount;

    public Playlist(int capacity) {
        this.songs = new String[Math.max(0, capacity)];
        this.songCount = 0;
    }

    public void addSong(String song) {
        if (song != null && songCount < songs.length) {
            songs[songCount++] = song;
        }
    }

    public String[] getSongs() {
        return Arrays.copyOf(songs, songCount);
    }

    public int getSongCount() {
        return songCount;
    }

    public static void main(String[] args) {
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");

        String[] copy = p.getSongs();
        copy[0] = "Hacked";

        System.out.println("p.getSongs()[0] is still: " + p.getSongs()[0]);
        System.out.println("p.getSongCount() = " + p.getSongCount());
    }
}
