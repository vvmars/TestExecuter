package com;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SearchTicket {
    private static final String TYPE_TICKET = "ticket";
    private static final String INDEX = "sale";

    private final Client client;

    public SearchTicket(Client client) {
        this.client = client;
    }

    /**
     * Adds a given ticket to the search index.
     * @param ticket the ticket.
     */
    public void add(Ticket ticket) {
        System.out.printf("adding beer to search index: %s\n", ticket);
        IndexRequest indexRequest = Requests.indexRequest(INDEX)
                .id("1")
                .source("id", ticket.getId(),
                        "film", ticket.getFilm(),
                        "demonstrationDate", ticket.getDemonstrationDate());

        IndexResponse response = client.index(indexRequest).actionGet();
        System.out.printf("entry added to index '%s', type '%s', doc-version: '%s', doc-id: '%s', created: %s\n",
                response.getIndex(), response.getType(), response.getVersion(), response.getId(), response.status());
    }

    /**
     * Searches tickets by given filmName
     * @param filmName the filmName
     * @return a list of tickets
     */
    public List<Ticket> findByTag(String filmName) {
        System.out.printf("searching tickets for given tag: %s\n", filmName);
        SearchResponse response = client.prepareSearch(INDEX)
                .setQuery(QueryBuilders.termQuery("film", filmName)).execute().actionGet();
        SearchHits hits = response.getHits();
        System.out.printf("%s hits for tag '%s' found\n", hits.getHits().length, filmName);

        return StreamSupport.stream(hits.spliterator(), true).map(hit -> {
            String name = hit.field("name").getValue();
            String[] tags = hit.field("tags").getValues().toArray(new String[] {});
            return Ticket.getTicketBuilder().builder().build();
        }).collect(Collectors.toList());
    }
}
