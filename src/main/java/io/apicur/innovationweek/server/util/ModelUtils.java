package io.apicur.innovationweek.server.util;

public class ModelUtils {

    /**
     * Creates a bean id from the given bean name.
     * @param name the name
     * @return the id
     */
    public static final String idFromName(String name) {
        return removeNonWord(name);
    }

    /**
     * This essentially removes any non "word" characters from the name.
     * @param name the name
     * @return the id
     */
    private static String removeNonWord(String name) {
        return name.replaceAll("[^\\w-\\.]", ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Validates that a version string is OK - doesn't contain any
     * illegal characters.
     * @param version the version
     * @return true if valid, else false
     */
    public static final boolean isValidVersion(String version) {
        return removeNonWord(version).equals(version);
    }

}
