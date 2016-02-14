package team5.datamodel.searches;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import team5.datamodel.actions.WorkCard;
import team5.datamodel.card.Card;
import team5.datamodel.exceptions.CardNotFoundException;

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
    public List searchByRegexp(String request)
            throws CardNotFoundException {
        ArrayList<Card> cards = WorkCard.getWork().getArrOfCards();
        ArrayList<Card> resultOfSearch = new ArrayList<Card>();
        Pattern p = Pattern.compile(request);
        Matcher m;
        for (Card card : cards) {
            m = p.matcher(card.getIconId().toString());
            if (m.matches()) {
                resultOfSearch.add(card);
                continue;
            }
            m = p.matcher(card.getColor().toString());
            if (m.matches()) {
                resultOfSearch.add(card);
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
    public List searchBySubstring(String request)
            throws CardNotFoundException {

        ArrayList<Card> cards = WorkCard.getWork().getArrOfCards();
        ArrayList<Card> resultOfSearch = new ArrayList<Card>();

        for (Card c : cards) {
            if (SearchServices.isStringIncludeSubstring(c.getIconId().toString(), request)) {
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
     * @param field takes values: "iconId", "color", "iconId_color"
     * @throws CardNotFoundException
     */
    @Override
    public List searchByField(String request, String field)
            throws CardNotFoundException {

        ArrayList<Card> cards = WorkCard.getWork().getArrOfCards();
        ArrayList<Card> resultOfSearch = new ArrayList<Card>();

        if ("iconId".equals(field) || "IconId".equals(field)) {
            for (Card c : cards) {
                if (SearchServices.isStringIncludeSubstring(c.getIconId().toString(), request)) {
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

        if ("iconId_color".equals(field) || "color_iconId".equals(field)
                || "IconId_color".equals(field) || "Color_iconId".equals(field)) {
            for (Card c : cards) {
                if (SearchServices.isStringIncludeSubstring(c.getIconId().toString(), request)
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
