package pl.jakub.musiclibrary.artist;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public List<Artist> findSortedPage(Integer pageNumber, Integer pageSize, String sort, Boolean descending) {
        Sort.Direction direction;
        if (descending) direction = Sort.Direction.DESC;
        else direction = Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sort));
        return artistRepository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist update(Long id, Artist updatedArtist) {
        Artist artist = artistRepository.findById(id).get();
        artist.setName(updatedArtist.getName());
        artist.setPlaceOfBirth(updatedArtist.getPlaceOfBirth());
        artist.setDateOfBirth(updatedArtist.getDateOfBirth());
        artist.setDateOfDeath(updatedArtist.getDateOfDeath());
        artistRepository.save(artist);
        return artist;
    }

    @Override
    public void delete(Long id) {
        artistRepository.deleteById(id);
    }

    @Override
    public List<Artist> findByName(String name) {
        return artistRepository.findByName(name);
    }
}
