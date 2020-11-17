package technical.test.massiv.repo;

import technical.test.massiv.model.Bet;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
@Repository
public interface BetMongoRepository extends ReactiveMongoRepository<Bet, String> {

}
