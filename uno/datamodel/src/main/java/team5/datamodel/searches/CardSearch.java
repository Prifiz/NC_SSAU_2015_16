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

        for (Card card : cards) {
            if (SearchServices.isStringIncludeSubstring(card.getIconId().toString(), request)) {
                resultOfSearch.add(card);
                continue;
            }
            if (SearchServices.isStringIncludeSubstring(card.getColor().toString(), request)) {
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
     * Search method for cards on a fields
     *
     * @author Dmitry
     * @param request
     * @param field
     * @throws CardNotFoundException
     */
    @Override
    public List searchByField(String request, FieldRequest field)
            throws CardNotFoundException {

        ArrayList<Card> cards = WorkCard.getWork().getArrOfCards();
        ArrayList<Card> resultOfSearch = new ArrayList<Card>();

        if (field == FieldRequest.ICONID) {
            for (Card card : cards) {
                if (SearchServices.isStringIncludeSubstring(card.getIconId().toString(), request)) {
                    resultOfSearch.add(card);
                }
            }
        }

        if (field == FieldRequest.COLOR) {
            for (Card card : cards) {
                if (SearchServices.isStringIncludeSubstring(card.getColor().toString(), request)) {
                    resultOfSearch.add(card);
                }
            }
        }

        if (field == FieldRequest.ICONID_COLOR) {
            for (Card card : cards) {
                if (SearchServices.isStringIncludeSubstring(card.getIconId().toString(), request)
                        && SearchServices.isStringIncludeSubstring(card.getColor().toString(), request)) {
                    resultOfSearch.add(card);
                }
            }
        }

        if (resultOfSearch.isEmpty()) {
            throw new CardNotFoundException();
        }

        return resultOfSearch;

    }

}
