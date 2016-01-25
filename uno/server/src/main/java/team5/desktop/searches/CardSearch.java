
package team5.desktop.searches;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import team5.desktop.actions.WorkCard;
import team5.desktop.card.Card;
import team5.desktop.exceptions.CardNotFoundException;

/**
 *
 * @author Dmitry
 */
public class CardSearch implements Search{  
    
    /**
     * Search method for cards on a regular expression
     *
     * @author Dmitry
     * @param request 
     * @throws CardNotFoundException
     */
    @Override
    public List regularSearch(String request)
            throws CardNotFoundException {
        ArrayList<Card> cards = WorkCard.getWork().getArrOfCards();
        ArrayList<Card> resultOfSearch = new ArrayList<Card>();
        Pattern p = Pattern.compile(request);
        Matcher m;
        for (Card c : cards) {
            m = p.matcher(c.getIcon().toString());
            if (m.matches()) {
                resultOfSearch.add(c);
                continue;
            }
            m = p.matcher(c.getColor().toString());
            if (m.matches()) {
                resultOfSearch.add(c);
                continue;
            }            
        }
        if (resultOfSearch.isEmpty()) {
            throw new CardNotFoundException();
        }

        return resultOfSearch;
    }

    /**
     * Search method for cards on a substring
     *
     * @author Dmitry
     * @param request 
     * throws CardNotFoundException
     */
    @Override
    public List substringSearch(String request)
            throws CardNotFoundException {

        ArrayList<Card> cards = WorkCard.getWork().getArrOfCards();
        ArrayList<Card> resultOfSearch = new ArrayList<Card>();

        for (Card c : cards) {
            if (SearchServices.isStringIncludeSubstring(c.getIcon().toString(), request)) {
                resultOfSearch.add(c);
                continue;
            }
            if (SearchServices.isStringIncludeSubstring(c.getColor().toString(), request)) {
                resultOfSearch.add(c);
                continue;
            }            
        }

        if (resultOfSearch.isEmpty()) {
            throw new CardNotFoundException();
        }

        return resultOfSearch;
    }

}
