package pl.jakub.musiclibrary.artist;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public List<Artist> getAll() {
        return artistService.getAll();
    }

    @GetMapping("/{id}")
    public Artist getById(@PathVariable long id) {
        return artistService.getById(id);
    }

    @PostMapping
    public List<Artist> save(@RequestBody List<Artist> artists) {
        return artistService.save(artists);
    }

//    @PutMapping("/{id}")
//    public int update(@PathVariable long id, @RequestBody Artist updatedArtist) {
//        return artistService.update(id, updatedArtist);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        artistService.delete(id);
    }
}