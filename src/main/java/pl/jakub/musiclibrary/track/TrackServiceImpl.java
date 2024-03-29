package pl.jakub.musiclibrary.track;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService{

    final TrackRepository trackRepository;

    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    @Override
    public List<Track> findSortedPage(Integer pageNumber, Integer pageSize, String sort, Boolean descending) {
        Sort.Direction direction;
        if (descending) direction = Sort.Direction.DESC;
        else direction = Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sort));
        return trackRepository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Track> findById(Long id) {
        return trackRepository.findById(id);
    }

    @Override
    public Track save(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public Track update(Long id, Track updatedTrack) {
        Track track = trackRepository.findById(id).get();
        track.setName(updatedTrack.getName());
        track.setArtist(updatedTrack.getArtist());
        track.setAlbum(updatedTrack.getAlbum());
        track.setLength(updatedTrack.getLength());
        trackRepository.save(track);
        return track;
    }

    @Override
    public void delete(Long id) {
        trackRepository.deleteById(id);
    }

    @Override
    public List<Track> findByName(String name) {
        return trackRepository.findByName(name);
    }
}
