package vol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import cards.Card;
import cards.CardDeck;
import cards.Color;
import cards.Rank;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

  @Mock
  CardDeck mockedDeck;

  @Test
  public void test__picking_spade_makes_opponent_play_twice() throws Exception {

    Mockito.doNothing().when(mockedDeck).shuffle();
    Mockito.when(mockedDeck.pickNCards(1)).thenReturn(Collections.singletonList(new Card(Color.SPADES, Rank.ACE)))
        .thenReturn(Collections.singletonList(new Card(Color.CLUBS, Rank.FOUR)))
        .thenReturn(Collections.singletonList(new Card(Color.HEARTS, Rank.TWO)));

    ArrayList<Player> players = new ArrayList<Player>();
    players.add(new Player("Victor"));
    Player gabriel = new Player("Gabriel");
    players.add(gabriel);
    Game game = new Game(players);
    game.start();

    assertEquals(2, gabriel.getHand().getCards().size());

  }

  // TODO 7 test
  // TODO 8 test
  // TODO Queen test
  // TODO Heart test
  // TODO Diamond test

}
