package technical.test.massiv.repo;

import technical.test.massiv.model.Roulette;
import technical.test.massiv.model.utils.State;

import reactor.core.publisher.Flux;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
@Repository
public interface RouletteMongoRepository extends ReactiveMongoRepository<Roulette, String> {

	Flux<Roulette> findByState(State state);

}