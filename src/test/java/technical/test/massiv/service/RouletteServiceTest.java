package technical.test.massiv.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import technical.test.massiv.MassivApplication;
import technical.test.massiv.exception.NotFoundRouletteException;
import technical.test.massiv.exception.NotPossibleActionException;
import technical.test.massiv.exception.RequestException;
import technical.test.massiv.model.Bet;
import technical.test.massiv.model.ColorBet;
import technical.test.massiv.model.PositionBet;
import technical.test.massiv.model.Roulette;
import technical.test.massiv.model.utils.Color;
import technical.test.massiv.model.utils.State;
import technical.test.massiv.repo.RouletteMongoRepository;
import technical.test.massiv.services.RouletteService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = MassivApplication.class)
public class RouletteServiceTest {

	@Autowired
	RouletteService rouletteService;

	@Autowired
	RouletteMongoRepository rouletteMongoRepository;

	@Test
	public void shouldCreateRouletteAndReturnIdAndItsStateShouldBeClosed() {

		Optional<String> id = rouletteService.createRoulette();
		assertTrue(id.isPresent());
		Optional<Roulette> roulette = rouletteMongoRepository.findById(id.get());
		assertEquals(roulette.get().getState(), State.CLOSED);
		rouletteMongoRepository.deleteById(id.get());
	}

	@Test(expected = NotFoundRouletteException.class)
	public void shouldFailBetWhenTheRouletteDoesNotCreate()
			throws NotFoundRouletteException, RequestException, NotPossibleActionException {

		PositionBet bet = new PositionBet();
		bet.setMoney(1000);
		bet.setUserId("17");
		bet.setPosition(1);
		rouletteService.doPositionBet("No-exist", "17", bet);
	}

	@Test(expected = RequestException.class)
	public void shouldFailWhenPositionIslesToZero()
			throws NotFoundRouletteException, RequestException, NotPossibleActionException {

		PositionBet bet = new PositionBet();
		bet.setMoney(1000);
		bet.setUserId("17");
		bet.setPosition(-1);
		rouletteService.doPositionBet("No-exist", "17", bet);
	}

	@Test(expected = RequestException.class)
	public void shouldFailWhenPositionIsGreaterToThirtySix()
			throws NotFoundRouletteException, RequestException, NotPossibleActionException {

		PositionBet bet = new PositionBet();
		bet.setMoney(1000);
		bet.setUserId("17");
		bet.setPosition(99);
		rouletteService.doPositionBet("No-exist", "17", bet);
	}

	@Test(expected = RequestException.class)
	public void shouldFailWhenMoneyIsGreaterToTenHundred()
			throws NotFoundRouletteException, RequestException, NotPossibleActionException {

		ColorBet bet = new ColorBet();
		bet.setMoney(10001);
		bet.setUserId("17");
		bet.setColor(Color.BLACK);
		rouletteService.doColorBet("No-exist", "17", bet);
	}

	@Test(expected = RequestException.class)
	public void shouldFailWhenMoneyIsLessToEqualsZero()
			throws NotFoundRouletteException, RequestException, NotPossibleActionException {

		ColorBet bet = new ColorBet();
		bet.setMoney(0);
		bet.setUserId("17");
		bet.setColor(Color.BLACK);
		rouletteService.doColorBet("No-exist", "17", bet);
	}

	@Test(expected = NotPossibleActionException.class)
	public void shouldNotAddANewBetWhenTheRouletteIsClosed()
			throws NotFoundRouletteException, RequestException, NotPossibleActionException {

		Optional<String> id = rouletteService.createRoulette();
		ColorBet bet = new ColorBet();
		bet.setMoney(1);
		bet.setUserId("17");
		bet.setColor(Color.BLACK);
		rouletteService.doColorBet(id.get(), "17", bet);
	}

	@Test
	public void shouldAddANewBetWhenTheRouletteIsOpened()
			throws NotFoundRouletteException, RequestException, NotPossibleActionException {

		Optional<String> id = rouletteService.createRoulette();
		ColorBet bet = new ColorBet();
		bet.setMoney(1);
		bet.setUserId("17");
		bet.setColor(Color.BLACK);
		rouletteService.openRoulette(id.get());
		rouletteService.doColorBet(id.get(), "17", bet);
		Optional<Roulette> roulette = rouletteMongoRepository.findById(id.get());
		assertTrue(roulette.isPresent());
		List<Bet> bets = roulette.get().getBets();
		assertTrue(bets.size() == 1);
		assertTrue(bets.get(0).getUserId().equals(bet.getUserId()));
	}

}
