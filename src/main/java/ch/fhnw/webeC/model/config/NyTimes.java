package ch.fhnw.webeC.model.config;

public class NyTimes {
    private static String apiKey = "d06IbsGf2YUetGSdjeGcvBSnu6yyxlZ5";

    private static String baseUrl = "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=";

    private static String searchTerm = "Food";

    public static String getFullUrl() {
        return baseUrl + apiKey + "&q=" + searchTerm;
    }
}
