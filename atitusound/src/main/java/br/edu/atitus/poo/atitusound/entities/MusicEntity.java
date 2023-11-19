package br.edu.atitus.poo.atitusound.entities;

import java.time.Duration;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_music")
public class MusicEntity extends GenericEntity{

	private Duration duration;
	
	private int links_count;

	private String url;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "artist_uuid")
	private ArtistEntity artist;

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public int getLinks_count() {
		return links_count;
	}

	public void setLinks_count(int links_count) {
		this.links_count = links_count;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArtistEntity getArtist() {
		return artist;
	}

	public void setArtist(ArtistEntity artist) {
		this.artist = artist;
	}
	
	
	
	
}
