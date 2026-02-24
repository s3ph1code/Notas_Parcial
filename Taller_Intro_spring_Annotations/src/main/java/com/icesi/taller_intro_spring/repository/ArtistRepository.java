package com.icesi.taller_intro_spring.repository;

import com.icesi.taller_intro_spring.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ArtistRepository {
    private List<Artist> artists = new ArrayList<>();
    private long currentId;



    public void save(Artist artist){
        if(artist == null){
            throw new IllegalArgumentException("El artista no puede ser nulo");
        }
        currentId++;
        artist.setId(currentId);
        artists.add(artist);
    }

    public List<Artist> findAll(){
        // return artists;  esta forma de retornar puede ser peligrosa porque retornamos la lista interna directa es mejor la siguiente forma
        return new ArrayList<>(artists);
    }

    public Artist findById(long id){
        return artists.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public boolean deleteById(long id){
        return artists.removeIf(u -> u.getId() == id); //removeif recorre la lista y elimina todos los elementos que cumplan con
                                                            // la condicion que ponemos en este caso que coincida el id y devuelve true
                                                            // si eliminó al menos un elemento
    }


    public Artist findByName(String name){
        if(name == null){
            return null;
        }
        String normalized = name.trim(); //Este normalized es para evitar falsos negativos al inicio o al final por espacios

        return artists.stream().filter(u -> u.getName() != null && u.getName().trim()
                        .equalsIgnoreCase(normalized))
                .findFirst().orElse(null);
    }



    public boolean deleteById2(long id){
        Artist objeto_eliminar = findById(id);

        if(objeto_eliminar !=null){
            return artists.remove(objeto_eliminar);
        }
        return false;
    }


}
