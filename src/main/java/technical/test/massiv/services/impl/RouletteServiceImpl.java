package technical.test.massiv.services.impl;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technical.test.massiv.config.ConfigurationRange;
import technical.test.massiv.exception.ErrorMessage;
import technical.test.massiv.exception.NotFoundRouletteException;
import technical.test.massiv.exception.NotPossibleActionException;
import technical.test.massiv.exception.RequestException;
import technical.test.massiv.model.Bet;
import technical.test.massiv.model.ColorBet;
import technical.test.massiv.model.PositionBet;
import technical.test.massiv.model.Roulette;
import technical.test.massiv.model.utils.State;
import technical.test.massiv.model.utils.StateRequest;
import technical.test.massiv.repo.BetMongoRepository;
import technical.test.massiv.repo.RouletteMongoRepository;
import technical.test.massiv.services.RouletteService;

@Service
public class RouletteServiceImpl implements RouletteService {

	@Autowired
	ConfigurationRange configurationRange;

	@Autowired
	RouletteMongoRepository rouletteRepository;

	@Autowired
	BetMongoRepository betMongoRepository;

	@Override
	public Optional<String> createRoulette() {

		Roulette roulette = new Roulette();
		roulette.setState(State.CLOSED);
		return Optional.ofNullable(rouletteRepository.save(roulette).getId());
	}

	@Override
	public StateRequest doColorBet(String idRoulette, String userId, ColorBet bet)
			throws NotFoundRouletteException, NotPossibleActionException, RequestException {

		return doBet(idRoulette, userId, bet);
	}

	@Override
	public StateRequest doPositionBet(String idRoulette, String userId, PositionBet bet)
			throws NotFoundRouletteException, NotPossibleActionException, RequestException {

		ValidatePosition(bet.getPosition());
		return doBet(idRoulette, userId, bet);
	}

	private StateRequest doBet(String idRoulette, String userId, Bet bet)
			throws NotPossibleActionException, NotFoundRouletteException, RequestException {

		bet.setUserId(userId);
		ValidateMoneyRange(bet);
		Roulette roulette = findRouletteById(idRoulette);
		if (roulette.isOpenRoulette()) {
			betMongoRepository.save(bet);
			roulette.addBet(bet);
			rouletteRepository.save(roulette);
			return StateRequest.ACCEPTED;
		}
		throw new NotPossibleActionException(ErrorMessage.NOT_POSSIBLE_BET);
	}

	@Override
	public StateRequest openRoulette(String idRoulette) throws NotPossibleActionException, NotFoundRouletteException {

		Roulette roulette = findRouletteById(idRoulette);
		if (roulette.isCloseRoulette()) {
			roulette.openRoulette();
			rouletteRepository.save(roulette);
			return StateRequest.ACCEPTED;
		}
		throw new NotPossibleActionException(ErrorMessage.ERROR_OPEN_ROULETTE);
	}

	@Override
	public Optional<List<Bet>> closeRoulette(String idRoulette)
			throws NotFoundRouletteException, NotPossibleActionException {

		Integer winnerNumber = chooseNumberWinner();
		Roulette roulette = findRouletteById(idRoulette);
		if (roulette.isOpenRoulette()) {
			roulette.defineWinners(winnerNumber);
			roulette.closeRoulette();
			rouletteRepository.save(roulette);
			return roulette.obtainProfitsFromTheBets();
		}
		throw new NotPossibleActionException(ErrorMessage.ERROR_CLOSE_ROULETTE);
	}

	private Roulette findRouletteById(String idRoulette) throws NotFoundRouletteException {

		Optional<Roulette> optionalRoulette = rouletteRepository.findById(idRoulette);
		if (optionalRoulette.isPresent()) {
			return optionalRoulette.get();
		}
		throw new NotFoundRouletteException(ErrorMessage.NOT_FOUND_ROULETTE, idRoulette);
	}

	@Override
	public List<Roulette> getRoulettes() {

		return rouletteRepository.findAll();
	}

	private Integer chooseNumberWinner() {

		SecureRandom rand = new SecureRandom();
		return rand.nextInt(36);
	}

	private void ValidatePosition(Integer position) throws RequestException {

		validateRange(configurationRange.getMinSpotBet(), configurationRange.getMaxSpotBet(), position,
					  ErrorMessage.ERROR_POSITION_BET);
	}

	private void ValidateMoneyRange(Bet bet) throws RequestException {

		validateRange(configurationRange.getMinMoneyBet(), configurationRange.getMaxMoneyBet(), bet.getMoney(),
					  ErrorMessage.ERROR_RANGE_MONEY);
	}

	private void validateRange(Integer start, Integer end, Integer value, ErrorMessage message) throws RequestException {

		if (value < start || value > end) {
			throw new RequestException(message);
		}
	}
}
