package com;

import org.elasticsearch.client.Client;
import org.elasticsearch.test.ESIntegTestCase;

import java.time.LocalDate;

//@SpringBootApplication
public class Application  extends ESIntegTestCase {
    private static SearchTicket searchTicket;
    private static Client client = null;
    public static void main(String[] args) throws InterruptedException {
        client = client();
        searchTicket = new SearchTicket(client);

        Application app = new Application();
        app.someExampleTest();
        /*File tempDir = Files.createTempDir();
        Settings settings = Settings.builder().put("path.home", tempDir.getAbsolutePath()).build();
        Node server = NodeBuilder.nodeBuilder().settings(settings).build();
        final String clusterName = server.settings().get("cluster.name");

        System.out.printf("starting server with cluster-name: %s\n", clusterName);
        server.start();
        Thread.sleep(2000);
        Client client = server.client();

        BeerSearch search = new BeerSearch(client);
        search.add(new Beer("1", "Becks", "mild", "tasty"));
        search.add(new Beer("2", "Holsten", "crisp", "strong"));
        search.add(new Beer("3", "Kilkenny", "mild", "sweet"));
        search.add(new Beer("4", "Budvar", "tasty", "crispy"));

        Thread.sleep(2000);
        List<Beer> beers = search.findByTag("tasty");
        beers.forEach(System.out::println);

        System.out.printf("closing server with cluster-name: %s\n", clusterName);
        server.close();*/
    }

    public void someExampleTest() {
        createIndex("sale");
        ensureGreen();

        searchTicket.add(Ticket
                .getTicketBuilder()
                .builder()
                .setId("1")
                .setFilm("Becks")
                .setDemonstrationDate(LocalDate.now())
                .build());;
        refresh();
        searchTicket.findByTag("Becks");

//        SearchResponse searchResponse = client().prepareSearch(“test”).setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
//
//        assertNoFailures(searchResponse);
//        assertFirstHit(searchResponse, hasId(“1”));
    }
}
