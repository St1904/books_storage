package books_storage.service;

import books_storage.db.model.Rack;
import books_storage.db.repository.RackRepository;
import books_storage.dto.RackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RackService {

    private final RackRepository rackRepository;

    @Autowired
    public RackService(RackRepository rackRepository) {
        this.rackRepository = rackRepository;
    }

    public RackDTO save(RackDTO rackDTO) {
        Rack rack = rackDTO.toEntity();
        return RackDTO.of(rackRepository.save(rack));
    }
}
