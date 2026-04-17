package io;

import core.StudySession;
import java.io.IOException;
import java.util.List;

public interface StudySessionInterface {
    List<StudySession> loadSessions() throws IOException;
    void saveSessions(List<StudySession> sessions) throws IOException;
}