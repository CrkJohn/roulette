package technical.test.massiv.repo;

import technical.test.massiv.model.Roulette;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
public interface RouletteMongoRepository extends MongoRepository<Roulette, String> {

}