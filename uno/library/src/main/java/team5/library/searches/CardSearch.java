package team5.library.searches;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import team5.library.actions.WorkCard;
import team5.library.card.Card;
import team5.library.exceptions.CardNotFoundException;

/**
 *
 * @author Dmitry
 */
public class CardSearch implements Search {

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
     * @throws CardNotFoundException
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

    /**
     * Search method for cards on a fields
     *
     * @author Dmitry
     * @param request
     * @param field takes values: "icon", "color", "icon_color"
     * @throws CardNotFoundException
     */
    @Override
    public List fieldSearch(String request, String field)
            throws CardNotFoundException {

        ArrayList<Card> cards = WorkCard.getWork().getArrOfCards();
        ArrayList<Card> resultOfSearch = new ArrayList<Card>();

        if ("icon".equals(field) || "Icon".equals(field)) {
            for (Card c : cards) {
                if (SearchServices.isStringIncludeSubstring(c.getIcon().toString(), request)) {
                    resultOfSearch.add(c);
                }
            }
        }

        if ("color".equals(field) || "Color".equals(field)) {
            for (Card c : cards) {
                if (SearchServices.isStringIncludeSubstring(c.getColor().toString(), request)) {
                    resultOfSearch.add(c);
                }
            }
        }

        if ("icon_color".equals(field) || "color_icon".equals(field)
                || "Icon_color".equals(field) || "Color_icon".equals(field)) {
            for (Card c : cards) {
                if (SearchServices.isStringIncludeSubstring(c.getIcon().toString(), request)
                        && SearchServices.isStringIncludeSubstring(c.getColor().toString(), request)) {
                    resultOfSearch.add(c);
                }
            }
        }

        if (resultOfSearch.isEmpty()) {
            throw new CardNotFoundException();
        }

        return resultOfSearch;

    }

}
