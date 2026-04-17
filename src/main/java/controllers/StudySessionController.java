package controllers;

import core.StudySession;
import io.StudySessionInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class StudySessionController {

    private final StudySessionInterface repository;
    private final ObservableList<StudySession> sessions;

    public StudySessionController(StudySessionInterface repository) {
        this.repository = repository;
        this.sessions = FXCollections.observableArrayList();
    }

    public ObservableList<StudySession> getSessions() {
        return sessions;
    }

    public void load() throws IOException {
        sessions.setAll(repository.loadSessions());
    }

    public void addSession(String subject, String minutesText, String difficulty) throws IOException {
        int minutes = Integer.parseInt(minutesText);
        StudySession session = new StudySession(subject, minutes, difficulty);
        sessions.add(session);
        repository.saveSessions(sessions);
    }

    public void deleteSession(StudySession session) throws IOException {
        if (session != null) {
            sessions.remove(session);
            repository.saveSessions(sessions);
        }
    }

    public int getTotalMinutes() {
        int total = 0;
        for (StudySession s : sessions) {
            total += s.minutes();
        }
        return total;
    }

    public int getSessionCount() {
        return sessions.size();
    }
}