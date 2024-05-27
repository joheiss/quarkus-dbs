package com.jovisco.quarkus.panache.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CD extends Item {

    @Column(name = "music_company")
    private String musicCompany;

    @Column(length = 100)
    private String genre;

    @OneToMany(mappedBy = "cd", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    public List<Track> tracks = new ArrayList<>();

    public void addTrack(Track track) {
      tracks.add(track);
      track.cd = this;
    }

    public void removeTrack(Track track) {
      tracks.remove(track);
      track.cd = null;
    } 
    
    public CD(String title, String description, BigDecimal price, String genre) {
      this.title = title;
      this.description = description;
      this.price = price;
      this.genre = genre;
    }
}
