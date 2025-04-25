package io.github.cnadjim.football.data.client.domain.model;


import java.util.Collection;

public record Standing(Stage stage, StandingType type, Group group, Collection<StandingTableItem> table) {
}
