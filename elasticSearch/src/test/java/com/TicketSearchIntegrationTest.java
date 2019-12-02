package com;

import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import org.elasticsearch.client.Client;
import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.elasticsearch.test.ESIntegTestCase.Scope.SUITE;
import static org.hamcrest.Matchers.equalTo;

@ESIntegTestCase.ClusterScope(scope = SUITE)
@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
public class TicketSearchIntegrationTest extends ESIntegTestCase {
    private static SearchTicket searchTicket;
    private static Client client = null;

    @BeforeClass
    public static void init(){
        client = client();
        searchTicket = new SearchTicket(client);
    }

    /*@Before
    public static void init1(){
        System.out.println(client);
    }*/

    @Test
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


    @Test
    public void shouldIndexAndSearchBeers() throws Exception {
        Client client = client();

        SearchTicket search = new SearchTicket(client);
        search.add(Ticket
                .getTicketBuilder()
                .builder()
                .setId("1")
                .setFilm("Becks")
                .setDemonstrationDate(LocalDate.now())
                .build());
        search.add(Ticket
                .getTicketBuilder()
                .builder()
                .setId("2")
                .setFilm("Holsten")
                .setDemonstrationDate(LocalDate.now())
                .build());

        refresh(); // otherwise we would not find beers yet

        indexExists("drinks"); // verifies that index 'drinks' exists
        ensureGreen("drinks"); // ensures cluster status is green

        List<Ticket> films = search.findByTag("strong");
        assertThat(films.size(), equalTo(1));

        Ticket strongBeer = films.get(0);
        assertThat(strongBeer.getFilm(), equalTo("Holsten"));
    }
}
