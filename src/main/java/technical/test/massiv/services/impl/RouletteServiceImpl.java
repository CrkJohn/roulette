package technical.test.massiv.services.impl;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import technical.test.massiv.model.Bet;
import technical.test.massiv.model.ColorBet;
import technical.test.massiv.model.PositionBet;
import technical.test.massiv.model.Roulette;
import technical.test.massiv.model.utils.ErrorMessage;
import technical.test.massiv.model.utils.State;
import technical.test.massiv.model.utils.StateBet;
import technical.test.massiv.repo.RouletteMongoRepository;
import technical.test.massiv.services.RouletteService;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouletteServiceImpl implements RouletteService {

	@Autowired
	RouletteMongoRepository rouletteRepository;

	@Override
	public Optional<String> createRoulette(Roulette roulette) {

		roulette.setState(State.CLOSED);
		return Optional.ofNullable(rouletteRepository.save(roulette).getId());
	}

	@Override
	public StateBet doColorBet(String id, ColorBet bet) {

		return doBet(id, bet);
	}

	@Override
	public StateBet doPositionBet(String id, PositionBet bet) {

		ValidatePosition(bet.getPosition());
		return doBet(id, bet);
	}

	private StateBet doBet(String id, Bet bet) {

		ValidateUserIdAndMoneyRange(bet);
		return rouletteRepository.findById(id)
								 .filter(Roulette::isOpenRoulette)
								 .map(roulette -> {
									 roulette.addBet(bet);
									 rouletteRepository.save(roulette);
									 return StateBet.ACCEPTED;
								 }).orElse(StateBet.REJECTED);
	}

	@Override
	public Boolean openRoulette(String idRoulette) {

		return rouletteRepository.findById(idRoulette)
								 .filter(Roulette::isCloseRoulette)
								 .map(roulette -> {
									 roulette.openRoulette();
									 rouletteRepository.save(roulette);
									 return Boolean.TRUE;
								 }).orElse(Boolean.FALSE);
	}

	@Override
	public Optional<List<Bet>> closeRoulette(String idRoulette) {

		Integer winnerNumber = chooseNumberWinner();
		return rouletteRepository.findById(idRoulette)
								 .map(roulette -> {
									 roulette.defineWinners(winnerNumber);
									 roulette.closeRoulette();
									 rouletteRepository.save(roulette);
									 return roulette.getBets();
								 });
	}

	@Override
	public List<Roulette> getRoulettes() {

		return rouletteRepository.findAll();
	}

	private Integer chooseNumberWinner() {

		SecureRandom rand = new SecureRandom();
		return rand.nextInt(36);
	}

	private void ValidatePosition(Integer position) {

		Validate.exclusiveBetween(0, 36, position, "");
	}

	private void ValidateUserIdAndMoneyRange(Bet bet) {

		Validate.notNull(bet.getUserId(), ErrorMessage.USER_NO_NULL.getMessage());
		boolean noAcceptableAmount = bet.getMoney() < 1 && bet.getMoney() > 10000;
		Validate.isTrue(noAcceptableAmount, ErrorMessage.ERROR_RANGE_MONEY.getMessage());
	}

}
