package Entity;

import java.util.List;

/**
 * Stores the participants list in a round of a season
 */
public class SeasonRound {
    private List<List<Playable>> participantsList;

    /**
     * Parameterized constructor, initializes a SeasonRound instance with a List of  a List of Playable objects.
     * Assigns the List to the one passed in.
     *
     * @param participantsList List of List of Playable
     */
    public SeasonRound(List<List<Playable>> participantsList) {
        this.participantsList = participantsList;
    }

    public List<List<Playable>> getParticipantsList() {
        return participantsList;
    }

    public void setParticipantsList(List<List<Playable>> participantsList) {
        this.participantsList = participantsList;
    }
}
