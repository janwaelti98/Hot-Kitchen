package ch.fhnw.webeC.service;

import ch.fhnw.webeC.model.Rating;
import ch.fhnw.webeC.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {
    public final RatingRepository repo;

    public RatingService(RatingRepository repo) {
        this.repo = repo;
    }

    public Optional<Rating> findRating(int id) {
        return repo.findById(id);
    }

    public Rating add(Rating rating) {
        return repo.save(rating);
    }

    public void update(Rating rating) {
        repo.save(rating);
    }

}
