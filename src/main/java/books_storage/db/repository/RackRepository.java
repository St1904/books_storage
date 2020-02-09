package books_storage.db.repository;

import books_storage.db.model.Rack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RackRepository extends JpaRepository<Rack, Long> {
}
