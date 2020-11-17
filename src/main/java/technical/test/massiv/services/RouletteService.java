package technical.test.massiv.services;

import java.util.Optional;
import java.util.List;
import technical.test.massiv.exception.NotFoundRouletteException;
import technical.test.massiv.exception.NotPossibleActionException;
import technical.test.massiv.exception.RequestException;
import technical.test.massiv.model.Bet;
import technical.test.massiv.model.ColorBet;
import technical.test.massiv.model.PositionBet;
import technical.test.massiv.model.Roulette;
import technical.test.massiv.model.utils.StateRequest;

/**
 * @author <a href="johnibanezt@gmail.com">John D. Ibanez</a>
 */
public interface RouletteService {

	Optional<String> createRoulette();

	StateRequest doColorBet(String idRoulette, String id, ColorBet bet)
			throws NotFoundRouletteException, NotPossibleActionException, RequestException;

	StateRequest doPositionBet(String idRoulette, String id, PositionBet bet)
			throws NotFoundRouletteException, NotPossibleActionException, RequestException;

	StateRequest openRoulette(String idRoulette) throws NotPossibleActionException, NotFoundRouletteException;

	Optional<List<Bet>> closeRoulette(String idRoulette) throws NotFoundRouletteException, NotPossibleActionException;

	List<Roulette> getRoulettes();
}
