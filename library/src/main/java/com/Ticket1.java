package com;

import java.time.LocalDate;
import java.util.Objects;

public class Ticket1 {
    private String id;
    private String film;
    private LocalDate demonstrationDate;

    private Ticket1(){};

    public String getId() {
        return id;
    }

    public String getFilm() {
        return film;
    }

    public LocalDate getDemonstrationDate() {
        return demonstrationDate;
    }

    public static TicketBuilder getTicketBuilder(){
        return new TicketBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket1 ticket = (Ticket1) o;
        return id.equals(ticket.id) &&
                film.equals(ticket.film) &&
                demonstrationDate.equals(ticket.demonstrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, film, demonstrationDate);
    }

    // ********* BUILDER ********
    static class TicketBuilder {
        private Ticket1 ticket = null;

        public TicketBuilder setId(String id) {
            this.ticket.id = id;
            return this;
        }

        public TicketBuilder setFilm(String film) {
            this.ticket.film = film;
            return this;
        }

        public TicketBuilder setDemonstrationDate(LocalDate demonstrationDate) {
            this.ticket.demonstrationDate = demonstrationDate;
            return this;
        }

        public TicketBuilder builder(){
            ticket = new Ticket1();
            return this;
        }

        public Ticket1 build(){
            return ticket;
        }
    }


}
