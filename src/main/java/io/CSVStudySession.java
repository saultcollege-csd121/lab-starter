package io;

import core.StudySession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVStudySession implements StudySessionInterface {

    private final Path filePath;

    public CSVStudySession(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<StudySession> loadSessions() throws IOException {
        List<StudySession> sessions = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return sessions;
        }

        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            if (!line.isBlank()) {
                sessions.add(StudySession.fromCsv(line));
            }
        }

        return sessions;
    }

    @Override
    public void saveSessions(List<StudySession> sessions) throws IOException {
        List<String> lines = new ArrayList<>();
        for (StudySession session : sessions) {
            lines.add(session.toCsv());
        }
        Files.write(filePath, lines);
    }
}