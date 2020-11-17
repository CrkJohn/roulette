package technical.test.massiv.repo;

import technical.test.massiv.model.Bet;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
public interface BetMongoRepository extends MongoRepository<Bet, String> {

}
