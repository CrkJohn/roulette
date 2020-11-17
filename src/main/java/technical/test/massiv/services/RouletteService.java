package technical.test.massiv.services;

import java.util.Optional;
import java.util.List;
import technical.test.massiv.model.Bet;
import technical.test.massiv.model.ColorBet;
import technical.test.massiv.model.PositionBet;
import technical.test.massiv.model.Roulette;
import technical.test.massiv.model.utils.StateBet;

/**
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
public interface RouletteService {

	Optional<String> createRoulette(Roulette roulette);

	StateBet doColorBet(String id, ColorBet bet);

	StateBet doPositionBet(String id, PositionBet bet);

	Boolean openRoulette(String idRoulette);

	Optional<List<Bet>> closeRoulette(String idRoulette);

	List<Roulette> getRoulettes();
}
