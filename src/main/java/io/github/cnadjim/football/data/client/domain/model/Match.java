package io.github.cnadjim.football.data.client.domain.model;



import java.time.LocalDateTime;
import java.util.Collection;

public record Match(Long id,
                    Area area,
                    Competition competition,
                    Season season,
                    String utcDate,
                    Status status,
                    Integer minute,
                    Integer injuryTime,
                    Integer attendance,
                    String venue,
                    Integer matchday,
                    Group group,
                    LocalDateTime lastUpdated,
                    Team homeTeam,
                    Team awayTeam,
                    Score score,
                    Collection<Goal> goals,
                    Collection<Booking> bookings,
                    Collection<Substitution> substitutions,
                    Collection<Referee> referees) {
}
