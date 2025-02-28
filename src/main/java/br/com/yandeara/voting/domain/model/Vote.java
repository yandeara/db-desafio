package br.com.yandeara.voting.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"motion_id", "associate_id"})})
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "motion_id", nullable = false)
    private Motion motion;

    @NotNull
    private Long associateId;

    @NotNull
    private boolean associateVote;

    private ZonedDateTime voteTime;

}
