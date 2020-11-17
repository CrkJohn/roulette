package technical.test.massiv.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.security.SecureRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import technical.test.massiv.MassivApplication;
import technical.test.massiv.model.utils.Color;
import technical.test.massiv.model.utils.State;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = MassivApplication.class)
public class RouletteTest {

	@Test
	public void shouldNotBeNullDateCreatedRouletteWhenItIsCreated() {

		Roulette roulette = new Roulette();
		assertNotNull(roulette.getDateCreatedRoulette());
	}

	@Test
	public void shouldChangeDateOpenedRouletteWhenItIsOpened() {

		Roulette roulette = new Roulette();
		roulette.openRoulette();
		assertNotNull(roulette.getDateOpenRoulette());
		assertEquals(roulette.getState(), State.OPENED);
	}

	@Test
	public void shouldChangeStateWhenItIsClosed() {

		Roulette roulette = new Roulette();
		roulette.openRoulette();
		assertNotNull(roulette.getDateOpenRoulette());
		assertEquals(roulette.getState(), State.OPENED);
		roulette.closeRoulette();
		assertNotNull(roulette.getDateCloseRoulette());
		assertEquals(roulette.getState(), State.CLOSED);
	}

	@Test
	public void shouldDefineFiveWinnerWithGainGraterThanZero() {

		Roulette roulette = new Roulette();
		roulette.openRoulette();
		assertNotNull(roulette.getDateOpenRoulette());
		assertEquals(roulette.getState(), State.OPENED);
		SecureRandom random = new SecureRandom();
		Integer numberWinner = random.nextInt(3);
		for (int i = 0; i < 6; ++i) {
			PositionBet bet = new PositionBet();
			bet.setMoney(1000);
			bet.setPosition(numberWinner);
			if (i == 5) {
				bet.setPosition(numberWinner + 1);
			}
			roulette.addBet(bet);
		}
		roulette.defineWinners(numberWinner);
		int amountWinners = 0;
		for (Bet bet : roulette.getBets()) {
			if (bet.getGain() > 0) {
				amountWinners++;
			}
		}
		assertTrue(amountWinners == 5);
		roulette.closeRoulette();
		assertNotNull(roulette.getDateCloseRoulette());
		assertEquals(roulette.getState(), State.CLOSED);
	}

	@Test
	public void shouldBeTheGainEqualTo15() {

		Roulette roulette = new Roulette();
		roulette.openRoulette();
		assertNotNull(roulette.getDateOpenRoulette());
		assertEquals(roulette.getState(), State.OPENED);
		SecureRandom random = new SecureRandom();
		Integer numberWinner = random.nextInt(3);
		for (int i = 0; i < 3; ++i) {
			PositionBet bet = new PositionBet();
			bet.setMoney(1);
			bet.setPosition(numberWinner);
			roulette.addBet(bet);
		}
		roulette.defineWinners(numberWinner);
		int gainTotal = 0;
		for (Bet bet : roulette.getBets()) {
			gainTotal += bet.getGain();
		}
		assertTrue(gainTotal == 15);
		roulette.closeRoulette();
		assertNotNull(roulette.getDateCloseRoulette());
		assertEquals(roulette.getState(), State.CLOSED);
	}

	@Test
	public void shouldBeTheGainEqualToFivePointFour() {

		Roulette roulette = new Roulette();
		roulette.openRoulette();
		assertNotNull(roulette.getDateOpenRoulette());
		assertEquals(roulette.getState(), State.OPENED);
		Integer numberWinner = 1;
		for (int i = 0; i < 3; ++i) {
			ColorBet bet = new ColorBet();
			bet.setMoney(1);
			bet.setColor(Color.BLACK);
			roulette.addBet(bet);
		}
		roulette.defineWinners(numberWinner);
		double gainTotal = 0;
		for (Bet bet : roulette.getBets()) {
			gainTotal += bet.getGain();
		}
		assertTrue(gainTotal == 5.4);
		roulette.closeRoulette();
		assertNotNull(roulette.getDateCloseRoulette());
		assertEquals(roulette.getState(), State.CLOSED);
	}

}
