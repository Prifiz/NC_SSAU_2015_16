
package team5.datamodel.searches;

/**
 * Search method of occurrences of a substring in a string
 *
 * @author Dmitry
 */
public class SearchServices {

    //Алгоритм поиска вхождения подстроки через хэш-функцию 
    public static boolean isStringIncludeSubstring(String string, String subString) {
        if (string.length() < subString.length()) {
            return false;
        }

        int patternHash = 0;
        int currentHash = 0;

        for (int i = 0; i < subString.length(); i++) {
            patternHash += subString.charAt(i);
            currentHash += string.charAt(i);
        }

        int end = string.length() - subString.length() + 1;
        for (int i = 0; i < end; i++) {
            if (patternHash == currentHash) {
                if (string.regionMatches(i, subString, 0, subString.length())) {
                    return true;
                }
            }

            currentHash -= string.charAt(i);
            if (i != end - 1) {
                currentHash += string.charAt(i + subString.length());
            }
        }

        return false;
    }
}
