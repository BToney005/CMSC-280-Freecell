/**
 * @author Robert Noonan (September 21, 2004 CS 301)
 * @author Don Blaheta
 * @version 25 September 2013
 *
 *Deck deals with the processes of a card deck including initializing a
 *deck, randomly dealing the cards.
 */

import java.util.Random;
import java.util.Date;
import java.util.Iterator;

public class Deck implements Iterator<Card>, Iterable<Card> {
    private Card[ ] deck = new Card[52];
    private Random random;
    private int count = 0;
    
    /**
     *Constructor - Creates a deck of 52 cards with 13 cards in spades,
     *13 cards in hearts, 13 cards in diamonds, 13 cards in clubs.
     *@param seed - the random seed that is used in the shuffle
     */
    public Deck(long seed)  {
        random = new Random(seed);
        // char[ ] suit = {'C', 'D', 'H', 'S'}; // clubs .. spades
        for (int s = 0; s < 4; s++) {
            for (int r = 1; r < 14; r++) { // ace 2 .. king
                deck[count] = new Card(r, s);
                count++;
            }//for
        }//for
    }//constructor


    /**
       Creates a deck of cards using clock as a seed
    */
    public Deck( ) { this(new Date( ).getTime( )); }

    /**
     * determines whether there is another card in the deck
     * @return true iff a subsequent call to next() will succeed */
    public boolean hasNext() {
      return count > 0;
    }
    
    /**
     * removes and returns a random card from the deck
     * @return a random card from the deck
     * @pre cannot be called more than 52 times
     */
    public Card next( )  {
        int ix = random.nextInt(count);
        Card c = deck[ix];
        --count;
        deck[ix] = deck[count];
        deck[count] = null;
        return c;
    }//nextCard

    /** throws exception (remove not implemented) */
    public void remove() {
      throw new UnsupportedOperationException();
    }

    /**
     * returns this Deck.  Permits a Deck to be used in a 
     * foreach statement. */
    public Iterator<Card> iterator() {
      return this;
    }

}//Deck

