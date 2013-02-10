/**
 * Solution to Spotify Tech Puzzle, ID: zipfsong
 * http://www.spotify.com/se/jobs/tech/zipfsong/
 *
 * @author Martin Pettersson
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Song {
	public long f;
	public double quality;
	public String songName;
	public long lineNumber;

	public Song(long f, double quality, String songName, long lineNumber) {
		this.f = f;
		this.quality = quality;
		this.songName = songName;
		this.lineNumber = lineNumber;
	}

	public void setQuality(double quality) {
		this.quality = quality;
	}
}

class QualityComparator implements Comparator<Song> {

	@Override
	public int compare(Song song1, Song song2) {
		if (song1.quality > song2.quality)
			return 1;
		// If two songs have the same quality, sort by line number.
		else if (song1.quality == song2.quality)
			return (song1.lineNumber < song2.lineNumber) ? 1 : -1;
		else
			return -1;
	}
}

public class Zipfsong {
	Kattio io;
	ArrayList<Song> songs;
	int n;
	int m;

	public static void main(String[] args) {
		new Zipfsong();
	}

	Zipfsong() {
		io = new Kattio(System.in, System.out);

		handleInput();
		calculateQuality();

		// Sort songs according to quality.
		Collections.sort(songs, new QualityComparator());

		printBestSongs();

		io.close();
	}

	void handleInput() {
		n = io.getInt();
		m = io.getInt();
		songs = new ArrayList<Song>();

		// Read all songs.
		for (int i = 0; i < n; i++) {
			songs.add(new Song(io.getLong(), 0, io.getWord(), i + 1));
		}
	}

	void calculateQuality() {
		int i = 0;
		for (Song song : songs) {
			double quality = (double) song.f * (double) (i + 1);
			song.setQuality(quality);
			i++;
		}
	}

	void printBestSongs() {
		for (int j = 0; j < m - 1; j++) {
			io.println(songs.get(n - j - 1).songName);
		}
		io.print(songs.get(n - m).songName);
	}
}
